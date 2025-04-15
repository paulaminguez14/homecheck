/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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

        // Obtener el EntityManagerFactory desde el contexto o crear uno nuevo
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioUsuario servicioUsuario = new ServicioUsuario(emf);

        // Eliminar si hay ID
        String idUsuarioStr = request.getParameter("id");
        if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
            try {
                Long idUsuario = Long.parseLong(idUsuarioStr);
                servicioUsuario.eliminarUsuarioPorId(idUsuario);
            } catch (NumberFormatException e) {
                System.out.println("ID de usuario inválido: " + e.getMessage());
            }
        }

        // Obtener la lista actualizada
        List<Usuario> listaUsuarios = servicioUsuario.obtenerTodosLosUsuarios();

        // Añadir la lista como atributo para que la JSP pueda usarla
        request.setAttribute("usuarios", listaUsuarios);

        // Enviar a la vista
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
