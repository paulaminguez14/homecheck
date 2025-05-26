/*
 * ControladorCiudadesPorProvincia
 */
package Controladores.Usuarios;

import Modelo.Entidades.Ciudades;
import Modelo.Servicio.ServicioCiudad;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/obtenerCiudades")
public class CiudadesPorProvinciaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Recuperar el EntityManagerFactory desde el contexto
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        // Crear una instancia del servicio
        ServicioCiudad servicioCiudad = new ServicioCiudad(emf);

        // Obtener las ciudades
        int idProvincia = Integer.parseInt(request.getParameter("idProvincia"));
        List<Ciudades> ciudades = servicioCiudad.obtenerCiudadesPorProvincia(idProvincia);

        // Generar la respuesta JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("[");
        for (int i = 0; i < ciudades.size(); i++) {
            Ciudades c = ciudades.get(i);
            out.print("{\"id\":" + c.getId() + ",\"nombre\":\"" + c.getNombre() + "\"}");
            if (i < ciudades.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
    }
}
