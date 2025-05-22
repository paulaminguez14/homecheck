/*
 * ControladorLogin
 */
package Controladores;

import Modelo.Entidades.Usuario;
import Modelo.Servicio.ServicioUsuario;
import java.io.IOException;
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
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {

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

        String accion = request.getParameter("accion");

        if ("logout".equals(accion)) {
            HttpSession sesion = request.getSession(false); // Obtener la sesión actual sin crear una nueva
            if (sesion != null) {
                sesion.invalidate(); // Cerrar la sesión
            }
            response.sendRedirect(request.getContextPath() + "/ControladorLogin");
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
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

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String error = "";

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            error = "El e-mail y la contraseña son obligatorios";
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
            ServicioUsuario su = new ServicioUsuario(emf);
            Usuario usu = su.validarUsuario(email, password);
            if (usu != null) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usu);
                emf.close();

                if (usu.getRol().equals("USUARIO")) {
                    request.getSession().setAttribute("usuario", usu);
                    response.sendRedirect("ControladorInicio");
                } else {
                    request.getSession().setAttribute("usuario", usu);
                    response.sendRedirect("/HomeCheck/Controladores.Admin/ControladorInicioAdmin");
                }
                return;
            } else {
                error = "E-mail o contraseña incorrecta";
            }
            emf.close();
        }

        // Guarda el error solo en request (NO en sesión)
        request.setAttribute("error", error);
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
