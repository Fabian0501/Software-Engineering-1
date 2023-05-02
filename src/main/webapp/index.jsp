<%@ page import="Parkhauspackege.Parkhaus" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Willkommen im Parkhaus!" %>
</h1>
<br/>


<form action="ticketZiehen" method="get">
    <button type="submit">Ticket ziehen</button>
</form>


<%
    //hier erstelle ich den Parkhasuobjek, so dass ihn jedre acuh nutzen kann: wichitg auf parkhaus Casdtne bei getAttribute()
    ServletContext context = config.getServletContext();
    if(context.getAttribute("parkhaus") == null){
        context.setAttribute("parkhaus", new Parkhaus());
        context.setAttribute("TicketID",null);
    }

    String plaetze = (String) (((Parkhaus)context.getAttribute("parkhaus")).getVerfügbareParkplätze()+ ""); // caste erst zum Parkhaus, um dann getVerfügbarPlätze auszuführen, dann die ausgabe zu string
%>
<div style="display: flex; flex-direction: row;">
    <p><%= plaetze %> Plätze frei</p>
</div>
<br/>
<a href="table-servlet"> parkhaus </a>
<br/>
<a href="table-servlet"> Tabelle</a>
<br>
<a href="Kasse-Servlet">Kassenautomat</a>
<br/>
</body>
</html>