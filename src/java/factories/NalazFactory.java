package factories;

import models.Nalaz;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NalazFactory {
    
    public int insertNalaz(Nalaz nalaz) {
        int inserted;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(nalaz);
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
