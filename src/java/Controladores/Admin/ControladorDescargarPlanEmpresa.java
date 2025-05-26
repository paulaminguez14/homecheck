/*
 * ControladorDescargarPlanEmpresa
 */
package Controladores.Admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pauladominguez
 */
@WebServlet(name = "ControladorDescargarPlanEmpresa", urlPatterns = {"/Controladores.Admin/ControladorDescargarPlanEmpresa"})
public class ControladorDescargarPlanEmpresa extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String filePath = getServletContext().getRealPath("/descarga/Plan_de_empresa-Paula_Dominguez_Bernabe.docx");
        File downloadFile = new File(filePath);

        if (!downloadFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        FileInputStream inStream = new FileInputStream(downloadFile);

        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setContentLength((int) downloadFile.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"Plan_de_empresa-Paula_Dominguez_Bernabe.docx\"");

        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();

    }
}
