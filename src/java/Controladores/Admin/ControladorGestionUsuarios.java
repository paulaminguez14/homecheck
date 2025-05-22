/*
 * ControladorGestionUsuarios
 */
package Controladores.Admin;

import Modelo.Entidades.Usuario;
import Modelo.Servicio.ServicioUsuario;
import java.io.IOException;
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
@WebServlet(name = "ControladorGestionUsuarios", urlPatterns = {"/Controladores.Admin/ControladorGestionUsuarios"})
public class ControladorGestionUsuarios extends HttpServlet {

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioUsuario servicioUsuario = new ServicioUsuario(emf);

        // Eliminar si hay ID
        String idUsuarioStr = request.getParameter("id");
        if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
            try {
                Long idUsuario = Long.parseLong(idUsuarioStr);
                boolean eliminado = servicioUsuario.eliminarUsuarioPorIdAdmin(idUsuario);

                if (!eliminado) {
                    request.getSession().setAttribute("error", "No se pudo eliminar el usuario.");
                    response.sendRedirect("ControladorGestionUsuarios");
                    return;
                }
            } catch (Exception e) {
                request.getSession().setAttribute("error", "Error al eliminar el usuario.");
                response.sendRedirect("ControladorGestionUsuarios");
                return;
            }
        }

        // Manejo del filtro
        String filtro = request.getParameter("filtro");
        List<Usuario> listaUsuarios;

        if (filtro != null && !filtro.trim().isEmpty()) {
            listaUsuarios = servicioUsuario.buscarUsuariosPorNombreOEmail(filtro.trim());
        } else {
            listaUsuarios = servicioUsuario.obtenerTodosLosUsuarios();
        }

        request.setAttribute("usuarios", listaUsuarios);

        if (request.getSession().getAttribute("error") != null) {
            request.setAttribute("error", request.getSession().getAttribute("error"));
            request.getSession().removeAttribute("error");
        }

        request.getRequestDispatcher("/admin/gestionUsuarios.jsp").forward(request, response);
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioUsuario servicioUsuario = new ServicioUsuario(emf);

        String idStr = request.getParameter("idUsuario");
        String eliminar = request.getParameter("eliminar");

        if (eliminar != null && eliminar.equals("true") && idStr != null && !idStr.isEmpty()) {
            try {
                Long id = Long.parseLong(idStr);

                boolean eliminado = servicioUsuario.eliminarUsuarioConDependencias(id);

                if (!eliminado) {
                    request.getSession().setAttribute("error", "No se pudo eliminar el usuario.");
                }

            } catch (Exception e) {
                request.getSession().setAttribute("error", "Error al eliminar el usuario.");
            }
        }

        response.sendRedirect("ControladorGestionUsuarios");
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
