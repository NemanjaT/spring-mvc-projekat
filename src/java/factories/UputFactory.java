package factories;

import java.util.List;
import models.Korisnici;
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
    
    public List getAllPresentUniqueForLekar(Korisnici lekarSpecijalista) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Uput> uputi = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Uput where datum_pregleda > CURRENT_TIMESTAMP() and klinika_id = :klinika and specijalista_tip_id = :specijalista group by pacijent_id")
                    .setParameter("klinika", lekarSpecijalista.getKlinikaId())
                    .setParameter("specijalista", lekarSpecijalista.getSpecijalistaTipId());
            uputi = (List<Uput>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return uputi;
    }
    
    public List getAllForPacijent(int pacijentId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Uput> uputi = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Uput where datum_pregleda > CURRENT_TIMESTAMP() and pacijent_id = :pacijent")
                    .setParameter("pacijent", pacijentId);
            uputi = (List<Uput>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return uputi;
    }
    
    public Uput getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Uput uput = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Uput where datum_pregleda > CURRENT_TIMESTAMP() and id = :id")
                    .setParameter("id", id);
            List<Uput> uputi = (List<Uput>)q.list();
            if(uputi.size() > 0)
                uput = uputi.get(0);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return uput;
    }
    
    public int updateUputPregledan(Uput uput) {
        int updated;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            uput.setPregledan(1);
            session.beginTransaction();
            session.update(uput);
            session.getTransaction().commit();
            updated = 1;
        } catch (Exception e) {
            System.err.println(e);
            updated = -1;
        } finally {
            if(session.isOpen())
                session.close();
        }
        return updated;
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
