package factories;

import java.util.List;
import models.Korisnici;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class KorisniciFactory {
    
    public List getAll(boolean accepted) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Korisnici> korisnici = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnici" + (accepted ? " where prihvacen = 1" : ""));
            korisnici = (List<Korisnici>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnici;
    }
    
    public List getAllPacijenti(boolean accepted) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Korisnici> korisnici = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnici where tip = 'pacijent'" + (accepted ? " and prihvacen = 1" : " and prihvacen = 0"));
            korisnici = (List<Korisnici>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnici;
    }
    
    public List getAllPacijentiFromKlinika(int klinikaId, boolean accepted) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Korisnici> korisnici = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnici where tip = 'pacijent' and klinika_id = :klinika_id" + (accepted ? " and prihvacen = 1" : ""))
                    .setParameter("klinika_id", klinikaId);
            korisnici = (List<Korisnici>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnici;
    }
    
    public List getAllLekariFromKlinika(int klinikaId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Korisnici> korisnici = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnici where klinika_id = :klinid and IFNULL(specijalista_tip_id, 0) > 1 order by prezime")
                    .setParameter("klinid", klinikaId);
            korisnici = (List<Korisnici>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnici;
    }
    
    public List getAllLekariFromNotKlinika(int klinikaId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Korisnici> korisnici = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnici where klinika_id != :klinid and IFNULL(specijalista_tip_id, 0) > 1 order by prezime")
                    .setParameter("klinid", klinikaId);
            korisnici = (List<Korisnici>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnici;
    }
    
    public List getAllLekari() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Korisnici> korisnici = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnici where tip like '%lekar%'");
            korisnici = (List<Korisnici>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnici;
    }
    
    public List getAllNacelnici() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Korisnici> korisnici = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnici where specijalista_tip_id = 1");
            korisnici = (List<Korisnici>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnici;
    }
    
    public Korisnici getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Korisnici korisnik = null;
        try {
            session.beginTransaction();
            List<Korisnici> korisnici = (List<Korisnici>)session.createQuery("from Korisnici k where id = :id")
                    .setParameter("id", id).list();
            if(korisnici.size() > 0)
                korisnik = korisnici.get(0);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnik;
    }
    
    public Korisnici tryLogin(String jmbg, String pass) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Korisnici korisnik = null;
        try {
            session.beginTransaction();
            List<Korisnici> korisnici = (List<Korisnici>)session.createQuery("from Korisnici k where k.jmbg = :jmbg and k.lozinka = :pass and prihvacen = 1")
                    .setParameter("jmbg", jmbg)
                    .setParameter("pass", pass).list();
            if(korisnici.size() > 0)
                korisnik = korisnici.get(0);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return korisnik;
    }
    
    public int updatePassword(Korisnici korisnik, String oldPass, String newPass) {
        int updated = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Korisnici> lista = (List<Korisnici>)session.createQuery("from Korisnici k where k.jmbg = :jmbg and k.lozinka = :pass and prihvacen = 1")
                    .setParameter("jmbg", korisnik.getJmbg())
                    .setParameter("pass", oldPass).list();
            if(lista.size() > 0) {
                updated = session.createQuery("update Korisnici k set k.lozinka = :lozinka where k.jmbg = :jmbg")
                        .setParameter("lozinka", newPass)
                        .setParameter("jmbg", korisnik.getJmbg()).executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return updated;
    }
    
    public int updateKlinikaId(Korisnici korisnik, int klinikaId) {
        int updated = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Korisnici> lista = (List<Korisnici>)session.createQuery("from Korisnici k where k.id = :id")
                    .setParameter("id", korisnik.getId());
            if(lista.size() > 0) {
                updated = session.createQuery("update Korisnici k set k.klinika_id = :klinid where k.id = :id")
                        .setParameter("klinid", klinikaId)
                        .setParameter("id", korisnik.getId()).executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return updated;
    }
    
    public int prihvatiKorisnika(Korisnici korisnik) {
        int updated = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            korisnik.setPrihvacen(1);
            session.update(korisnik);
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
    
    public int simpleUpdate(Korisnici korisnik) {
        int updated = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(korisnik);
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
    
    public int deleteKorisnik(Korisnici korisnik) {
        int deleted = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(korisnik);
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
    
    public int insertKorisnik(Korisnici korisnik) {
        int inserted;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(korisnik);
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
