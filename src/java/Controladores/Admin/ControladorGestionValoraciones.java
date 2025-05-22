/*
 * Controlador para gestionar las valoraciones de los usuarios y los pisos
 */
package Controladores.Admin;

import Modelo.Entidades.Valoracion;
import Modelo.Servicio.ServicioValoracion;
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

@WebServlet(name = "ControladorGestionValoraciones", urlPatterns = {"/Controladores.Admin/ControladorGestionValoraciones"})
public class ControladorGestionValoraciones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioValoracion servicioValoracion = new ServicioValoracion(emf);

        String accion = request.getParameter("accion");
        String idValoracionStr = request.getParameter("id");

        // Si se quiere eliminar
        if ("eliminar".equals(accion) && idValoracionStr != null && !idValoracionStr.isEmpty()) {
            try {
                Long idValoracion = Long.parseLong(idValoracionStr);
                servicioValoracion.destroy(idValoracion);
            } catch (NumberFormatException e) {
                System.out.println("ID de valoración inválido: " + e.getMessage());
            } catch (NonexistentEntityException e) {
                System.out.println("La valoración no existe: " + e.getMessage());
            }
        }

        // Obtener lista de valoraciones
        List<Valoracion> listaValoraciones = servicioValoracion.findValoracionEntities();
        request.setAttribute("valoraciones", listaValoraciones);
        request.getRequestDispatcher("/admin/gestionValoraciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestionar las valoraciones";
    }
}
