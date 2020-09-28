/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import my.linkdin.dao.ApplicationDao;
import my.linkdin.dao.JobDao;
import my.linkdin.dao.StudentDao;
import my.linkdin.pojo.Application;
import my.linkdin.pojo.Job;
import my.linkdin.pojo.Student;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author mac
 */
public class StudentController extends AbstractController {
    
    public StudentController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userType = (String)session.getAttribute("USERTYPE");
        if (!userType.equals("STUDENT"))
            return new ModelAndView(new RedirectView("login.htm",false));
        
        Student student = (Student)session.getAttribute("USER");
        StudentDao studentDao = new StudentDao();
        JobDao jobDao = new JobDao();
        ModelAndView mv = null;
        List<Job> jobs = null;
        Job job = null;
        ApplicationDao appDao = new ApplicationDao();
        Application application = null;
        List<Application> applications = null;
        int result = 0;
        String option = request.getParameter("option") != null ? request.getParameter("option") : "";
        switch (option) {
            case "stdinfo":
                mv = new ModelAndView("stdinfo");
                break;
            case "stdapp":
                applications = studentDao.getApplications(student.getId());
                mv = new ModelAndView("stdapp", "applications", applications);
                break;
            case "stdjob":
                String search = request.getParameter("search");
                
                if (search != null) {
                    Map<String, String> restrictions = new HashMap<>();
                    String company = request.getParameter("company");
                    String title = request.getParameter("title");
                    String city = request.getParameter("city");
                    if (company != null && !company.equals(""))
                        restrictions.put("companyname", company);
                    if (title != null && !title.equals(""))
                        restrictions.put("title", title);
                    if (city != null && !city.equals(""))
                        restrictions.put("city", city);
                    jobs = jobDao.getJobs(restrictions);
                }
                else 
                    jobs = jobDao.getJobs();
                mv = new ModelAndView("stdjob", "jobs", jobs);
                break;
            case "jobdetail":
                job = jobDao.getJob(Integer.parseInt(request.getParameter("job")));
                mv = new ModelAndView("stdjobdetail", "job", job);
                break;
            case "apply":
                int jobid = Integer.parseInt(request.getParameter("job"));
                applications = studentDao.getApplications(student.getId());
                for (Application app: applications) {
                    if (app.getJob().getId() == jobid)
                        return new ModelAndView("error", "message", "You have already applied this job");
                }
                job = jobDao.getJob(jobid);
                application = new Application();
                application.setDate(new Date());
                application.setJob(job);
                application.setStatus("Application Submitted");
                application.setStudent(student);
                result = appDao.addApplication(application);
                if (result == 0)
                    mv = new ModelAndView("error", "message", "Apply Fail");
                else 
                    mv = new ModelAndView("success", "message", "Apply Success");
                break;
            case "cancel":
                int applicationid = Integer.parseInt(request.getParameter("app"));
                appDao.deleteApplication(applicationid);
                mv = new ModelAndView(new RedirectView("student.htm?option=stdapp", false));
                break;
            case "infoupdate":
                student.setName(request.getParameter("name"));
                student.setEmail(request.getParameter("email"));
                student.setPassword(request.getParameter("password"));
                
                CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
                commonsMultipartResolver.setDefaultEncoding("utf-8");
                
                if (commonsMultipartResolver.isMultipart(request))  
                {   
                    MultipartRequest multipartRequest = (MultipartRequest)request; 
                    MultipartFile file = multipartRequest.getFile("resume"); 
                    String filename = file.getOriginalFilename() + student.getId() + ".pdf";
                    File resume = new File("/Users/mac/resume", filename);
                    try{
                        file.transferTo(resume);  
                        student.setResume(filename);
                        student.setOriginresume(file.getOriginalFilename());
                    } catch (IllegalStateException e) {
                        return new ModelAndView("error", "message", "Upload resume Fail");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }  
                result = studentDao.updateStudent(student);
                if (result == 0)
                    mv = new ModelAndView("error", "message", "Update Fail");
                else 
                    mv = new ModelAndView("success", "message", "Update Success");
                break;
            case "resume":
                String resume = student.getResume();
                response.setContentType("application/pdf");
                InputStream inputStream = new FileInputStream(new File("/Users/mac/resume/" + resume));
                int nRead;
                while ((nRead = inputStream.read()) != -1) {
                    response.getWriter().write(nRead);
                }
                break;
            default:
                mv = new ModelAndView("student");
                break;
        }
        return mv;   
    }
    
}
