/*
 * Controlador Ver Más
 */
package Controladores.Usuarios;

import Modelo.Entidades.Valoracion;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioValoracion;
import Modelo.Servicio.ServicioVivienda;
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
@WebServlet(name = "ControladorVerMas", urlPatterns = {"/Controladores.Usuarios/ControladorVerMas"})
public class ControladorVerMas extends HttpServlet {

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

        // Obtener el ID de la vivienda
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
                ServicioVivienda servicioVivienda = new ServicioVivienda(emf);
                Vivienda vivienda = servicioVivienda.findVivienda(id);

                if (vivienda != null) {
                    // Obtener la lista de valoraciones del piso
                    ServicioValoracion servicioValoracion = new ServicioValoracion(emf);
                    List<Valoracion> valoraciones = servicioValoracion.findValoracionesByViviendaId(id);
                    // Obtener valoraciones del casero (propietario)
                    List<Valoracion> valoracionesCasero = servicioValoracion.findValoracionesByPropietarioId(vivienda.getCasero().getId());
                    request.setAttribute("valoracionesCasero", valoracionesCasero);
                    // Obtener comentarios de la vivienda
                    Modelo.Servicio.ServicioOpinion servicioComentario = new Modelo.Servicio.ServicioOpinion(emf);
                    List<Modelo.Entidades.Opinion> comentarios = servicioComentario.findComentariosByViviendaId(id);
                    // Obtener comentarios del propietario (casero)
                    Long idCasero = vivienda.getCasero().getId();
                    List<Modelo.Entidades.Opinion> comentariosCasero = servicioComentario.findComentariosByPropietarioId(idCasero);

                    request.setAttribute("comentariosCasero", comentariosCasero);
                    request.setAttribute("vivienda", vivienda);
                    request.setAttribute("valoraciones", valoraciones);
                    request.setAttribute("valoracionesCasero", valoracionesCasero);
                    request.setAttribute("comentarios", comentarios);
                } else {
                    // Si no se encuentra la vivienda, redirigir a la página de error o no encontrado
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vivienda no encontrada");
                    return;
                }

                emf.close();

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID no válido");
                return;
            }
        }

        // Redirigir a la vista detallada
        getServletContext().getRequestDispatcher("/usuarios/detalleVivienda.jsp").forward(request, response);
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
