<%-- 
    Document   : buscarExperiencias
    Created on : 10 mar 2025, 13:47:46
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-ES">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomeCheck</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <nav class="logoCabecera">
            LOGO
        </nav>
        <nav class="menuCabecera">
            <ul>
                <li>
                    <a href="ControladorBuscarExperiencias"><svg  xmlns="http://www.w3.org/2000/svg"  width="20"  height="20"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-home"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 12l-2 0l9 -9l9 9l-2 0" /><path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" /><path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" /></svg> Explorar viviendas</a>
                </li>
                <li>
                    <a href="ControladorPublicarVivienda"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-speakerphone"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M18 8a3 3 0 0 1 0 6" /><path d="M10 8v11a1 1 0 0 1 -1 1h-1a1 1 0 0 1 -1 -1v-5" /><path d="M12 8h0l4.524 -3.77a.9 .9 0 0 1 1.476 .692v12.156a.9 .9 0 0 1 -1.476 .692l-4.524 -3.77h-8a1 1 0 0 1 -1 -1v-4a1 1 0 0 1 1 -1h8" /></svg> Publicar propiedad</a>
                </li>
                <li>
                    <a href="buscarExperiencias.html"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-question-mark"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M8 8a3.5 3 0 0 1 3.5 -3h1a3.5 3 0 0 1 3.5 3a3 3 0 0 1 -2 3a3 4 0 0 0 -2 4" /><path d="M12 19l0 .01" /></svg> Cómo funciona</a>
                </li>
                <li>
                    <a href="ControladorUsuarios?editar=true"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="whitesmoke"  class="icon icon-tabler icons-tabler-filled icon-tabler-user"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" /><path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" /></svg></a>
                </li>
            </ul>
        </nav>
    </header>

    <main class="mainBuscarExperiencias">
        <div class="viviendasContainer">
            <h1>Viviendas</h1>
            <c:if test="${not empty viviendas}">
                <div class="viviendas">
                    <c:forEach var="vivienda" items="${viviendas}">
                        <div class="viviendaCard">
                            <div class="texto">
                                <h3 style="margin-bottom: 20px">${vivienda.direccion}</h3>
                                <p style="margin-bottom: 10px"><strong>Ciudad:</strong> ${vivienda.ciudad}</p>
                                <p style="margin-bottom: 10px"><strong>Provincia:</strong> ${vivienda.provincia}</p>
                                <p style="margin-bottom: 10px"><strong>Código Postal:</strong> ${vivienda.codigoPostal}</p>
                                <p style="margin-bottom: 10px"><strong>Precio:</strong> ${vivienda.precio} €</p>
                                <div class="svg" style="display: flex;
                                                        justify-content: center;
                                                        align-items: center;
                                                        width: 100%;
                                                        margin-bottom: 10px">
                                    <p style="margin-right: 20px"><strong><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="currentColor"  class="icon icon-tabler icons-tabler-filled icon-tabler-bed"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M3 6a1 1 0 0 1 .993 .883l.007 .117v6h6v-5a1 1 0 0 1 .883 -.993l.117 -.007h8a3 3 0 0 1 2.995 2.824l.005 .176v8a1 1 0 0 1 -1.993 .117l-.007 -.117v-3h-16v3a1 1 0 0 1 -1.993 .117l-.007 -.117v-11a1 1 0 0 1 1 -1z" /><path d="M7 8a2 2 0 1 1 -1.995 2.15l-.005 -.15l.005 -.15a2 2 0 0 1 1.995 -1.85z" /></svg></strong> ${vivienda.habitaciones}</p>
                                    <p style="margin-right: 20px"><strong><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="currentColor"  class="icon icon-tabler icons-tabler-filled icon-tabler-bath"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M11 2a1 1 0 0 1 .993 .883l.007 .117v2.25a1 1 0 0 1 -1.993 .117l-.007 -.117v-1.25h-2a1 1 0 0 0 -.993 .883l-.007 .117v6h13a2 2 0 0 1 1.995 1.85l.005 .15v3c0 1.475 -.638 2.8 -1.654 3.715l.486 .73a1 1 0 0 1 -1.594 1.203l-.07 -.093l-.55 -.823a4.98 4.98 0 0 1 -1.337 .26l-.281 .008h-10a4.994 4.994 0 0 1 -1.619 -.268l-.549 .823a1 1 0 0 1 -1.723 -1.009l.059 -.1l.486 -.73a4.987 4.987 0 0 1 -1.647 -3.457l-.007 -.259v-3a2 2 0 0 1 1.85 -1.995l.15 -.005h1v-6a3 3 0 0 1 2.824 -2.995l.176 -.005h3z" /></svg></strong> ${vivienda.baños}</p>
                                    <p style="margin-right: 20px"><strong><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-armchair-2"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 10v-4a3 3 0 0 1 3 -3h8a3 3 0 0 1 3 3v4" /><path d="M16 15v-2a3 3 0 1 1 3 3v3h-14v-3a3 3 0 1 1 3 -3v2" /><path d="M8 12h8" /><path d="M7 19v2" /><path d="M17 19v2" /></svg></strong> ${vivienda.amueblado ? 'Sí' : 'No'}</p>
                                    <p style="margin-right: 20px"><strong></strong> ${vivienda.activo ? 'Disponible' : 'No disponible'}</p>
                                    <p style="margin-right: 20px"><strong><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="currentColor"  class="icon icon-tabler icons-tabler-filled icon-tabler-user"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" /><path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" /></svg></strong> ${vivienda.casero.nombre} (${vivienda.casero.email})</p>  <!-- Nombre y email del casero -->
                                </div>
                                <a href="detalleVivienda.jsp?id=${vivienda.id}">Ver más</a>
                            </div>
                            <div class="imagen">
                                <img src="Controladores.Usuarios/ControladorBuscarExperiencias?idImagen=${vivienda.id}" alt="Imagen de la vivienda">
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${empty viviendas}">
                <p>No se han encontrado viviendas.</p>
            </c:if>
        </div>
    </main>

    <footer>
        <!-- Información de pie de página -->
    </footer>

    <script src="../js/range.js"></script>
</body>
</html>