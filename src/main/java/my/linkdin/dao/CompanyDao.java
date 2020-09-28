/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import my.linkdin.pojo.Company;
import my.linkdin.pojo.Job;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mac
 */
public class CompanyDao {
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
    
    public int addCompany(Company company) {
        int result = 0;
        try {
            getSession().beginTransaction();
            getSession().save(company);
            getSession().getTransaction().commit();
             result = 1;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return result;
    }
    
    public boolean isEmailValid(String email) {
        try{
            beginTransaction();
            Criteria criteria = session.createCriteria(Company.class);  
            criteria.add( Restrictions.eq("email", email));
            List<Company> results = criteria.list();
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
    
    public Company authenticateLogin(String email, String password) {
        Company company = null;
        try{
            beginTransaction();
            Criteria criteria = session.createCriteria(Company.class);  
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("password", password));
            List<Company> results = criteria.list();
            if (results == null || results.isEmpty())
                return null;
            company = results.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return company;
    }
    
    public int updateCompany(Company c) {
        int result = 0;
        try {
            beginTransaction();
            getSession().update(c);
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
    
    public List<Job> getJobs(int id) {
        List<Job> jobs = null;
        try {
            beginTransaction();
            
            jobs = getSession().get(Company.class, id).getJobs();
            jobs.size();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return jobs;
    }
    
    public Company getCompany(int id) {
        Company company = null;
        try {
            beginTransaction();
            company = getSession().get(Company.class, id);
            company.getJobs().size();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return company;

    }
    
    public List<Company> getCompanies() {
        List<Company> companies = null;
        try{
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
            Root<Company> company = criteria.from(Company.class);
            criteria.select(company);
                    
            
            companies = getSession().createQuery(criteria).getResultList();
            companies.size();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return companies;
    }

    public List<Company> getCompanies(boolean Verification) {
        List<Company> companies = null;
        try{
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
            Root<Company> company = criteria.from(Company.class);
            criteria.select(company).where(builder.equal(company.get("verification"), false));
            
            companies = getSession().createQuery(criteria).getResultList();
            companies.size();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return companies;
    }
    
    public void updatePwd(String email, String password) {
        try {
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
            Root<Company> company = criteria.from(Company.class);
            criteria.select(company).where(builder.equal(company.get("email"), email));
            List<Company> results = getSession().createQuery(criteria).getResultList();
            Company c = results.get(0);
            c.setPassword(password);
            getSession().update(c);
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
