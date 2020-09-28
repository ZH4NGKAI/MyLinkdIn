/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import my.linkdin.dao.CompanyDao;
import my.linkdin.pojo.Company;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author mac
 */
public class AdminController extends AbstractController {
    
    public AdminController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userType = (String)session.getAttribute("USERTYPE");
        if (!userType.equals("ADMIN"))
            return new ModelAndView(new RedirectView("login.htm",false));
        CompanyDao companyDao = new CompanyDao();
        Company company = companyDao.getCompany(Integer.parseInt(request.getParameter("cp")));
        
        ModelAndView mv = null;
        String option = request.getParameter("option") != null ? request.getParameter("option") : "";
        switch (option) {
            case "cpinfo":
                
                mv = new ModelAndView("adcpinfo", "company", company);
                break;
            case "verify0":
                company.setVerification(!company.getVerification());
                companyDao.updateCompany(company);
                mv = new ModelAndView(new RedirectView("home.htm", false));
                break;
            case "verify1":
                company.setVerification(!company.getVerification());
                companyDao.updateCompany(company);
                mv = new ModelAndView(new RedirectView("admin.htm?option=cpinfo&cp="+company.getId(), false));
                break;
        }
                
        return mv;
    }
    
}
