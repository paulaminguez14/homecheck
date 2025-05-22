/*
 * Clase ServicioUsuario
 */
package Modelo.Servicio;

import Modelo.Entidades.Usuario;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author pauladominguez
 */
public class ServicioUsuario implements Serializable {

    private EntityManagerFactory emf = null;

    public ServicioUsuario(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Long id = usuario.getId();
            if (findUsuario(id) == null) {
                throw new NonexistentEntityException("El usuario con id " + id + " no existe.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El usuario con id " + id + " no existe.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario validarUsuario(String email, String password) {
        List<Usuario> usuarios = findUsuarioEntities();
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public boolean eliminarUsuarioConDependencias(Long idUsuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Usuario usuario = em.find(Usuario.class, idUsuario);

            if (usuario == null) {
                return false;
            }

            // 1. Eliminar comentarios del usuario
            em.createQuery("DELETE FROM Opinion c WHERE c.usuario.id = :id")
                    .setParameter("id", idUsuario)
                    .executeUpdate();

            // 2. Eliminar valoraciones del usuario
            em.createQuery("DELETE FROM Valoracion v WHERE v.usuario.id = :id")
                    .setParameter("id", idUsuario)
                    .executeUpdate();

            // 3. Eliminar viviendas del usuario
            List<Vivienda> viviendas = em.createQuery(
                    "SELECT v FROM Vivienda v WHERE v.casero.id = :id", Vivienda.class)
                    .setParameter("id", idUsuario)
                    .getResultList();

            for (Vivienda v : viviendas) {
                // 3.1 Eliminar comentarios asociados a viviendas
                em.createQuery("DELETE FROM Opinion c WHERE c.vivienda.id = :viviendaId")
                        .setParameter("viviendaId", v.getId())
                        .executeUpdate();

                // 3.2 Eliminar valoraciones asociadas a viviendas
                em.createQuery("DELETE FROM Valoracion val WHERE val.vivienda.id = :viviendaId")
                        .setParameter("viviendaId", v.getId())
                        .executeUpdate();

                // 3.3 Eliminar fotos asociadas si tienes esa entidad
                em.createQuery("DELETE FROM FotoVivienda f WHERE f.vivienda.id = :viviendaId")
                        .setParameter("viviendaId", v.getId())
                        .executeUpdate();

                // 3.4 Eliminar la vivienda
                em.remove(em.contains(v) ? v : em.merge(v));
            }

            // 4. Finalmente, eliminar el usuario
            em.remove(em.contains(usuario) ? usuario : em.merge(usuario));

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarUsuarioPorId(Long idUsuario) {
        try {
            destroy(idUsuario);
        } catch (NonexistentEntityException e) {
            System.out.println("Usuario no encontrado: " + e.getMessage());
        }
    }

    public boolean eliminarUsuarioPorIdAdmin(Long idUsuario) {
        EntityManager em = getEntityManager();
        try {
            Usuario usuario = em.find(Usuario.class, idUsuario);
            if (usuario != null) {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Usuario> buscarUsuariosPorNombreOEmail(String filtro) {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE :filtro OR LOWER(u.email) LIKE :filtro OR LOWER(u.apellidos) LIKE :filtro", Usuario.class)
                .setParameter("filtro", "%" + filtro.toLowerCase() + "%")
                .getResultList();
    }
}
