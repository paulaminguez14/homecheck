/*
 * ServicioCiudad
 */
package Modelo.Servicio;

/**
 *
 * @author pauladominguez
 */
import Modelo.Entidades.Ciudades;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ServicioCiudad implements Serializable {

    private EntityManagerFactory emf;

    public ServicioCiudad(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Ciudades> obtenerCiudadesPorProvincia(int idProvincia) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Ciudades c WHERE c.provincia.id = :idProvincia", Ciudades.class)
                    .setParameter("idProvincia", idProvincia)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ciudades> obtenerCiudades() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Ciudades c", Ciudades.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Ciudades findCiudad(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Ciudades.class, id);
    }

}
