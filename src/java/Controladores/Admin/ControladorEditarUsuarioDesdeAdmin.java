/*
 * ControladorEditarUsuarioDesdeAdmin
 */
package Controladores.Admin;

import Controladores.Usuarios.ControladorUsuarios;
import Modelo.Entidades.Usuario;
import Modelo.Servicio.ServicioUsuario;
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
@WebServlet(name = "ControladorEditarUsuarioDesdeAdmin", urlPatterns = {"/Controladores.Admin/ControladorEditarUsuarioDesdeAdmin"})
public class ControladorEditarUsuarioDesdeAdmin extends HttpServlet {

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
        ServicioUsuario su = new ServicioUsuario(emf);
        String vista = "/registrado.jsp";

        HttpSession sesion = request.getSession();
        Usuario usuarioSesion = (Usuario) sesion.getAttribute("usuario");

        try {
            String idParam = request.getParameter("id");

            if (idParam != null) {
                Long id = Long.parseLong(idParam);

                // Validar si el usuario puede editar
                if (usuarioSesion == null || (!usuarioSesion.getRol().equals("ADMIN") && !usuarioSesion.getId().equals(id))) {
                    response.sendRedirect(request.getContextPath() + "/ControladorLogin");
                    return;
                }

                Usuario usuarioEditar = su.findUsuario(id);

                if (usuarioEditar != null) {
                    request.setAttribute("idUsuario", usuarioEditar.getId());
                    request.setAttribute("nombre", usuarioEditar.getNombre());
                    request.setAttribute("apellidos", usuarioEditar.getApellidos());
                    request.setAttribute("email", usuarioEditar.getEmail());
                    request.setAttribute("password", "");
                    request.setAttribute("password2", "");
                    request.setAttribute("activo", usuarioEditar.isActivo());
                    request.setAttribute("tipo", usuarioEditar.getRol());
                    request.setAttribute("telefono", usuarioEditar.getTelefono());
                    vista = "/admin/editarUsuarioDesdeAdmin.jsp";
                } else {
                    sesion.setAttribute("error", "Usuario no encontrado.");
                    response.sendRedirect(request.getContextPath() + "/Controladores.Admin/ControladorGestionUsuarios");
                    return;
                }
            }

        } catch (Exception e) {
            sesion.setAttribute("error", "Error al cargar el usuario: " + e.getMessage());
        } finally {
            emf.close();
        }

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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession();
        Usuario usuarioSesion = (Usuario) sesion.getAttribute("usuario");
        String vista = "/admin/editarUsuarioDesdeAdmin.jsp";
        String error = "";

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String tipo = request.getParameter("tipo");
        String activo = request.getParameter("activo");
        String telefono = request.getParameter("telefono");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioUsuario su = new ServicioUsuario(emf);

        boolean editando = request.getParameter("editar") != null;

        try {
            if (editando) {
                // Editar usuario
                Long idUsuarioEditar = Long.parseLong(request.getParameter("idUsuario"));
                Usuario usuarioEditar = su.findUsuario(idUsuarioEditar);

                // Validación de acceso
                if (usuarioSesion == null
                        || (!usuarioSesion.getRol().equals("ADMIN") && !usuarioSesion.getId().equals(usuarioEditar.getId()))) {
                    response.sendRedirect(request.getContextPath() + "/ControladorLogin");
                    return;
                }

                usuarioEditar.setNombre(nombre);
                usuarioEditar.setApellidos(apellidos);
                usuarioEditar.setEmail(email);
                usuarioEditar.setRol(tipo);
                usuarioEditar.setActivo("on".equalsIgnoreCase(activo));
                usuarioEditar.setTelefono(telefono);

                if (password != null && !password.isEmpty()) {
                    if (password.equals(password2)) {
                        String hashed = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
                        usuarioEditar.setPassword(hashed);
                    } else {
                        error = "Las contraseñas no coinciden.";
                    }
                }

                if (error.isEmpty()) {
                    su.edit(usuarioEditar);
                    if (usuarioSesion.getId().equals(usuarioEditar.getId())) {
                        sesion.setAttribute("usuario", usuarioEditar);
                    }
                    request.setAttribute("mensaje", "Usuario actualizado correctamente.");
                }

                // Siempre pasar los valores al formulario (aunque haya error)
                request.setAttribute("idUsuario", usuarioEditar.getId());
                request.setAttribute("nombre", nombre);
                request.setAttribute("apellidos", apellidos);
                request.setAttribute("email", email);
                request.setAttribute("password", "");
                request.setAttribute("password2", "");
                request.setAttribute("activo", "on".equalsIgnoreCase(activo));
                request.setAttribute("tipo", tipo);
                request.setAttribute("telefono", telefono);

            }

        } catch (Exception ex) {
            error = "Ocurrió un error al procesar el formulario.";
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            emf.close();
        }

        // Si llegamos aquí, hubo error o simplemente queremos mostrar el formulario
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
        }

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
