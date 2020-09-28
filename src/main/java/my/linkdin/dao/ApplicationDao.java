/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.dao;

import my.linkdin.pojo.Application;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mac
 */
public class ApplicationDao {
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
    
    public int addApplication(Application application) {
        int result = 0;
        try {
            beginTransaction();
            getSession().save(application);
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    public Application getApplication(int id) {
        Application app = null;
        try {
            beginTransaction();
            app = getSession().get(Application.class, id);
            
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return app;
    }

    public void deleteApplication(int id) {
        
        try {
            beginTransaction();
            Application app = getSession().get(Application.class, id);
            getSession().delete(app);
            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
    }
    
    public void updateApplication(Application app) {
        try {
            beginTransaction();
            
            getSession().update(app);
            
            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
    }
}
