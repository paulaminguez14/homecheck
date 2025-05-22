<%-- 
    Document   : gestionUsuarios
    Created on : 15 abr 2025, 18:59:59
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="Modelo.Entidades.Usuario"%>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>HomeCheck</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                <a href="../ControladorInicio"><img src="../imagenes/logo.png" style="width: 130px;"></a>
            </nav>
            <nav class="menuCabecera">
                <ul>
                    <li>
                        <a href="../Controladores.Usuarios/ControladorBuscarExperiencias">
                            <svg  xmlns="http://www.w3.org/2000/svg"  width="20"  height="20"  
                                  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  
                                  stroke-linecap="round"  stroke-linejoin="round"  
                                  class="icon icon-tabler icons-tabler-outline icon-tabler-home">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M5 12l-2 0l9 -9l9 9l-2 0" />
                            <path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" />
                            <path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" />
                            </svg> 
                            Explorar viviendas
                        </a>
                    </li>
                    <li>
                        <a href="../Controladores.Usuarios/ControladorPublicarVivienda">
                            <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  
                                  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  
                                  stroke-linecap="round"  stroke-linejoin="round"  
                                  class="icon icon-tabler icons-tabler-outline icon-tabler-speakerphone">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M18 8a3 3 0 0 1 0 6" />
                            <path d="M10 8v11a1 1 0 0 1 -1 1h-1a1 1 0 0 1 -1 -1v-5" />
                            <path d="M12 8h0l4.524 -3.77a.9 .9 0 0 1 1.476 .692v12.156a.9 .9 0 0 1 -1.476 .692l-4.524 -3.77h-8a1 1 0 0 1 -1 -1v-4a1 1 0 0 1 1 -1h8" />
                            </svg> 
                            Publicar propiedad
                        </a>
                    </li>
                    <li>
                        <a href="../Controladores.Usuarios/ControladorUsuarios?editar=true">
                            <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  
                                  viewBox="0 0 24 24"  fill="whitesmoke"  
                                  class="icon icon-tabler icons-tabler-filled icon-tabler-user">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" />
                            <path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" />
                            </svg>
                            Ver mi Perfil
                        </a>
                    </li>
                </ul>
            </nav>
        </header>

        <main class="mainGestionUsuarios">
            <h1 style="text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">Listado de Usuarios</h1>
            <a class="volverAdmin" href="ControladorInicioAdmin" style="margin-bottom: 20px; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">Volver</a>
            <form method="get" action="ControladorGestionUsuarios" style="margin-bottom: 20px; justify-content: left;" class="filtroForm">
                <input type="text" name="filtro" placeholder="Buscar por nombre, email..." 
                       value="${param.filtro}" style="padding: 6px; width: 250px; margin-right: 15px;">
                <button type="submit" style="padding: 6px;"class="botonFiltrar">Buscar</button>
            </form>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Rol</th>
                        <th>Activo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.apellidos}</td>
                            <td>${usuario.rol}</td>
                            <td>${usuario.activo ? "Sí" : "No"}</td>
                            <td style="display: flex; align-items: center; justify-content: space-around;">
                                <a style="text-decoration: none; color: black; font-weight: bold;" href="${pageContext.request.contextPath}/Controladores.Admin/ControladorEditarUsuarioDesdeAdmin?id=${usuario.id}">Editar</a>
                                <form action="ControladorGestionUsuarios" method="post" style="display:inline;">
                                    <input type="hidden" name="idUsuario" value="${usuario.id}">
                                    <input type="hidden" name="eliminar" value="true">
                                    <button style="background-color: transparent; border: none; font-size: medium; font-weight: bold; cursor: pointer;" 
                                            type="submit" onclick="return confirm('¿Seguro que quieres eliminar este usuario?');">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
        </main>
    </body>
</html>