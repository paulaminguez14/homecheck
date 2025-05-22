/*
 * ControladorBuscarExperiencias
 */
package Controladores.Usuarios;

import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioVivienda;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
@WebServlet(name = "ControladorBuscarExperiencias", urlPatterns = {"/Controladores.Usuarios/ControladorBuscarExperiencias"})
public class ControladorBuscarExperiencias extends HttpServlet {

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        String idStr = request.getParameter("idImagen");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                Vivienda vivienda = servicioVivienda.findVivienda(id);
                if (vivienda != null && vivienda.getFoto() != null) {
                    String rutaImagen = getServletContext().getRealPath("/imagenesPisos/" + vivienda.getFoto());
                    File imagenFile = new File(rutaImagen);
                    if (imagenFile.exists()) {
                        response.setContentType("image/jpeg");
                        response.getOutputStream().write(Files.readAllBytes(imagenFile.toPath()));
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
                    }
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID no válido");
            } finally {
                emf.close();
            }
            return;
        }

        // Lógica de filtrado
        // Lógica de filtrado
        final String ciudadParam = request.getParameter("ciudad");
        final String precioStr = request.getParameter("precioMax");
        Double tempPrecioMax = null;

        if (precioStr != null && !precioStr.isEmpty()) {
            try {
                tempPrecioMax = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                tempPrecioMax = null; // Ignorar si el formato es incorrecto
            }
        }

        final Double precioMax = tempPrecioMax;

        List<Vivienda> viviendas = servicioVivienda.findViviendaEntities();

        viviendas.removeIf(v -> !v.isActivo());

        if (ciudadParam != null && !ciudadParam.isEmpty()) {
            final String ciudadFiltrada = ciudadParam.toLowerCase();
            viviendas.removeIf(v -> v.getCiudad() == null || !v.getCiudad().toLowerCase().contains(ciudadFiltrada));
        }

        if (precioMax != null) {
            viviendas.removeIf(v -> v.getPrecio() > precioMax);
        }

        request.setAttribute("viviendas", viviendas);
        emf.close();

        getServletContext().getRequestDispatcher("/usuarios/buscarExperiencias.jsp").forward(request, response);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
