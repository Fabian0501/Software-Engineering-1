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
<br>
<br>
<%-- fIXME HIER WEITERE BUTTON EINFÜGEN ... --%>
<div style="text-align: center;">
  <form action="setTicketPreis" method="get">
    <button type="submit"> Ticket Kosten ändern </button>
  </form>
  <br>
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
