/*
 * ControladorImagenVivienda
 */
package Controladores.Usuarios;

import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioVivienda;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pauladominguez
 */
@WebServlet("/imagenVivienda")
public class ControladorImagenVivienda extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Long id = Long.parseLong(request.getParameter("id"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);
        Vivienda vivienda = servicioVivienda.findVivienda(id);
        emf.close();

        if (vivienda != null && vivienda.getFoto() != null) {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(vivienda.getFoto());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
