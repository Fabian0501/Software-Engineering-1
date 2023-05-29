
<%--
  Created by IntelliJ IDEA.
  User: fabia
  Date: 25.05.2023
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            border: 2px solid black; /* Schwarzer Rahmen um den Button */
            outline: none; /* Entfernt den blauen Rahmen um den Button */
        }
        .button-container {
            display: flex;
            justify-content: center;
        }
        .button-container form {
            margin: 20px; /* Hinzufügen von seitlichem Abstand zwischen den Formularen */
        }
    </style>

</head>
<body>
    <div style="display: flex; justify-content: center;">
        <h1><%= "Willkommen Betreiber!" %></h1>
    </div>
    <br>
    <div style="display: flex; justify-content: center;">
        <h1><%= "Wählen sie ihre gewünschte Funktion aus" %></h1>
    </div>
    <br>
    <div style="display: flex; justify-content: center;">
        <h3><%= "Wählen sie die Art des Tickets, dessen Preis geändert werden soll" %></h3>
    </div>
    <div class="button-container">
        <br>
        <div class="button-div">
            <form action="setTicketPreis" method="get">
                <input type="submit" name="Button1" value="        Ticket        ">
            </form>
        </div>
    <div class="button-div">
        <form action="setTicketPreis" method="get">
            <input type="submit" name="Button2" value="       E-Ticket       ">
        </form>
    </div>
    <div class="button-div">
        <form action="setTicketPreis" method="get">
            <input type="submit" name="Button3" value="    MonatsTicket    ">
        </form>
    </div>
</div>
<br>
<br>
<br>
<div style="text-align: center;">
    <form action="Einnahmen-Servlet" method="get">
        <button type="submit"> Einnahmen ansehen</button>
    </form>
    <br>
    <form action="index.jsp">
        <button type="submit"> Parkhaus Startseite</button>
    </form>
</div>
</body>
</html>