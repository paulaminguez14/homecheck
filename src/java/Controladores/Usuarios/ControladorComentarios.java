/*
 * ControladorComentarios
 */
package Controladores.Usuarios;

import Modelo.Entidades.Opinion;
import Modelo.Entidades.Valoracion;
import Modelo.Servicio.ServicioOpinion;
import Modelo.Servicio.ServicioValoracion;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
@WebServlet(name = "ControladorComentarios", urlPatterns = {"/Controladores.Usuarios/ControladorComentarios"})
public class ControladorComentarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener idUsuario desde los parámetros (puede ser la sesión o la URL)
        Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");

        // Servicios de Opinión y Valoración
        ServicioOpinion servicioOpinion = new ServicioOpinion(emf);
        ServicioValoracion servicioValoracion = new ServicioValoracion(emf);

        try {
            // Obtener las opiniones y valoraciones
            List<Opinion> opiniones = servicioOpinion.obtenerOpinionesPorUsuario(idUsuario);
            List<Valoracion> valoraciones = servicioValoracion.obtenerValoracionesPorUsuario(idUsuario);

            // Establecer los resultados como atributos de la solicitud
            request.setAttribute("opiniones", opiniones);
            request.setAttribute("valoraciones", valoraciones);

            // Redirigir a la vista donde se muestran los comentarios y valoraciones
            getServletContext().getRequestDispatcher("/usuarios/comentarios.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/HomeCheck/ControladorLogin");
        } finally {
            emf.close();
        }
    }
}