/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import my.linkdin.dao.CompanyDao;
import my.linkdin.pojo.Company;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author mac
 */
public class HomeController extends AbstractController {
    
    public HomeController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        
        String userType = (String)session.getAttribute("USERTYPE");
        
        ModelAndView mv = null;
        switch (userType) {
            case "ADMIN":
                CompanyDao companyDao = new CompanyDao();
                String option = request.getParameter("option") != null ? request.getParameter("option") : "";
                List<Company> list = null;
                if (option.equals("unverified"))
                    list = companyDao.getCompanies(false);
                else
                    list = companyDao.getCompanies();
                mv = new ModelAndView("admin", "companies", list);
                break;
            case "COMPANY":
                mv = new ModelAndView("company");
                break;
            case "STUDENT":
                mv = new ModelAndView("student");
                break;
        }
        return mv;
    }
    
}
