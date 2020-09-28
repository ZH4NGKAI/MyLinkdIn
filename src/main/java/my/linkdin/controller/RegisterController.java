/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import my.linkdin.dao.AdminDao;
import my.linkdin.dao.CompanyDao;
import my.linkdin.dao.StudentDao;
import my.linkdin.email.EmailSection;
import my.linkdin.pojo.Company;
import my.linkdin.pojo.Student;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author mac
 */
public class RegisterController extends AbstractController {
    
    public RegisterController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        
        String option = request.getParameter("option") == null ? "" : request.getParameter("option");
        if (option.equals(""))
            return new ModelAndView("login");
        ModelAndView mv = null;
        AdminDao adminDao = new AdminDao();
        StudentDao studentDao = new StudentDao();
        CompanyDao companyDao = new CompanyDao();
        String email = request.getParameter("email");
        if (adminDao.isEmailExisted(email) || studentDao.isEmailValid(email) || companyDao.isEmailValid(email)){
            return new ModelAndView("error", "message", "This email has been registered");
        }
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String code = "";
        int result = 0;
        if (request.getParameter("button").equals("Send Verify Code")) {
            EmailSection section = new EmailSection();
            if (section.sendEmail(email)) {
                code = section.getConfirmCode();
                session.setAttribute("code", code);
                session.setAttribute("email", email);
                return new ModelAndView("success", "message", "Email Sending Success");
            }
            else
                return new ModelAndView("error", "message", "Email Sending Fail");
        }
        code = (String)session.getAttribute("code");
        if (!request.getParameter("code").equals(code) || !email.equals((String)session.getAttribute("email"))) {
            session.invalidate();
            return new ModelAndView("error", "message", "Wrong Verify Code");
        }
        if (!passwordPattern(password)) {
            return new ModelAndView("error", "message", "Password must contains at least one lowercase letter,"
                    + "one uppercase letter, one digit, one unique letter($*#&) and its length should be from 8 to 16");
        }
        switch (option) {
            case "student":
                Student student = new Student();
                student.setEmail(email);
                student.setPassword(password);
                student.setName(name);
                result = studentDao.addStudent(student);
                if (result == 1) {
                    session.setAttribute("USER", student);
                    session.setAttribute("USERTYPE", "STUDENT");
                    mv = new ModelAndView(new RedirectView("home.htm", false));
                }
                else {
                    mv = new ModelAndView("error", "message", "Register Failed");
                }
                break;
            case "company":
                Company company = new Company();
                company.setEmail(email);
                company.setPassword(password);
                company.setName(name);
                company.setVerification(false);
                result = companyDao.addCompany(company);
                if (result == 1) {
                    session.setAttribute("USER", company);
                    session.setAttribute("USERTYPE", "COMPANY");
                    mv = new ModelAndView(new RedirectView("home.htm",false));
                }
                else {
                    mv = new ModelAndView("error", "message", "Register Failed");
                }  
                break;
            default:
                break;
        }
        return mv;
    }
    private boolean passwordPattern(String password) {
        Pattern p = Pattern.compile("^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))(?=.*[$*#&]).{8,16}$");
        Matcher m = p.matcher(password);
        boolean b = m.matches();
        return b;
    }

}
