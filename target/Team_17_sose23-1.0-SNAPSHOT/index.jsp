<%@ page import="Parkhauspackege.Parkhaus" %>
<%@ page import="Parkhauspackege.Parkticket" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Parkhaus</title>
    <style>
        button {
            background-color: #7fb0cb;
            font-size: 16px;
            padding: 10px 20px;
        }
        .login-button {
            position: fixed;
            bottom: 20px;
            right: 20px;
        }
        .ticketpreise {
            position: absolute;
            bottom: 20px;
            left: 20px;
            background-color: #f2f2f2;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <%
    ServletContext context = config.getServletContext();
    if (context.getAttribute("parkhaus") == null) {
        context.setAttribute("parkhaus", new Parkhaus());
    }

    Parkhaus parkhaus = (Parkhaus) context.getAttribute("parkhaus");
    int verfügbarePlätze = parkhaus.getVerfügbareParkplätze();
    double normalesTicketPreis = parkhaus.getTicketPreis()[0];
    double ladestationPreis = parkhaus.getTicketPreis()[1];
    double monatsTicketPreis = parkhaus.getTicketPreis()[2];

    int ticketID = 0;
    if (parkhaus.getBelegtePlätze() > 0) {
        ticketID = ((Parkticket) context.getAttribute("ticket")).getMeineID();
    }
%>

<div style="display: flex; justify-content: center;">
    <h1><%= "Willkommen im Parkhaus!" %></h1>
</div>
<div class="ticketpreise">
    <h2>Ticketpreise</h2>
    <p>Normales Ticket: <%= normalesTicketPreis %> Euro pro Stunde</p>
    <p>Normales Ticket + Ladestation: Normalpreis + <%= ladestationPreis %> Euro Ladegebühr</p>
    <p>Monatsticket: <%= monatsTicketPreis %> Euro</p>
</div>

<div style="display: flex; flex-direction: column; align-items: center;">
    <h2>Freie Plätze: <%= verfügbarePlätze %></h2>
</div>
<br>
    <div style="display: flex; flex-direction: column; align-items: center;">
        <h3>Ihre Ticket-ID: <%= ticketID %></h3>
    </div>
    <br>
<div style="text-align: center;">
    <form action="TicketArten.jsp" method="get">
        <button type="submit">Einfahren</button>
    </form>


</div>
    <br>
    <div style="text-align: center;">
    <form action="Kasse-Servlet" method="get">
        <button type="submit">Bezahlen</button>
    </form>
    <br>
    <form action="Parkhaus-Verlassen" method="get">
        <button type="submit">Ausfahren</button>
    </form>
</div>
    <a href="betreiberZugriff" class="login-button">Login</a>
</body>
</html>