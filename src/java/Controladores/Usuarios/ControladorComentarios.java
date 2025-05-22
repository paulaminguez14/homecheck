/*
 * ControladorComentarios
 */
package Controladores.Usuarios;

import Modelo.Entidades.Usuario;
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

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String idParam = request.getParameter("idUsuario");
        Long idUsuario = null;

        try {
            if (idParam != null) {
                idUsuario = Long.parseLong(idParam);
            } else {
                // Intenta obtener el usuario desde la sesión si no está en la URL
                Object usuarioSesion = request.getSession().getAttribute("usuario");
                if (usuarioSesion != null) {
                    idUsuario = ((Usuario) usuarioSesion).getId();
                } else {
                    response.sendRedirect("/HomeCheck/ControladorLogin");
                    return;
                }
            }

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
            ServicioOpinion servicioOpinion = new ServicioOpinion(emf);
            ServicioValoracion servicioValoracion = new ServicioValoracion(emf);

            // Obtener valoraciones del casero (propietario)
            List<Modelo.Entidades.Valoracion> valoracionesCasero = servicioValoracion.findValoracionesByPropietarioId(idUsuario);
            // Obtener comentarios del propietario(casero)
            List<Modelo.Entidades.Opinion> comentariosCasero = servicioOpinion.findComentariosByPropietarioId(idUsuario);

            request.setAttribute("comentarios", comentariosCasero);
            request.setAttribute("valoraciones", valoracionesCasero);

            emf.close();
            getServletContext().getRequestDispatcher("/usuarios/comentarios.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("/HomeCheck/ControladorLogin");
        }
    }
}
