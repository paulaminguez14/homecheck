/*
 * Controlador para Valoraciones
 */
package Controladores.Usuarios;

import Modelo.Entidades.Opinion;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.Valoracion;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioOpinion;
import Modelo.Servicio.ServicioUsuario;
import Modelo.Servicio.ServicioValoracion;
import Modelo.Servicio.ServicioVivienda;
import java.io.IOException;
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
@WebServlet(name = "ControladorValoraciones", urlPatterns = {"/Controladores.Usuarios/ControladorValoraciones"})
public class ControladorValoraciones extends HttpServlet {

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

        String tipo = request.getParameter("tipo");
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        Long id = Long.parseLong(idStr);

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioUsuario servicioUsuario = new ServicioUsuario(emf);
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        if ("piso".equalsIgnoreCase(tipo)) {
            Vivienda vivienda = servicioVivienda.findVivienda(id);
            if (vivienda != null) {
                request.setAttribute("vivienda", vivienda);
                request.getRequestDispatcher("/usuarios/valorarPiso.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }

        } else if ("casero".equalsIgnoreCase(tipo)) {
            Usuario casero = servicioUsuario.findUsuario(id);
            if (casero != null) {
                request.setAttribute("casero", casero);
                request.getRequestDispatcher("/usuarios/valorarPropietario.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }

        } else {
            response.sendRedirect("error.jsp");
        }

        emf.close();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);
        ServicioUsuario servicioUsuario = new ServicioUsuario(emf);
        ServicioValoracion servicioValoracion = new ServicioValoracion(emf);
        ServicioOpinion servicioOpinion = new ServicioOpinion(emf);

        String tipo = request.getParameter("tipo");

        if (!"piso".equalsIgnoreCase(tipo) && !"casero".equalsIgnoreCase(tipo)) {
            request.setAttribute("error", "Tipo inválido.");
            response.sendRedirect("error.jsp");
            emf.close();
            return;
        }

        String idPisoStr = request.getParameter("idPiso");
        String puntuacionStr = request.getParameter("puntuacion");
        String comentario = request.getParameter("comentario");

        if (idPisoStr == null || puntuacionStr == null || comentario == null
                || idPisoStr.isEmpty() || puntuacionStr.isEmpty() || comentario.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("/usuarios/valorarPiso.jsp").forward(request, response);
            emf.close();
            return;
        }

        Long idPiso = Long.parseLong(idPisoStr);
        int puntuacion = Integer.parseInt(puntuacionStr);

        Usuario autor = (Usuario) request.getSession().getAttribute("usuario");
        System.out.println("¿El usuario está logueado?: " + autor);
        if (autor == null) {
            emf.close();
            response.sendRedirect("ControladorVerMas?id=" + idPiso);
            return;
        }

        try {
            Vivienda vivienda = servicioVivienda.findVivienda(idPiso);
            if (vivienda == null) {
                request.setAttribute("error", "La vivienda no existe.");
                request.getRequestDispatcher("/usuarios/valorarPiso.jsp").forward(request, response);
                return;
            }

            autor = servicioUsuario.findUsuario(autor.getId());
            Usuario casero = servicioUsuario.findUsuario(vivienda.getCasero().getId());

            Valoracion valoracion = new Valoracion();
            valoracion.setPuntuacion(puntuacion);
            valoracion.setFecha(new java.util.Date());
            valoracion.setAutor(autor);
            valoracion.setVivienda(vivienda);
            valoracion.setUsuario(null);

            Opinion opinion = new Opinion();
            opinion.setComentario(comentario);
            opinion.setFecha(new java.util.Date());
            opinion.setAutor(autor);
            opinion.setVivienda(vivienda);
            opinion.setUsuario(null);

            try {
                servicioValoracion.create(valoracion);
                System.out.println("Valoración guardada: " + valoracion);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                servicioOpinion.create(opinion);
                System.out.println("Opinión guardada: " + opinion);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Autor: " + autor);
            System.out.println("Casero (usuario): " + casero);
            System.out.println("Vivienda: " + vivienda);

            request.setAttribute("exito", "Tu valoración y opinión se han guardado correctamente.");
            response.sendRedirect("ControladorVerMas?id=" + vivienda.getId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al guardar la valoración. Intenta de nuevo.");
            request.getRequestDispatcher("/usuarios/valorarPiso.jsp").forward(request, response);
        } finally {
            emf.close();
        }
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
