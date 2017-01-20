package hr.karlovrbic.notify.v1.dao.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Karlo Vrbic on 10/30/16.
 */
@WebListener
public class Initialization implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("database");
        JPAEMFProvider.setEmf(emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory emf = JPAEMFProvider.getEmf();
        JPAEMFProvider.setEmf(null);
        if (emf != null) {
            emf.close();
        }
    }
}
