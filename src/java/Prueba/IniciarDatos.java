/*
 * Servlet para iniciar los datos en la base de datos.
 */
package Prueba;

import Modelo.Entidades.Opinion;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.Valoracion;
import Modelo.Entidades.Vivienda;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pauladominguez
 */
@WebServlet(name = "IniciarDatos", urlPatterns = {"/IniciarDatos"})
public class IniciarDatos extends HttpServlet {

    private EntityManager em;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Crear EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        em = emf.createEntityManager();

        // Iniciar la transacción
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            // Crear dos usuarios iniciales
            Usuario usuario1 = new Usuario();
            usuario1.setEmail("juan@homecheck.com");
            usuario1.setPassword("1234");
            usuario1.setNombre("Juan");
            usuario1.setApellidos("Pérez");
            usuario1.setRol("USUARIO");
            usuario1.setActivo(true);
            em.persist(usuario1);

            Usuario usuario2 = new Usuario();
            usuario2.setEmail("ana@homecheck.com");
            usuario2.setPassword("1234");
            usuario2.setNombre("Ana");
            usuario2.setApellidos("López");
            usuario2.setRol("USUARIO");
            usuario2.setActivo(true);
            em.persist(usuario2);

            // Crear un nuevo usuario con un piso
            Usuario usuario3 = new Usuario();
            usuario3.setEmail("carlos@homecheck.com");
            usuario3.setPassword("1234");
            usuario3.setNombre("Carlos");
            usuario3.setApellidos("García");
            usuario3.setRol("USUARIO");
            usuario3.setActivo(true);
            em.persist(usuario3);

            // Crear un piso para el nuevo usuario
            Vivienda vivienda = new Vivienda();
            vivienda.setCasero(usuario3);  // El casero es el usuario creado
            vivienda.setDireccion("Calle Ficticia 123");
            vivienda.setPrecio(600.00);
            vivienda.setCiudad("Madrid");
            vivienda.setProvincia("Madrid");
            vivienda.setCodigoPostal("28001");
            vivienda.setHabitaciones(3);
            vivienda.setBaños(2);
            vivienda.setAmueblado(true);
            vivienda.setActivo(true);  // El piso está disponible para alquilar
            vivienda.setFoto(cargarImagenPorDefecto());
            em.persist(vivienda);

            // Crear una valoración de un usuario (usuario1) sobre el piso
            Valoracion valoracion = new Valoracion();
            valoracion.setUsuario(usuario3); // El dueño del piso
            valoracion.setAutor(usuario1); // El autor de la valoración
            valoracion.setPuntuacion(4);  // Valoración de 4 estrellas
            valoracion.setFecha(new Date()); // Fecha actual
            valoracion.setVivienda(vivienda); // Relacionamos con el piso
            em.persist(valoracion);

            // Crear una opinión de un usuario (usuario2) sobre el piso
            Opinion opinion = new Opinion();
            opinion.setUsuario(usuario3); // El dueño del piso
            opinion.setAutor(usuario2);  // El autor de la opinión
            opinion.setComentario("Piso muy bien ubicado y cómodo. Recomendado.");
            opinion.setFecha(new Date());  // Fecha actual
            opinion.setVivienda(vivienda); // Relacionamos con el piso
            em.persist(opinion);

            // Confirmar la transacción
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Si hay un error, revertir los cambios
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        // Responder al cliente
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Iniciar Datos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Se han creado los datos de prueba en la base de datos.</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private byte[] cargarImagenPorDefecto() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("imagenes/default.jpg");
            if (inputStream != null) {
                return inputStream.readAllBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para iniciar los datos en la base de datos.";
    }
}
