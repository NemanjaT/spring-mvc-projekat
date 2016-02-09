package factories;

import java.util.List;
import models.Uput;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UputFactory {
    
    public List getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Uput> uputi = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Uput");
            uputi = (List<Uput>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return uputi;
    }
    
    public List getAllPresent() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Uput> uputi = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Uput where datum_pregleda > CURRENT_TIMESTAMP() order by datum_pregleda");
            uputi = (List<Uput>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return uputi;
    }
    
    public int insertUput(Uput uput) {
        int inserted;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(uput);
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
