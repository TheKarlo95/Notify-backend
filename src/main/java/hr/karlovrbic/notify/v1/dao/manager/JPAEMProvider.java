package hr.karlovrbic.notify.v1.dao.manager;

import hr.karlovrbic.notify.v1.dao.DAOException;

import javax.persistence.EntityManager;

/**
 * Created by Karlo Vrbic on 10/30/16.
 */
public class JPAEMProvider {

    private static ThreadLocal<EntityManager> LOCALS = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        EntityManager em = LOCALS.get();
        if (em == null) {
            em = JPAEMFProvider.getEmf().createEntityManager();
            em.getTransaction().begin();
            LOCALS.set(em);
        }
        return em;
    }

    public static void close() throws DAOException {
        EntityManager em = LOCALS.get();
        if (em == null) {
            return;
        }
        DAOException dex = null;
        try {
            em.getTransaction().commit();
        } catch (Exception ex) {
            dex = new DAOException("Unable to commit transaction.", ex);
        }
        try {
            em.close();
        } catch (Exception ex) {
            if (dex != null) {
                dex = new DAOException("Unable to close entity manager.", ex);
            }
        }
        LOCALS.remove();
        if (dex != null)
            throw dex;
    }
}