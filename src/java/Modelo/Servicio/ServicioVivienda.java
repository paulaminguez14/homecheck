/*
 * Clase ServicioVivienda
 */
package Modelo.Servicio;

import Modelo.Entidades.Opinion;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.Valoracion;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author pauladominguez
 */
public class ServicioVivienda implements Serializable {

    private EntityManagerFactory emf = null;

    public ServicioVivienda(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vivienda vivienda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vivienda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vivienda vivienda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vivienda = em.merge(vivienda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Long id = vivienda.getId();
            if (findVivienda(id) == null) {
                throw new NonexistentEntityException("La vivienda con id " + id + " no existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        System.out.println("→ Entrando al método destroy() con ID: " + id); // MOVER AQUÍ
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vivienda vivienda;
            try {
                vivienda = em.getReference(Vivienda.class, id);
                vivienda.getId();
            } catch (EntityNotFoundException enfe) {
                System.out.println("⚠️ No se encontró la vivienda con ID: " + id);
                throw new NonexistentEntityException("La vivienda con id " + id + " no existe.", enfe);
            }
            System.out.println("→ ¿Se encontró la vivienda? " + (vivienda != null));
            em.remove(vivienda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vivienda> findViviendaEntities() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Vivienda> query = em.createQuery("SELECT v FROM Vivienda v", Vivienda.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private List<Vivienda> findViviendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT v FROM Vivienda v");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vivienda findVivienda(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vivienda.class, id);
        } finally {
            em.close();
        }
    }

    public List<Vivienda> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vivienda v", Vivienda.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void guardarValoracion(Valoracion valoracion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(valoracion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void guardarOpinion(Opinion opinion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(opinion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public List<Opinion> obtenerOpinionesPorVivienda(Long idVivienda) {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT o FROM Opinion o WHERE o.vivienda.id = :id", Opinion.class)
                .setParameter("id", idVivienda)
                .getResultList();
    }
}
