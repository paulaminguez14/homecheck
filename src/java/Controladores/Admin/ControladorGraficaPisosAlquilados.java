/*
 * Gráfica de pisos alquilados por ciudad
 */
package Controladores.Admin;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorGraficaPisosAlquilados", urlPatterns = {"/Controladores.Admin/ControladorGraficaPisosAlquilados"})
public class ControladorGraficaPisosAlquilados extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Recuperar los datos de las viviendas alquiladas
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT v.ciudad, COUNT(v) FROM Vivienda v WHERE v.activo = 0 GROUP BY v.ciudad");
            List<Object[]> resultList = query.getResultList();

            // Generar la cadena CSV para Highcharts
            StringBuilder csvData = new StringBuilder("Ciudad;Viviendas Alquiladas\n");
            for (Object[] result : resultList) {
                String ciudad = (String) result[0];
                Long cantidad = (Long) result[1];
                csvData.append(ciudad).append(";").append(cantidad).append("\n");
            }

            request.setAttribute("csvData", csvData.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        // Enviar la respuesta a la vista
        request.getRequestDispatcher("/admin/graficaPisosAlquilados.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para mostrar gráfico de viviendas alquiladas por ciudad";
    }
}
