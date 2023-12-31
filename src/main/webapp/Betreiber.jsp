<%--
  Created by IntelliJ IDEA.
  User: fabia
  Date: 25.05.2023
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Parkhauspackege.Parkhaus" %>
<%@ page import="Parkhauspackege.Parkticket" %>

<html>
<head>
    <title>Betreiber</title>
    <style>
        button {
            background-color: #7fb0cb;
            font-size: 16px;
            padding: 10px 20px;
        }
        .button-div input[type="submit"] {
            width: 100%; /* Breite des Buttons innerhalb des Div-Elements */
            height: 60%; /* Höhe des Buttons innerhalb des Div-Elements */
            background-color: #7fb0cb; /* Hintergrundfarbe des Buttons */
            color: #000000; /* Textfarbe des Buttons */
            font-size: 16px; /* Schriftgröße des Buttons */
            border: 2px solid black;  /* button-container um buttons horizontal anzuordnen. */
            outline: none; /* Entfernt den blauen Rahmen um den Button */
        }
        .button-container {
            display: flex;
            justify-content: center;
            align-items: center; /* Zentriert die Elemente horizontal und vertikal */
            flex-wrap: wrap; /* Elemente bei Bedarf umbrechen */
            gap: 20px; /* Abstand zwischen den Elementen */
        }
        .button-container form {
            flex: 1 1 auto; /* Elemente gleichmäßig verteilen */
        }
        .button-container .time-buttons {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px;
        }
        .logout-button{
            position: fixed;
            bottom: 20px;
            right: 20px;
            width: 5%; /* Breite des Buttons innerhalb des Div-Elements */
            height: 5%; /* Höhe des Buttons innerhalb des Div-Elements */
            font-size: 15px; /* Schriftgröße des Buttons */
            background-color: #ffaf37;
        }
    </style>
    <%
        ServletContext context = config.getServletContext();
        if (context.getAttribute("parkhaus") == null) {
            context.setAttribute("parkhaus", new Parkhaus());
        }

        Parkhaus parkhaus = (Parkhaus) context.getAttribute("parkhaus");
        String s = parkhaus.getParkhausUhr() + "";
    %>
</head>
<body>
<div style="display: flex; justify-content: center;">
    <h1><%= "Willkommen Betreiber!" %></h1>
</div>
<br>
<div style="display: flex; justify-content: center;">
    <h1><%= "Wählen Sie Ihre gewünschte Funktion aus" %></h1>
</div>
<br>
<div style="display: flex; justify-content: center;">
    <h3><%= "Wählen Sie die Art des Tickets, dessen Preis geändert werden soll" %></h3>
</div>
<div class="button-container">
    <div class="button-div">
        <form action="setTicketPreis" method="get">
            <input type="submit" name="Button1" value="Ticket">
        </form>
    </div>
    <div class="button-div">
        <form action="setTicketPreis" method="get">
            <input type="submit" name="Button2" value="E-Ticket">
        </form>
    </div>
    <div class="button-div">
        <form action="setTicketPreis" method="get">
            <input type="submit" name="Button3" value="Monatsticket">
        </form>
    </div>
    <div class="button-div">
        <form action="table-servlet" method="get">
            <input type="submit" value="Parkhausbesucher">
        </form>
    </div>
</div>
<br>
<br>
<div class="button-container">
    <div class="button-div">
        <form action="Einnahmen-Servlet" method="get">
            <button type="submit">Einnahmen</button>
        </form>
    </div>
    <div class="button-div">
        <form action="Oeffnungszeiten-Servlet" method="get">
            <button type="submit">Öffnungszeiten festlegen</button>
        </form>
    </div>
</div>
<div class="button-container">
    <form action="ZeitHandling-Servlet" method="get">
        <div class="time-buttons">
            <input
                    id="party"
                    type="datetime-local"
                    name="partydate"
                    value="<%= s %>"
            />
            <button type="submit" name="button1">Timewarp</button>
            <button type="submit" name="button2">+5 Minuten</button>
            <button type="submit" name="button3">+10 Minuten</button>
            <button type="submit" name="button4">+1 Stunde</button>
        </div>
    </form>
</div>
<div>
    <br>
    <form action="index.jsp">
        <button type="submit">Parkhaus Startseite</button>
    </form>
</div>
<form action="betreiberZugriff">
    <input class="logout-button" type="submit" name="logout" value="logout">
</form>

</body>
</html>
