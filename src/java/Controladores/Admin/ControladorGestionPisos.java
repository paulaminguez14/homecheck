/*
 * Controlador Gestión de Pisos
 */
package Controladores.Admin;

import Modelo.Servicio.ServicioVivienda;
import Modelo.Servicio.exceptions.NonexistentEntityException;
import Modelo.Entidades.Vivienda;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControladorGestionPisos", urlPatterns = {"/Controladores.Admin/ControladorGestionPisos"})
public class ControladorGestionPisos extends HttpServlet {

    private ServicioVivienda servicioVivienda;

    public ControladorGestionPisos() {
        // Iniciar el EntityManagerFactory y ServicioVivienda
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU"); // Asegúrate de usar el nombre correcto de tu PU
        servicioVivienda = new ServicioVivienda(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Obtener todas las viviendas de la base de datos
        List<Vivienda> viviendas = servicioVivienda.findViviendaEntities();

        // Establecer las viviendas en el request para que estén disponibles en la JSP
        request.setAttribute("viviendas", viviendas);

        // Redirigir a la vista JSP para mostrar las viviendas
        request.getRequestDispatcher("/admin/gestionPisos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        Long viviendaId = Long.parseLong(request.getParameter("id"));

        if ("eliminar".equals(accion)) {
            try {
                // Eliminar la vivienda
                servicioVivienda.destroy(viviendaId);
                response.sendRedirect("ControladorGestionPisos"); // Redirigir para actualizar la lista
            } catch (NonexistentEntityException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "La vivienda no existe");
            }
        } else if ("editar".equals(accion)) {
            // Redirigir a la página de edición, por ejemplo
            response.sendRedirect("ControladorGestionPisos?id=" + viviendaId);
        }
    }
}
