<%@ page import="Parkhauspackege.TicketZiehenServlet" %><%--
  Created by IntelliJ IDEA.
  User: Ömer Süzen
  Date: 25/05/2023
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticketauswahl</title>
    <style>
        .ticket-info {
            position: fixed;
            bottom: 20px;
            left: 20px;
            background-color: #f0f0f0;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div style="display: flex; justify-content: left;">
    <h1><%= "Ticketauswahl" %></h1>
</div>

<div style="text-align: center;">
    <form action="ticketZiehen" method="get">
        <input type="submit" name="Button1" value="Normales-Ticket">
    </form>
    <br>
    <form action="ticketZiehen" method="get">
        <input type="submit" name="Button2" value="Normales-Ticket + Ladestation">
    </form>
    <br>
    <form action="ticketZiehen" method="get">
        <input type="submit" name="Button3" value="Monats-Ticket">
    </form>
    <br>
    <form action="ticketZiehen" method="get">
        <label for="TicketID">Mit Ticket-ID einfahren:</label>
        <input type="TicketID" id="TicketID" name="TicketID" value="">
        <input type="submit" value="Einfahren!">

    </form>
</div>

<div class="ticket-info">
    <p>
        Normales Ticket: Die Kosten werden pro Stunde angerechnet.
    </p>
    <p>
        Normales Ticket + Ladestation: Ein normales Ticket wird ausgestellt mit zusätzlicher Berechtigung für die Ladestation.
    </p>
    <p>
        Monatskarte: Ein fester Betrag, die Ladestation ist kostenlos.
    </p>
    <p>
        Info: An jedem Parkplatz befindet sich eine Ladestation für E-Autos.
    </p>
</div>
</body>
</html>
