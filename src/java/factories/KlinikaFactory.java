package factories;

import java.util.List;
import models.Klinika;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class KlinikaFactory {
    
    public List getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Klinika> klinike = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Klinika");
            klinike = (List<Klinika>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return klinike;
    }
    
    public Klinika getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Klinika klinika = null;
        try {
            session.beginTransaction();
            List<Klinika> klinike = (List<Klinika>)session.createQuery("from Klinika k where id = :id")
                    .setParameter("id", id).list();
            if(klinike.size() > 0)
                klinika = klinike.get(0);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return klinika;
    }
    
    public int deleteKlinika(Klinika klinika) {
        int deleted = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(klinika);
            session.getTransaction().commit();
            deleted = 1;
        } catch (Exception e) {
            System.err.println(e);
            deleted = -1;
        } finally {
            if(session.isOpen())
                session.close();
        }
        return deleted;
    }
    
    public int insertKlinika(Klinika klinika) {
        int inserted;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(klinika);
            tx.commit();
            inserted = 1;
        } catch (Exception e) {
            System.err.println(e);
            inserted = -1;
        } finally {
            if(session.isOpen())
                session.close();
        }
        return inserted;
    }
}
