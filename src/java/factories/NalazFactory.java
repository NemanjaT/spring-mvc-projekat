package factories;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import models.Nalaz;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NalazFactory {
    
    public Nalaz getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Nalaz> nalazi;
        Nalaz nalaz = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Nalaz where id = :id").setParameter("id", id);
            nalazi = (List<Nalaz>)q.list();
            if(nalazi.size() > 0)
                nalaz = nalazi.get(0);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return nalaz;
    }
    
    public int insertNalaz(Nalaz nalaz) {
        int inserted = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            Serializable ser = session.save(nalaz);
            if (ser != null) {
                inserted = (Integer) ser;
            }
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
