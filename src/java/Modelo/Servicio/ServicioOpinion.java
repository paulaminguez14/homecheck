/*
 * Clase ServicioOpinion
 */
package Modelo.Servicio;

import Modelo.Entidades.Opinion;
import Modelo.Servicio.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 *
 * @author pauladominguez
 */
public class ServicioOpinion implements Serializable {

    private EntityManagerFactory emf = null;

    public ServicioOpinion(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opinion opinion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opinion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opinion opinion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opinion = em.merge(opinion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Long id = opinion.getId();
            if (findOpinion(id) == null) {
                throw new NonexistentEntityException("La opinión con id " + id + " no existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opinion opinion;
            try {
                opinion = em.getReference(Opinion.class, id);
                opinion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("La opinión con id " + id + " no existe.", enfe);
            }
            em.remove(opinion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opinion> findComentariosByViviendaId(Long viviendaId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Opinion c WHERE c.vivienda.id = :id", Opinion.class)
                    .setParameter("id", viviendaId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Opinion> findComentariosByPropietarioId(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Opinion c WHERE c.usuario.id = :id", Opinion.class)
                    .setParameter("id", usuarioId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Opinion> findOpinionEntities() {
        return findOpinionEntities(true, -1, -1);
    }

    private List<Opinion> findOpinionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o FROM Opinion o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Opinion findOpinion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opinion.class, id);
        } finally {
            em.close();
        }
    }

    public List<Opinion> obtenerOpinionesPorUsuario(Long idUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT o FROM Opinion o WHERE o.usuario.id = :idUsuario", Opinion.class);
            query.setParameter("idUsuario", idUsuario);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
