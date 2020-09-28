/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import my.linkdin.dao.ApplicationDao;
import my.linkdin.dao.CompanyDao;
import my.linkdin.dao.JobDao;
import my.linkdin.dao.StudentDao;
import my.linkdin.pojo.Application;
import my.linkdin.pojo.Company;
import my.linkdin.pojo.Job;
import my.linkdin.pojo.Student;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author mac
 */
public class CompanyController extends AbstractController {
    
    public CompanyController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         HttpSession session = request.getSession();
        String userType = (String)session.getAttribute("USERTYPE");
        if (!userType.equals("COMPANY"))
            return new ModelAndView(new RedirectView("login.htm",false));
        
        Company company = (Company)session.getAttribute("USER");
        CompanyDao companyDao = new CompanyDao();
        JobDao jobDao = new JobDao();
        Job job = (Job)request.getAttribute("job");
        ApplicationDao applicationDao = new ApplicationDao();
        StudentDao studentDao = new StudentDao();
        Student student = null;
        ModelAndView mv = null;
        int result = 0;
        String option = request.getParameter("option") != null ? request.getParameter("option") : "";
        if (!option.equals("cpinfo") && !option.equals("infoupdate") && !company.getVerification())
            return new ModelAndView("error", "message", "Company is unverified, cannot access job/applications");
        switch (option) {
            case "cpinfo":
                mv = new ModelAndView("cpinfo");
                break;
            case "infoupdate":
                company.setName(request.getParameter("name"));
                company.setEmail(request.getParameter("email"));
                company.setAddress(request.getParameter("address"));
                company.setDescription(request.getParameter("description"));
                company.setPassword(request.getParameter("password"));
                result = companyDao.updateCompany(company);
                if (result == 0)
                    mv = new ModelAndView("error", "message", "Update Fail");
                else 
                    mv = new ModelAndView("success", "message", "Update Success");
                break;
            case "cpjob":
                List<Job> jobs = companyDao.getJobs(company.getId());
                mv = new ModelAndView("cpjob", "jobs", jobs);
                break;
            case "newjob":
                mv = new ModelAndView("newjob");
                break;
            case "jobdetail":
                job = jobDao.getJob(Integer.parseInt(request.getParameter("job")));
                mv = new ModelAndView("jobdetail", "job", job);
                break;
            case "addjob":
                job = new Job();
                job.setDate(new Date());
                job.setTitle(request.getParameter("title"));
                job.setCity(request.getParameter("city"));
                job.setDescription(request.getParameter("description"));
                job.setCompany(company);
                job.setCompanyname(company.getName());
                result = jobDao.addJob(job);
                if (result == 0)
                    mv = new ModelAndView("error", "message", "Add Fail");
                else {
//                    company.getJobs().add(job);
                    mv = new ModelAndView("success", "message", "Add Success");
                }
                break;
            case "deletejob":
                job = jobDao.getJob(Integer.parseInt(request.getParameter("job")));
                jobDao.deleteJob(job);
                
                mv = new ModelAndView(new RedirectView("company.htm?option=cpjob",false));
//                company.getJobs().remove(job);
                break;
            case "updatejob":
                job.setTitle(request.getParameter("title"));
                job.setCity(request.getParameter("city"));
                job.setDescription(request.getParameter("description"));
                result = jobDao.updateJob(job);
                if (result == 0)
                    mv = new ModelAndView("error", "message", "Delete Fail");
                else
                    mv = new ModelAndView("success", "message", "Delete Success");
                break;
            case "status":
                String status = request.getParameter("status");
                Application app = applicationDao.getApplication(Integer.parseInt(request.getParameter("application")));
                app.setStatus(status);
                applicationDao.updateApplication(app);
                job = jobDao.getJob(app.getJob().getId());
//                mv = new ModelAndView(new RedirectView("company.htm?option=jobdetail",false));
                mv = new ModelAndView("jobdetail", "job", job);
                break;
            case "stdinfo": 
                student = studentDao.getStudent(Integer.parseInt(request.getParameter("student")));
                mv = new ModelAndView("cpstdinfo", "student", student);
                break;
            case "resume":
                student = studentDao.getStudent(Integer.parseInt(request.getParameter("student")));
                String resume = student.getResume();
                response.setContentType("application/pdf");
                InputStream inputStream = new FileInputStream(new File("/Users/mac/resume/" + resume));
                int nRead;
                while ((nRead = inputStream.read()) != -1) {
                    response.getWriter().write(nRead);
                }
                break;
            default:
                mv = new ModelAndView("company");
                break;
        }
        return mv;
    }
    
}
