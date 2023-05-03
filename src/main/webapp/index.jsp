<%@ page import="Parkhauspackege.Parkhaus" %>
<%@ page import="Parkhauspackege.Parkticket" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        button {
            background-color: #bbdeff;
            font-size: 16px;
            padding: 10px 20px;
        }
    </style>
</head>
<body>

<%
    //hier erstelle ich den Parkhasuobjek, so dass ihn jedre acuh nutzen kann: wichitg auf parkhaus Casdtne bei getAttribute()
    ServletContext context = config.getServletContext();
    if(context.getAttribute("parkhaus") == null){
        context.setAttribute("parkhaus", new Parkhaus());
        context.setAttribute("TicketID",null);
    }

    String plaetze = (String) (((Parkhaus)context.getAttribute("parkhaus")).getVerfügbareParkplätze()+ ""); // caste erst zum Parkhaus, um dann getVerfügbarPlätze auszuführen, dann die ausgabe zu string
%>

<% int id = 0;
    if (  (int)((Parkhaus)config.getServletContext().getAttribute("parkhaus")).getBelegtePlätze() > 0){
//
        id = (int) ((Parkticket)config.getServletContext().getAttribute("ticket")).getMeineID();
}
%>

<div style="display: flex; justify-content: center;">
    <h1><%= "Willkommen im Parkhaus!" %></h1>
</div>
<div style="display: flex; flex-direction: column; align-items: center;">
    <h2> Freie Plätze: <%= plaetze %> </h2>
</div>
<br>
<div style="text-align: center;">
    <form action="ticketZiehen" method="get">
        <button type="submit"> Einfahren  </button>
    </form>
</div>

<div style="display: flex; flex-direction: column; align-items: center;">
    <h3> Ihre Ticket-ID: <%= id %> </h3>
</div>

<div style="text-align: center;">
    <form action="Kasse-Servlet" method="get">
        <button type="submit"> Bezahlen </button>
    </form>
    <br>
    <form action="Parkhaus-Verlassen" method="get">
        <button type="submit"> Ausfahren </button>
    </form>
</div>


<%--<br/>--%>
<%--<a href="table-servlet"> parkhaus </a>--%>
<%--<br/>--%>
<%--<a href="table-servlet"> Tabelle</a>--%>
<%--<br>--%>
<%--<a href="Kasse-Servlet">Kassenautomat</a>--%>
<%--<br>--%>
<%--<a href="Parkhaus-Verlassen"> Parkhaus verlassen</a>--%>
<%--<br/>--%>


</body>
</html>