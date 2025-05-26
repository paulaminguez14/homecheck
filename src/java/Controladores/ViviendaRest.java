/*
 * Api Rest Vivienda
 */
package Controladores;

import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioVivienda;
import Modelo.Servicio.ViviendaDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(name = "ViviendaRest", urlPatterns = {"/ViviendaRest"})
public class ViviendaRest extends HttpServlet {

    private ServicioVivienda servicioVivienda;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        servicioVivienda = new ServicioVivienda(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new Gson();
        String json;

        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Vivienda vivienda = servicioVivienda.findVivienda(id);
                if (vivienda != null) {
                    ViviendaDTO dto = new ViviendaDTO(vivienda);
                    json = gson.toJson(dto);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
                    json = "{\"error\":\"Vivienda no encontrada\"}";
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                json = "{\"error\":\"ID inválido\"}";
            }
        } else {
            List<Vivienda> viviendas = servicioVivienda.findAll();
            List<ViviendaDTO> dtoList = viviendas.stream()
                    .map(ViviendaDTO::new)
                    .collect(Collectors.toList());
            json = gson.toJson(dtoList);

            if (dtoList.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(json);
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
