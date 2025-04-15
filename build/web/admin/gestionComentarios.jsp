<%-- 
    Document   : gestionComentarios
    Created on : 15 abr 2025, 19:31:51
    Author     : pauladominguez
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomeCheck - Gestión de Comentarios</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
                background-color: white;
                color: black;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <header>
            <nav class="logoCabecera">
                LOGO
            </nav>
            <nav class="menuCabecera">
                <ul>
                    <li><a href="Controladores.Usuarios/ControladorBuscarExperiencias">Explorar viviendas</a></li>
                    <li><a href="Controladores.Usuarios/ControladorPublicarVivienda">Publicar propiedad</a></li>
                    <li><a href="buscarExperiencias.html">Cómo funciona</a></li>
                    <li><a href="Controladores.Usuarios/ControladorUsuarios?editar=true">Perfil</a></li>
                    <li><a href="/HomeCheck/ControladorLogin?accion=logout" class="boton-cerrar-sesion">Cerrar Sesión</a></li>
                </ul>
            </nav>
        </header>

        <main>
            <h2>Comentarios</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Comentario</th>
                        <th>Autor</th>
                        <th>Vivienda</th>
                        <th>Fecha</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comentario" items="${requestScope.comentarios}">
                        <tr>
                            <td>${comentario.id}</td>
                            <td>${comentario.comentario}</td>
                            <td>${comentario.autor.nombre}</td>
                            <td>${comentario.vivienda.direccion}</td> <!-- Asegúrate de que `direccion` sea un atributo de `Vivienda` -->
                            <td>${comentario.fecha}</td>
                            <td>
                                <form action="Controladores.Admin/ControladorGestionComentarios" method="get">
                                    <input type="hidden" name="id" value="${comentario.id}">
                                    <button type="submit">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </body>
</html>
