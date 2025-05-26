/*
 * ServicioProvincia
 */

package Modelo.Servicio;

/**
 *
 * @author pauladominguez
 */

import Modelo.Entidades.Provincias;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ServicioProvincia implements Serializable {

    private EntityManagerFactory emf;

    public ServicioProvincia(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Provincias> obtenerProvincias() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Provincias p", Provincias.class).getResultList();
        } finally {
            em.close();
        }
    }
}