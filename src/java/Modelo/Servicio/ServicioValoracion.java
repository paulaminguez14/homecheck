/*
 * Clase ServicioValoracion
 */
package Modelo.Servicio;

import Modelo.Entidades.Valoracion;
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
public class ServicioValoracion implements Serializable {

    private EntityManagerFactory emf = null;

    public ServicioValoracion(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Valoracion valoracion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(valoracion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Valoracion valoracion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            valoracion = em.merge(valoracion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Long id = valoracion.getId();
            if (findValoracion(id) == null) {
                throw new NonexistentEntityException("La valoración con id " + id + " no existe.");
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
            Valoracion valoracion;
            try {
                valoracion = em.getReference(Valoracion.class, id);
                valoracion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("La valoración con id " + id + " no existe.", enfe);
            }
            em.remove(valoracion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Valoracion> findValoracionesByPropietarioId(Long propietarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Valoracion v WHERE v.usuario.id = :id", Valoracion.class)
                    .setParameter("id", propietarioId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Valoracion> findValoracionEntities() {
        return findValoracionEntities(true, -1, -1);
    }

    public List<Valoracion> findValoracionesByViviendaId(Long idVivienda) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Valoracion v WHERE v.vivienda.id = :idVivienda", Valoracion.class)
                    .setParameter("idVivienda", idVivienda)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    private List<Valoracion> findValoracionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT v FROM Valoracion v");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Valoracion findValoracion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Valoracion.class, id);
        } finally {
            em.close();
        }
    }

    public List<Valoracion> obtenerValoracionesPorUsuario(Long idUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT v FROM Valoracion v WHERE v.usuario.id = :idUsuario", Valoracion.class);
            query.setParameter("idUsuario", idUsuario);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
