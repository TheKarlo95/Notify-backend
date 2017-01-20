package hr.karlovrbic.notify.v1.dao.manager;

import javax.persistence.EntityManagerFactory;

/**
 * Created by thekarlo95 on 10/30/16.
 */
public class JPAEMFProvider {

    public static EntityManagerFactory emf;

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        JPAEMFProvider.emf = emf;
    }

}