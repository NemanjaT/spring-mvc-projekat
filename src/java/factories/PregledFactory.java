package factories;

import java.util.List;
import models.Pregled;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PregledFactory {
    
    public List getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Pregled> klinike = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Pregled");
            klinike = (List<Pregled>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return klinike;
    }
    
    public List getForKorisnik(int idPacijenta) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Pregled> klinike = null;
        try {
            session.beginTransaction();
            klinike = (List<Pregled>)session.createQuery("from Pregled where pacijent_id = :pid")
                    .setParameter("pid", idPacijenta).list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return klinike;
    }
    
    public int insertPregled(Pregled pregled) {
        int inserted;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(pregled);
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
