/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.dao;

import java.util.Iterator;
import java.util.List;
import my.linkdin.pojo.Student;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import my.linkdin.pojo.Application;
import my.linkdin.pojo.Job;
/**
 *
 * @author mac
 */
public class StudentDao {
    private Session session=null;
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();   
    private Session getSession(){
        if(session==null || !session.isOpen()){
            session = sf.openSession();
            
        }
        return session;
    }
  
    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();;
    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }
    
    public int addStudent(Student student) {
        int result = 0;
        try {
            beginTransaction();
            getSession().save(student);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    public Student getStudent(int id) {
        Student student = null;
        try {
            beginTransaction();
            student = getSession().get(Student.class, id);
            
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return student;
    }
    
    public boolean isEmailValid(String email) {
        try{
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
            Root<Student> student = criteria.from(Student.class);
            criteria.select(student).where(builder.equal(student.get("email"), email));
            List<Student> results = getSession().createQuery(criteria).getResultList();
            if (results == null || results.isEmpty())
                return false;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return true;
    }
    
    public Student authenticateLogin(String email, String password) {
        Student s = null;
        try{
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
            Root<Student> student = criteria.from(Student.class);
            criteria.select(student).where(builder.and(
                    builder.equal(student.get("email"), email),
                    builder.equal(student.get("password"), password)));
                    
            
            List<Student> results = getSession().createQuery(criteria).getResultList();
            
            
            if (results == null || results.isEmpty())
                return null;
            s = results.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return s;
    }
    
    public int updateStudent(Student s) {
        int result = 0;
        try {
            beginTransaction();
            getSession().update(s);
            commit();
            result = 1;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }

    public List<Application> getApplications(int id) {
        List<Application> apps = null;
        try {
            beginTransaction();
            
            apps = getSession().get(Student.class, id).getApplications();
            Iterator<Application> iterator = apps.iterator();
            while (iterator.hasNext()) {
                Application app = iterator.next();
                if (!app.getJob().getCompany().getVerification())
                    iterator.remove();
            }
        }
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return apps;
    }
    
    public void updatePwd(String email, String password) {
        try {
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
            Root<Student> student = criteria.from(Student.class);
            criteria.select(student).where(builder.equal(student.get("email"), email));
            List<Student> results = getSession().createQuery(criteria).getResultList();
            Student s = results.get(0);
            s.setPassword(password);
            getSession().update(s);
            commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
    }
}
