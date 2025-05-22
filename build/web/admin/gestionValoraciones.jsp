<%-- 
    Document   : gestionValoraciones
    Created on : 15 abr 2025, 19:28:22
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>HomeCheck - Gestión de Valoraciones</title>
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
            <h1 style="text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">Listado de Valoraciones</h1>
            <a class="volverAdmin" href="ControladorInicioAdmin" style="margin-bottom: 20px; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">Volver</a>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Usuario</th>
                        <th>Autor</th>
                        <th>Vivienda</th>
                        <th>Puntuación</th>
                        <th>Fecha</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="valoracion" items="${valoraciones}">
                        <tr>
                            <td>${valoracion.id}</td>
                            <td>${valoracion.usuario.nombre}</td>
                            <td>${valoracion.autor.nombre}</td>
                            <td>${valoracion.vivienda.direccion}</td>
                            <td>${valoracion.puntuacion}</td>
                            <td>${valoracion.fecha}</td>
                            <td style="display: flex; align-items: center; justify-content: center;">
                                <!-- Botón Eliminar -->
                                <a href="${pageContext.request.contextPath}/Controladores.Admin/ControladorGestionValoraciones?accion=eliminar&id=${valoracion.id}"
                                   style="text-decoration: none; color: black; font-weight: bold;"
                                   onclick="return confirm('¿Seguro que quieres eliminar esta valoración?');">Eliminar</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </body>
</html>