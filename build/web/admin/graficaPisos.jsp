<%-- 
    Document   : graficaPisos
    Created on : 15 abr 2025, 18:30:16
    Author     : pauladominguez
--%>

<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomeCheck</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/accessibility.js"></script>
        <style>
            .highcharts-figure,
            .highcharts-data-table table {
                min-width: 320px;
                max-width: 500px;
                margin: 1em auto;
            }

            .highcharts-figure {
                padding: 0;
            }

            #container {
                height: 400px;
            }

            .highcharts-data-table table {
                font-family: Verdana, sans-serif;
                border-collapse: collapse;
                border: 1px solid #ebebeb;
                margin: 10px auto;
                text-align: center;
                width: 100%;
                max-width: 500px;
            }

            .highcharts-data-table caption {
                padding: 1em 0;
                font-size: 1.2em;
                color: #555;
            }

            .highcharts-data-table th {
                font-weight: 600;
                padding: 0.5em;
            }

            .highcharts-data-table td,
            .highcharts-data-table th,
            .highcharts-data-table caption {
                padding: 0.5em;
            }

            .highcharts-data-table thead tr,
            .highcharts-data-table tbody tr:nth-child(even) {
                background: #f8f8f8;
            }

            .highcharts-data-table tr:hover {
                background: #f1f7ff;
            }

            .highcharts-description {
                margin: 0.3rem 10px;
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

        <main class="mainGraficaPisosPorCiudad">
            <h2 style="text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">Cantidad de pisos publicados por ciudad</h2>
            <figure class="highcharts-figure">
                <div id="container"></div>
            </figure>
            <a class="volverAdmin" href="ControladorInicioAdmin" style="text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">Volver</a>
        </main>

        <%
            Map<String, Integer> datos = (Map<String, Integer>) request.getAttribute("datosPisos");
            StringBuilder datosJS = new StringBuilder();
            if (datos != null) {
                for (Map.Entry<String, Integer> entry : datos.entrySet()) {
                    datosJS.append("['").append(entry.getKey()).append("', ").append(entry.getValue()).append("],");
                }
            }
            if (datosJS.length() > 0) {
                datosJS.setLength(datosJS.length() - 1); // quitar última coma
            }
        %>

        <script>
            Highcharts.chart('container', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: 0,
                    plotShadow: false
                },
                title: {
                    text: 'Pisos<br>por<br>Ciudad',
                    align: 'center',
                    verticalAlign: 'middle',
                    y: 60,
                    style: {
                        fontSize: '1.1em'
                    }
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                accessibility: {
                    point: {
                        valueSuffix: '%'
                    }
                },
                plotOptions: {
                    pie: {
                        dataLabels: {
                            enabled: true,
                            distance: -50,
                            style: {
                                fontWeight: 'bold',
                                color: 'white'
                            }
                        },
                        startAngle: -90,
                        endAngle: 90,
                        center: ['50%', '75%'],
                        size: '110%'
                    }
                },
                series: [{
                        type: 'pie',
                        name: 'Pisos',
                        innerSize: '50%',
                        data: [<%= datosJS.toString() %>]
                    }]
            });
        </script>
    </body>
</html>
