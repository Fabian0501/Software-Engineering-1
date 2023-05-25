<%--
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


</head>
<body>
<div style="display: flex; justify-content: left;">
    <h1><%= "Ticketauswahl" %></h1>
</div>

<div style="text-align: center;">
    <form action="ticketZiehen" method="post">
        <input type="submit" name="Button1" value="Normales-Ticket">   <%-- damit kann ich in  Ticketziehen unterscheiden
                                                                             welche art von ticket es ist--%>
    </form>
    <br>
    <form action="ticketZiehen" method="post">
        <input type="submit" name="Button2" value="Normales-Ticket + Ladestation">
    </form>
    <br>
    <form action="ticketZiehen" method="post">
        <input type="submit" name="Button3" value="Monats-Ticket">
    </form>



    <p>
        Normales Ticket: Die Kosten werden pro Stunde angerechnet.
    </p>
    <p>
        Normales Ticket + Ladestation: Ein normales Ticket wird ausgestellt mit zusätzlicher Berechtigung die Ladestation.
    </p>
    <p>
        Monatskarte: Ein fixer Betrag, Ladestation ist kostenlos.
    </p>
    <p>
        Info: An jedem Parkplatz befindet sich eine Ladestation für E-Autos.
    </p>


</div>
</body>
</html>
