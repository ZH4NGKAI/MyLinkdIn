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
import my.linkdin.pojo.Admin;
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
public class AdminDao {
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;
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
    
    public int addAdmin(Admin admin) {
        int result = 0;
        try {
            beginTransaction();
            getSession().save(admin);
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    
    
    public Admin authenticateLogin(String email, String password) {
        Admin admin = null;
        try{
            beginTransaction();
            Criteria criteria = session.createCriteria(Admin.class);  
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("password", password));
            List<Admin> results = criteria.list();
            if (results == null || results.isEmpty())
                return null;
            admin = results.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return admin;
    }
    
    public boolean isEmailExisted(String email) {
        try {
            beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Admin> criteria = builder.createQuery(Admin.class);
            Root<Admin> admin = criteria.from(Admin.class);
            criteria.select(admin).where(builder.equal(admin.get("email"), email));
            List<Admin> results = getSession().createQuery(criteria).getResultList();
            if (results != null && results.size() > 0)
                return true;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return false;
    }
}
