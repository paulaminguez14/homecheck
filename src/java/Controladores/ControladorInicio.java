/*
 * ControladorInicio
 */
package Controladores;

import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioVivienda;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pauladominguez
 */
@WebServlet(name = "ControladorInicio", urlPatterns = {"/ControladorInicio"})
public class ControladorInicio extends HttpServlet {

    private EntityManagerFactory emf;
    private ServicioVivienda servicioVivienda;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher dispatcher = request.getRequestDispatcher("usuarios/inicio.jsp");
        dispatcher.forward(request, response);
    }

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

        emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        servicioVivienda = new ServicioVivienda(emf);

        String ciudad = request.getParameter("ciudad");
        String precioParam = request.getParameter("precio");

        double precioMinimo;

        try {
            precioMinimo = (precioParam != null && !precioParam.isEmpty())
                    ? Double.parseDouble(precioParam)
                    : 0.0;
        } catch (NumberFormatException e) {
            precioMinimo = 0.0;
        }

        final double precioFinal = precioMinimo;

        // Obtener todas las viviendas desde el servicio
        List<Vivienda> todasLasViviendas = servicioVivienda.findAll();

        // Aplicar filtros
        List<Vivienda> viviendasFiltradas = todasLasViviendas.stream()
                .filter(v -> ciudad == null || ciudad.isEmpty() || v.getCiudad().equalsIgnoreCase(ciudad))
                .filter(v -> v.getPrecio() >= precioFinal)
                .collect(Collectors.toList());

        // Enviar resultados a la vista
        request.setAttribute("viviendas", viviendasFiltradas);
        request.getRequestDispatcher("/usuarios/inicio.jsp").forward(request, response);
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

        processRequest(request, response);
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
