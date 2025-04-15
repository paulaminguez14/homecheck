package Controladores.Usuarios;

import Modelo.Entidades.Usuario;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioVivienda;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "ControladorPublicarVivienda", urlPatterns = {"/Controladores.Usuarios/ControladorPublicarVivienda"})
@MultipartConfig // Necesario para manejar archivos
public class ControladorPublicarVivienda extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("ControladorLogin");
            return;
        }

        request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        Vivienda vivienda = new Vivienda();
        vivienda.setDireccion(request.getParameter("direccion"));

        String precioStr = request.getParameter("precio");
        vivienda.setPrecio((precioStr != null && !precioStr.isEmpty()) ? Double.parseDouble(precioStr) : 0.0);

        vivienda.setCiudad(request.getParameter("ciudad"));
        vivienda.setProvincia(request.getParameter("provincia"));
        vivienda.setCodigoPostal(request.getParameter("codigoPostal"));

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null) {
            vivienda.setCasero(usuario);
        } else {
            request.setAttribute("error", "No estás logueado. Por favor, inicia sesión.");
            request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
            return;
        }

        String habitacionesStr = request.getParameter("habitaciones");
        vivienda.setHabitaciones((habitacionesStr != null && !habitacionesStr.isEmpty()) ? Integer.parseInt(habitacionesStr) : 0);

        String banosStr = request.getParameter("banos");
        vivienda.setBaños((banosStr != null && !banosStr.isEmpty()) ? Integer.parseInt(banosStr) : 0);

        String amuebladoStr = request.getParameter("amueblado");
        vivienda.setAmueblado(amuebladoStr != null && !amuebladoStr.isEmpty());

        try {
            // 1. Obtener la imagen en bytes
            Part filePart = request.getPart("foto"); // input de la imagen
            if (filePart != null && filePart.getSize() > 0) {
                InputStream input = filePart.getInputStream();
                byte[] fotoBytes = input.readAllBytes(); // Convertir a byte[]

                vivienda.setFoto(fotoBytes); // Guardar en la BD
            }

            // 2. Guardar la vivienda en la BD con la imagen en byte[]
            servicioVivienda.create(vivienda);

            request.setAttribute("exito", "Vivienda publicada correctamente.");
            request.getRequestDispatcher("/usuarios/viviendaPublicada.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Hubo un error al publicar la vivienda. Por favor, intente nuevamente.");
            request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
        } finally {
            emf.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para publicar vivienda con imagen";
    }
}
