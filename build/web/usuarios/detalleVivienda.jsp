<%-- 
    Document   : detalleVivienda
    Created on : 11 may 2025, 19:13:11
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es-ES">
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalle Vivienda</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <!-- RateYo CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">

        <!-- jQuery y RateYo JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
    </head>
    <body>
        <header>
            <nav class="logoCabecera">
                <a href="../ControladorInicio"><img src="../imagenes/logo.png" style="width: 130px;"></a>
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
                        <a href="ControladorComoFunciona"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-question-mark"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M8 8a3.5 3 0 0 1 3.5 -3h1a3.5 3 0 0 1 3.5 3a3 3 0 0 1 -2 3a3 4 0 0 0 -2 4" /><path d="M12 19l0 .01" /></svg> Cómo funciona</a>
                    </li>
                    <li>
                        <a href="ControladorUsuarios?editar=true"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="whitesmoke"  class="icon icon-tabler icons-tabler-filled icon-tabler-user"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" /><path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" /></svg></a>
                    </li>
                </ul>
            </nav>
        </header>

        <a href="javascript:history.back()" class="enlaceVolverDetalle">Volver</a>

        <main class="mainDetalleVivienda">
            <div class="viviendaDetalle">
                <h1>${vivienda.direccion}</h1>

                <c:if test="${not empty vivienda}">
                    <div id="carouselExampleControls" class="carousel slide">
                        <div class="carousel-inner">
                            <c:forEach var="foto" items="${vivienda.fotos}" varStatus="status">
                                <div class="carousel-item ${status.first ? 'active' : ''}">
                                    <img class="d-block w-100" src="${pageContext.request.contextPath}/${foto.ruta}" alt="Imagen ${status.index + 1}">
                                </div>
                            </c:forEach>
                        </div>

                        <c:if test="${fn:length(vivienda.fotos) > 1}">
                            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" onclick="moveSlide(-1)">
                                <span class="carousel-control-prev-icon" aria-hidden="true">&#10094;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleControls" role="button" onclick="moveSlide(1)">
                                <span class="carousel-control-next-icon" aria-hidden="true">&#10095;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </c:if>
                    </div>

                    <div class="informacionVivienda">
                        <c:set var="suma" value="0" />
                        <c:forEach var="v" items="${valoraciones}">
                            <c:set var="suma" value="${suma + v.puntuacion}" />
                        </c:forEach>

                        <c:set var="totalValoraciones" value="${fn:length(valoraciones)}" />
                        <c:set var="media" value="${totalValoraciones > 0 ? suma / totalValoraciones : 0}" />
                        <div style="margin: 10px 0; display: flex; align-items: center; gap: 10px; margin-bottom: 30px;">
                            <div id="rating-vivienda"></div>
                            <div id="rating-text" style="font-size: 16px; color: #333; margin-top: 5px;">
                                <c:choose>
                                    <c:when test="${totalValoraciones > 0}">
                                        <fmt:formatNumber value="${media}" maxFractionDigits="2" minFractionDigits="2" /> / 5 (${totalValoraciones} valoraciones)
                                    </c:when>
                                    <c:otherwise>
                                        No hay valoraciones todavía.
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <p><strong>${vivienda.precio} €</strong></p>
                        <p>${vivienda.descripcion}</p>
                        <p>${vivienda.ciudad}, ${vivienda.provincia}. ${vivienda.codigoPostal}</p>
                        <div style="display: flex; align-items: center; justify-content: space-around;">
                            <p><strong>Habitaciones:</strong> ${vivienda.habitaciones}</p>
                            <p><strong>Baños:</strong> ${vivienda.baños}</p>
                            <p><strong>Amueblado:</strong> ${vivienda.amueblado ? 'Sí' : 'No'}</p>
                            <p><strong>Disponible:</strong> ${vivienda.activo ? 'Sí' : 'No'}</p>
                        </div>
                    </div>

                    <div class="enlacesValorarViviendasEnDetalleVivienda">
                        <button id="buscar" style="background-color: white; font-weight: bold; box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); margin-top: 30px;">
                            <a href="ControladorValoraciones?tipo=piso&id=${vivienda.id}" class="enlaceValorarVivienda">Valorar Piso</a>
                        </button>
                    </div>

                    <!-- Sección de comentarios -->
                    <section class="comentariosVivienda" style="margin-top: 30px;text-align: left; padding: 30px 120px 0px 120px;">
                        <h2 style="margin-bottom: 30px;">Comentarios</h2>

                        <c:if test="${empty comentarios}">
                            <p>No hay comentarios todavía. Sé el primero en comentar.</p>
                        </c:if>

                        <c:forEach var="comentario" items="${comentarios}">
                            <div class="comentario" style="border-bottom: 1px solid #ccc; padding: 10px 0;">
                                <p>
                                    <a href="ControladorValoracionesPropietarios?tipo=casero&id=${comentario.autor.id}&idVivienda=${vivienda.id}">
                                        <strong>${comentario.autor.nombre} ${comentario.autor.apellidos}</strong>
                                    </a> - <small>${comentario.fecha}</small>
                                </p>
                                <p>${comentario.comentario}</p>
                            </div>
                        </c:forEach>
                    </section>

                    <c:set var="sumaCasero" value="0" />
                    <c:forEach var="v" items="${valoracionesCasero}">
                        <c:set var="sumaCasero" value="${sumaCasero + v.puntuacion}" />
                    </c:forEach>

                    <c:set var="totalValoracionesCasero" value="${fn:length(valoracionesCasero)}" />
                    <c:set var="mediaCasero" value="${totalValoracionesCasero > 0 ? sumaCasero / totalValoracionesCasero : 0}" />

                    <div class="datosCasero" style="margin-top: 40px;">
                        <h2>Datos del Propietario</h2>
                        <h2 style="color: black; text-align: left; text-shadow: none;"><strong>${vivienda.casero.nombre}</strong></h2>
                                <fmt:formatNumber value="${mediaCasero}" maxFractionDigits="2" minFractionDigits="2" var="mediaCaseroFormateada" />

                        <div style="margin: 10px 0; display: flex; align-items: center; gap: 10px; margin-bottom: 30px;">
                            <div id="rating-casero"></div>
                            <div style="font-size: 16px; color: #333; margin-top: 5px;">
                                <c:choose>
                                    <c:when test="${totalValoracionesCasero > 0}">
                                        ${mediaCaseroFormateada} / 5 (${totalValoracionesCasero} valoraciones)
                                    </c:when>
                                    <c:otherwise>
                                        No hay valoraciones todavía.
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <p><strong>Email:</strong> ${vivienda.casero.email}</p>
                        <p><strong>Teléfono:</strong> ${vivienda.casero.telefono}</p>
                        
                        <button id="buscar" style="background-color: white; font-weight: bold; box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">
                            <a href="ControladorValoracionesPropietarios?tipo=casero&id=${vivienda.casero.id}&idVivienda=${vivienda.id}" class="enlaceValorarCasero">Valorar Propietario</a>
                        </button>

                        <!-- Sección de comentarios -->
                        <section class="comentariosVivienda" style="margin-top: 50px; text-align: left; margin-bottom: 50px;">
                            <h2>Comentarios</h2>
                            <c:if test="${empty comentarios}">
                                <p>No hay comentarios todavía. Sé el primero en comentar.</p>
                            </c:if>
                            <c:forEach var="comentarioCasero" items="${comentariosCasero}">
                                <div class="comentariosCasero" style="border-bottom: 1px solid #ccc; padding: 10px 0; margin-top: 0px;">
                                    <p>
                                        <a href="ControladorValoracionesPropietarios?tipo=casero&id=${comentarioCasero.autor.id}&idVivienda=${vivienda.id}">
                                            <strong>${comentarioCasero.autor.nombre}  ${comentarioCasero.autor.apellidos}</strong>
                                        </a> - <small>${comentarioCasero.fecha}</small>
                                    </p>
                                    <p>${comentarioCasero.comentario}</p>
                                </div>
                            </c:forEach>
                        </section>
                    </div>
                </c:if>

                <c:if test="${empty vivienda}">
                    <p>No se ha encontrado la vivienda.</p>
                </c:if>

            </div>
        </main>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const carousel = document.querySelector('#carouselExampleControls');
                const items = carousel.querySelectorAll('.carousel-item');
                let currentIndex = 0;

                window.moveSlide = function (direction) {
                    items[currentIndex].classList.remove('active');
                    currentIndex = (currentIndex + direction + items.length) % items.length;
                    items[currentIndex].classList.add('active');
                };
            });
        </script>
        <script>
            $(function () {
                // Para la vivienda
                const mediaStr = '${media}';
                const media = parseFloat(mediaStr.replace(',', '.')) || 0;

                $("#rating-vivienda").rateYo({
                    rating: media.toFixed(2),
                    readOnly: true,
                    starWidth: "30px",
                    ratedFill: "#ffc107",
                    normalFill: "#ddd",
                    precision: 2
                });

                // Para el casero
                const mediaCaseroStr = '${mediaCasero}';
                const mediaCasero = parseFloat(mediaCaseroStr.replace(',', '.')) || 0;

                $("#rating-casero").rateYo({
                    rating: mediaCasero.toFixed(2),
                    readOnly: true,
                    starWidth: "30px",
                    ratedFill: "#ffc107",
                    normalFill: "#ddd",
                    precision: 2
                });
            });
        </script>
    </body>
</html>