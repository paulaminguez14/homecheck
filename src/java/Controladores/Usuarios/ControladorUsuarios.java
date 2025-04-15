/*
 * ControladorUsuarios.
 */
package Controladores.Usuarios;

import Modelo.Entidades.Usuario;
import Modelo.Servicio.ServicioUsuario;
import Modelo.Servicio.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/Controladores.Usuarios/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioUsuario su = new ServicioUsuario(emf);
        String vista = "/registrado.jsp";

        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (request.getParameter("crear") != null) { // Crear Usuario
            vista = "/registro.jsp";
        } else if (usuario != null) { // Editar Usuario desde sesión
            try {
                request.setAttribute("idUsuario", usuario.getId());
                request.setAttribute("nombre", usuario.getNombre());
                request.setAttribute("apellidos", usuario.getApellidos());
                request.setAttribute("email", usuario.getEmail());
                request.setAttribute("password", usuario.getPassword());
                request.setAttribute("password2", usuario.getPassword());
                request.setAttribute("activo", usuario.isActivo());
                request.setAttribute("tipo", usuario.getRol());
                vista = "/registro.jsp";
            } catch (Exception e) {
                sesion.setAttribute("error", "error al cargar el usuario...");
            }
        }

        emf.close();
        getServletContext().getRequestDispatcher(vista).forward(request, response);
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
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String tipo = request.getParameter("tipo");
        String activo = request.getParameter("activo");

        String vista = "/registro.jsp";
        String error = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioUsuario su = new ServicioUsuario(emf);
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (request.getParameter("crear") != null) { // Crear
            try {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellidos(apellidos);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setRol(tipo);
                nuevoUsuario.setActivo(false);

                if (password.equals(password2)) {
                    nuevoUsuario.setPassword(password);
                } else {
                    error = "Las contraseñas no coinciden.";
                }

                if (error.isEmpty()) {
                    su.create(nuevoUsuario);
                    sesion.setAttribute("usuario", nuevoUsuario);
                    response.sendRedirect(request.getContextPath() + "/registrado.jsp");
                    return;
                }

            } catch (Exception e) {
                error = "Error al crear el usuario " + nombre;
            }

        } else if (request.getParameter("editar") != null && usuario != null) { // Editar
            try {
                usuario.setNombre(nombre);
                usuario.setApellidos(apellidos);
                usuario.setEmail(email);
                usuario.setRol(tipo);

                if (password.equals(password2)) {
                    usuario.setPassword(password);
                } else {
                    error = "Las contraseñas no coinciden.";
                }

                if (error.isEmpty()) {
                    su.edit(usuario);
                    sesion.setAttribute("usuario", usuario);
                    response.sendRedirect("/HomeCheck/ControladorInicio");
                    return;
                }
            } catch (Exception ex) {
                error = "Error al editar el usuario.";
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("eliminar") != null) { // Eliminar
            Long id = Long.parseLong(request.getParameter("idUsuario"));
            try {
                su.destroy(id);  // Eliminar usuario
                sesion.invalidate();  // Invalidar sesión
            } catch (NonexistentEntityException e) {
                error = "El usuario ya no existe.";
            } catch (Exception e) {
                error = "No se puede eliminar el usuario.";
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, e);
            }

            if (!error.isEmpty()) {
                sesion.setAttribute("error", error);
                response.sendRedirect("/HomeCheck/Controladores.Usuarios/ControladorUsuarios?editar=true");
                return;
            } else {
                response.sendRedirect("/HomeCheck/ControladorLogin");
                return;
            }
        }

        emf.close();
        sesion.removeAttribute("error"); // Elimina el error anterior
        request.setAttribute("error", error);
        getServletContext().getRequestDispatcher(vista).forward(request, response);
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
