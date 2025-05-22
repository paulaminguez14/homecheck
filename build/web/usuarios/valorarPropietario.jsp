<%-- 
    Document   : valorarPropietario
    Created on : 18 may 2025, 17:28:21
    Author     : pauladominguez
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="Modelo.Entidades.Usuario" %>

<%
    Modelo.Entidades.Usuario casero = (Modelo.Entidades.Usuario) request.getAttribute("casero");
%>

<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Valorar Piso</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <style>
            .star-rating {
                font-size: 2em;
                direction: ltr;
                unicode-bidi: bidi-override;
                display: inline-block;
            }

            .star {
                cursor: pointer;
                color: #ccc;
            }

            .star.selected {
                color: gold;
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
        <main class="mainValorarPiso">
            <h1>Valora a <%= casero.getNombre()%> <%= casero.getApellidos()%></h1>

            <form action="ControladorValoracionesPropietarios" method="post">
                <input type="hidden" name="tipo" value="casero" />
                <input type="hidden" name="idCasero" value="<%= casero.getId()%>" />
                <input type="hidden" name="idVivienda" value="${idVivienda}" />

                <label>Puntuación:</label>
                <div class="star-rating">
                    <span class="star" data-value="1">&#9733;</span>
                    <span class="star" data-value="2">&#9733;</span>
                    <span class="star" data-value="3">&#9733;</span>
                    <span class="star" data-value="4">&#9733;</span>
                    <span class="star" data-value="5">&#9733;</span>
                </div>
                <input type="hidden" name="puntuacion" id="puntuacion" required>

                <label>Comentario:</label>
                <textarea name="comentario" rows="5" cols="40" required></textarea><br>

                <input class="botonInicioSesion" type="submit" value="Enviar valoración" style="background-color: #2C2C2C; color: white;"/>

                <a href="javascript:history.back()" class="enlaceValorarPiso" style="color: black;">Volver</a>
            </form>
            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) {%>
            <p style="color:red;"><%= error%></p>
            <% }%>
        </main>
        <script>
            const stars = document.querySelectorAll('.star-rating .star');
            const puntuacionInput = document.getElementById('puntuacion');

            stars.forEach((star, index) => {
                star.addEventListener('click', () => {
                    const rating = star.getAttribute('data-value');
                    puntuacionInput.value = rating;

                    // Marcar como seleccionadas
                    stars.forEach((s, i) => {
                        s.classList.toggle('selected', i < rating);
                    });
                });
            });
        </script>
    </body>
</html>