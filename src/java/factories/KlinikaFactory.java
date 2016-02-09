package factories;

import java.util.List;
import models.Klinika;
import org.hibernate.Query;
import org.hibernate.Session;

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
}
