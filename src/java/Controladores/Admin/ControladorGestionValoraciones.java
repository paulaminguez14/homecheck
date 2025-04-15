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

        // Obtener el EntityManagerFactory y el servicio de valoraciones
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioValoracion servicioValoracion = new ServicioValoracion(emf);

        // Eliminar una valoración si se proporciona un ID
        String idValoracionStr = request.getParameter("id");
        if (idValoracionStr != null && !idValoracionStr.isEmpty()) {
            try {
                Long idValoracion = Long.parseLong(idValoracionStr);
                servicioValoracion.destroy(idValoracion);
            } catch (NumberFormatException e) {
                System.out.println("ID de valoración inválido: " + e.getMessage());
            } catch (NonexistentEntityException e) {
                System.out.println("La valoración no existe: " + e.getMessage());
            }
        }

        // Obtener la lista de valoraciones
        List<Valoracion> listaValoraciones = servicioValoracion.findValoracionEntities();

        // Pasar la lista de valoraciones a la vista
        request.setAttribute("valoraciones", listaValoraciones);

        // Redirigir a la vista
        request.getRequestDispatcher("/admin/gestionValoraciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No es necesario procesar POST en este caso
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestionar las valoraciones";
    }
}
