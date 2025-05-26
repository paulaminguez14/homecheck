/*
 * ControladorListarPisosPropios
 */
package Controladores.Usuarios;

import Modelo.Entidades.Usuario;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioVivienda;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pauladominguez
 */
@WebServlet(name = "ControladorListarPisosPropios", urlPatterns = {"/Controladores.Usuarios/ControladorListarPisosPropios"})
public class ControladorListarPisosPropios extends HttpServlet {

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

        HttpSession sesion = request.getSession(false);
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        List<Vivienda> viviendas = servicioVivienda.findViviendaEntities();
        List<Vivienda> viviendasPropias = viviendas.stream()
                .filter(v -> v.getCasero() != null)
                .filter(v -> v.getCasero().getId().equals(usuario.getId()))
                .collect(Collectors.toList());

        request.setAttribute("viviendas", viviendasPropias);
        emf.close();

        getServletContext().getRequestDispatcher("/usuarios/listarPisosPropios.jsp").forward(request, response);
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
    }// </editor-fold>

}
