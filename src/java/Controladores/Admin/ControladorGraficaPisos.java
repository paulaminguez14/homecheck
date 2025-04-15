/*
 * Grafica de pisos publicados por ciudad
 */
package Controladores.Admin;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pauladominguez
 */
@WebServlet(name = "ControladorGraficaPisos", urlPatterns = {"/Controladores.Admin/ControladorGraficaPisos"})
public class ControladorGraficaPisos extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
    EntityManager em = emf.createEntityManager();

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

        Map<String, Integer> pisosPorCiudad = new LinkedHashMap<>();

        List<Object[]> resultados = em.createQuery(
                "SELECT p.ciudad, COUNT(p) FROM Vivienda p GROUP BY p.ciudad", Object[].class)
                .getResultList();

        for (Object[] fila : resultados) {
            String ciudad = (String) fila[0];
            Long total = (Long) fila[1];
            pisosPorCiudad.put(ciudad, total.intValue());
        }

        em.close();
        emf.close();

        request.setAttribute("datosPisos", pisosPorCiudad);
        request.getRequestDispatcher("/admin/graficaPisos.jsp").forward(request, response);
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
