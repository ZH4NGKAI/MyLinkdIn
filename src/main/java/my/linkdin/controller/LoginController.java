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
import my.linkdin.pojo.Admin;
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
public class LoginController extends AbstractController {
    
    public LoginController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        String option = request.getParameter("option") == null ? "" : request.getParameter("option");
        if (option.equals("")) {
            if (session.getAttribute("USER") == null)
                return new ModelAndView("login");
            else
                return new ModelAndView(new RedirectView("home.htm", false));
        }
        
        ModelAndView mv = null;
        AdminDao adminDao = new AdminDao();
        CompanyDao companyDao = new CompanyDao();
        StudentDao studentDao = new StudentDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        switch (option) {
            case "logout":
                session.invalidate();
                mv = new ModelAndView("login");
                break;
            case "admin":
                Admin admin = adminDao.authenticateLogin(email, password);
                if(admin == null){
                    return new ModelAndView("error", "message", "Email or password are wrong");
                } else{
                    session.setAttribute("USER", admin);
                    session.setAttribute("USERTYPE", "ADMIN");
                    mv = new ModelAndView(new RedirectView("home.htm", false));
                }
                break;
            case "company":
                Company company = companyDao.authenticateLogin(email, password);
                if(company == null){
                    return new ModelAndView("error", "message", "Email or password are wrong");
                } else{
                    session.setAttribute("USER", company);
                    session.setAttribute("USERTYPE", "COMPANY");
                    mv = new ModelAndView(new RedirectView("home.htm", false));
                }
                break;
            case "student":
                Student student = studentDao.authenticateLogin(email, password);
                if(student == null){
                    return new ModelAndView("error", "message", "Email or password are wrong");
                } else{
                    session.setAttribute("USER", student);
                    session.setAttribute("USERTYPE", "STUDENT");
                    mv = new ModelAndView(new RedirectView("home.htm", false));
                }
                break;
            case "pwd":
                mv = new ModelAndView("pwdhelp");
                break;
            case "pwdreset":
                String code = "";
                if (request.getParameter("button").equals("Send Verify Code")) {
                    if ((request.getParameter("user").equals("student") && !studentDao.isEmailValid(email)) 
                            || (request.getParameter("user").equals("company") && !companyDao.isEmailValid(email)))
                        return new ModelAndView("error", "message", "Email Not Existed");
                     
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
                if (request.getParameter("user").equals("student"))
                    studentDao.updatePwd(email, password);
                else 
                    companyDao.updatePwd(email, password);                
                mv = new ModelAndView("success", "message", "Password Reset");
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
