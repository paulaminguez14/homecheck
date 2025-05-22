/*
 * Controlador para gestionar los comentarios de los usuarios y los pisos
 */
package Controladores.Admin;

import Modelo.Entidades.Opinion;
import Modelo.Servicio.ServicioOpinion;
import Modelo.Servicio.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorGestionComentarios", urlPatterns = {"/Controladores.Admin/ControladorGestionComentarios"})
public class ControladorGestionComentarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioOpinion servicioOpinion = new ServicioOpinion(emf);

        String accion = request.getParameter("accion");
        String idComentarioStr = request.getParameter("id");

        // Eliminar solo si accion=eliminar
        if ("eliminar".equals(accion) && idComentarioStr != null && !idComentarioStr.isEmpty()) {
            try {
                Long idComentario = Long.parseLong(idComentarioStr);
                servicioOpinion.destroy(idComentario);
            } catch (NumberFormatException e) {
                System.out.println("ID de comentario inválido: " + e.getMessage());
            } catch (NonexistentEntityException e) {
                System.out.println("El comentario no existe: " + e.getMessage());
            }
        }

        // Obtener la lista de comentarios actualizada
        List<Opinion> listaComentarios = servicioOpinion.findOpinionEntities();
        request.setAttribute("comentarios", listaComentarios);

        request.getRequestDispatcher("/admin/gestionComentarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestionar los comentarios";
    }
}
