<%-- 
    Document   : comoFunciona
    Created on : 16 may 2025, 12:03:46
    Author     : pauladominguez
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomeCheck</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
        <div class="mainComoFunciona">
            <a href="javascript:history.back()" class="enlaceVolverPublicarVivienda" style="color: black;">Volver</a>
            <h1>¿Cómo funciona HomeCheck?</h1>
            <div class="contenedorTarjetasInformacion">
                <div class="tarjetasInformacion">
                    <h2>¿Qué es HomeCheck?</h2>
                    <p>
                        <span class="highlight">HomeCheck</span> es una plataforma web diseñada para facilitar el alquiler de viviendas a largo plazo. A diferencia de otras apps similares, HomeCheck se enfoca exclusivamente en la vivienda habitual, no en alojamientos vacacionales.
                    </p>
                </div>

                <div class="tarjetasInformacion">
                    <h2>¿Cómo funciona?</h2>
                    <p>
                        Los <span class="highlight">propietarios</span> pueden publicar sus pisos en la plataforma, incluyendo fotos, descripciones, ubicación y condiciones del alquiler. Por otro lado, los <span class="highlight">interesados</span> pueden navegar por las viviendas disponibles y contactar directamente con los caseros a través de la aplicación.
                    </p>
                </div>

                <div class="tarjetasInformacion">
                    <h2>Un sistema de valoraciones único</h2>
                    <p>
                        HomeCheck se diferencia por su sistema de valoraciones <strong>bidireccional</strong>:
                    </p>
                    <p>Los <strong>inquilinos</strong> pueden valorar tanto los pisos como a los <strong>caseros</strong>. 
                        Los <strong>propietarios</strong> pueden valorar a los <strong>inquilinos</strong> una vez finaliza el alquiler.</p>
                    <p>
                        Esto crea un entorno más <span class="highlight">seguro y transparente</span> para todos los usuarios, fomentando relaciones de confianza y ayudando a tomar decisiones mejor informadas.
                    </p>
                </div>

                <div class="tarjetasInformacion">
                    <h2>Tu hogar, con confianza</h2>
                    <p>
                        En HomeCheck creemos que alquilar una vivienda debe ser un proceso sencillo, justo y transparente. Por eso trabajamos para ofrecerte una plataforma donde todos los implicados tengan voz y puedan compartir sus experiencias.
                    </p>
                </div>
            </div>
        </div>
        <script>
            const tarjetas = document.querySelectorAll('.tarjetasInformacion');

            tarjetas.forEach(tarjeta => {
                const titulo = tarjeta.querySelector('h2');
                titulo.addEventListener('click', () => {
                    // Alternar clase active para mostrar/ocultar párrafo
                    tarjeta.classList.toggle('active');
                });
            });
        </script>
    </body>
</html>
