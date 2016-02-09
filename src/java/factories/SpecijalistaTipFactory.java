package factories;

import java.util.List;
import models.SpecijalistaTip;
import org.hibernate.Query;
import org.hibernate.Session;

public class SpecijalistaTipFactory {
    
    public List getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<SpecijalistaTip> specijaliste = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SpecijalistaTip");
            specijaliste = (List<SpecijalistaTip>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return specijaliste;
    }
    
    public SpecijalistaTip getHigh() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<SpecijalistaTip> specijaliste;
        SpecijalistaTip specijalista = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SpecijalistaTip st where st.prioritet = 1");
            specijaliste = (List<SpecijalistaTip>)q.list();
            if(specijaliste.size() > 0)
                specijalista = specijaliste.get(0);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return specijalista;
    }
    
    public List getAllLow() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<SpecijalistaTip> specijaliste = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SpecijalistaTip st where st.prioritet > 1");
            specijaliste = (List<SpecijalistaTip>)q.list();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return specijaliste;
    }
}
