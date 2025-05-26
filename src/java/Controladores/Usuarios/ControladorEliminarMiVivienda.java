/*
 * Controlador Eliminar en Cascada
 */
package Controladores.Usuarios;

import Modelo.Entidades.Opinion;
import Modelo.Entidades.Valoracion;
import Modelo.Servicio.ServicioOpinion;
import Modelo.Servicio.ServicioValoracion;
import Modelo.Servicio.ServicioVivienda;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pauladominguez
 */
@WebServlet(name = "ControladorEliminarMiVivienda", urlPatterns = {"/Controladores.Usuarios/ControladorEliminarMiVivienda"})
public class ControladorEliminarMiVivienda extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String idParam = request.getParameter("idVivienda");

        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("error", "No se ha recibido el ID de la vivienda.");
            request.getRequestDispatcher("/usuarios/editarMiVivienda.jsp").forward(request, response);
            return;
        }

        Long idVivienda = Long.parseLong(idParam);

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("HomeCheckPU");

        ServicioValoracion servicioValoracion = new ServicioValoracion(emf);
        ServicioOpinion servicioOpinion = new ServicioOpinion(emf);
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        try {
            // Eliminar valoraciones
            List<Valoracion> valoraciones = servicioValoracion.findValoracionesByViviendaId(idVivienda);
            for (Valoracion v : valoraciones) {
                System.out.println("→ ID de vivienda recibido: " + idVivienda);
                System.out.println("→ Eliminando valoraciones...");
                servicioValoracion.destroy(v.getId());
            }

            // Eliminar opiniones
            List<Opinion> opiniones = servicioOpinion.findComentariosByViviendaId(idVivienda);
            for (Opinion o : opiniones) {
                System.out.println("→ ID de vivienda recibido: " + idVivienda);
                System.out.println("→ Eliminando opiniones...");
                servicioOpinion.destroy(o.getId());
            }

            // Eliminar vivienda
            System.out.println("→ ID de vivienda recibido: " + idVivienda);
            System.out.println("→ Eliminando vivienda...");

            servicioVivienda.destroy(idVivienda);

            request.setAttribute("mensaje", "La vivienda se ha eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("→ ID de vivienda recibido: " + idVivienda);
            System.out.println("→ Eliminando valoraciones...");
            System.out.println("→ Error al eliminar la vivienda: ");
            e.printStackTrace(); // Imprime la excepción completa
            request.setAttribute("error", "Error al eliminar la vivienda: " + e.getMessage());

            request.setAttribute("error", "Error al eliminar la vivienda: " + e.getMessage());
        }

        response.sendRedirect("ControladorListarPisosPropios");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
