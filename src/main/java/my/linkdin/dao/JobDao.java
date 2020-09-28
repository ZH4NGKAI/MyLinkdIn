/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import my.linkdin.pojo.Job;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mac
 */
public class JobDao {
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
    
    public int addJob(Job job) {
        int result = 0;
        try {
            beginTransaction();
            getSession().save(job);
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }

    public void deleteJob(Job job) {
        
        try {
            beginTransaction();
            getSession().delete(job);
            commit();
            
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        
    }
    
    public int updateJob(Job job) {
        int result = 0;
        try {
            beginTransaction();
            getSession().update(job);
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }

    public Job getJob(int id) {
        Job job = null;
        try {
            beginTransaction();
            job = getSession().get(Job.class, id);
            job.getApplications().size();
                    
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return job;
    }
    
    public List<Job> getJobs() {
        List<Job> jobs = null;
        try{
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Job> criteria = builder.createQuery(Job.class);
            Root<Job> job = criteria.from(Job.class);
            criteria.select(job);
                    
            
            jobs = getSession().createQuery(criteria).getResultList();
            
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job j = iterator.next();
                if (!j.getCompany().getVerification())
                    iterator.remove();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return jobs;
    }
    
    public List<Job> getJobs(Map<String, String> restrictions) {
        List<Job> jobs = null;
        try{
            
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Job> criteria = builder.createQuery(Job.class);
            Root<Job> job = criteria.from(Job.class);
            Predicate[] predicates = new Predicate[restrictions.size()];
            int i = 0;
            for (String key: restrictions.keySet()) {
                predicates[i] = builder.equal(job.get(key), restrictions.get(key));
                i++;
            }
            criteria.select(job).where(predicates);       
            jobs = getSession().createQuery(criteria).getResultList();
            
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job j = iterator.next();
                if (!j.getCompany().getVerification())
                    iterator.remove();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return jobs;
    } 
}
