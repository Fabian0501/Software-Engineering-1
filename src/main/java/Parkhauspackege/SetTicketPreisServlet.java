package Parkhauspackege;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SetTicket", value = "/setTicketPreis")
public class SetTicketPreisServlet extends HttpServlet {
    Parkhaus parkhaus = null;
    double[] aktuellerPreise;

    @Override
    public void init() throws ServletException {
        parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
       aktuellerPreise = parkhaus.getTicketPreis();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // eingabefeld für Ticketpreis
        out.println("<h3>Möchten sie den aktuellen Preis pro Stunde von " + aktuellerPreise + " ändern ? <h3>");
        out.println();
        out.println("<form method=\"post\">");
        out.println("<label for=\"stundenPreis\">Neuer stundenPreis :  </label>");
        out.println("<input type=\"stundenPreis\" id=\"stundenPreis\" name=\"stundenPreis\"><br>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"bestätigen\"");
        out.println("</form></body></html>");
        out.println("<br>");
        out.println("<br>");
        out.println("<br>");
//      out.println("<a href=\"" + req.getContextPath() + "/Betreiber.jsp\">Zurück zur Betreiber Startseite</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        double newTicketPreis = Double.parseDouble(req.getParameter("stundenPreis"));

        if (newTicketPreis >= 0){
           // parkhaus.setPreisProStunde(newTicketPreis);
            out.println("<h3> Der Preis pro Stunde wurde erfolgreich auf " + newTicketPreis + " gesetzt");
        }
        out.println("<br>");
        out.println("<br>");
        out.println("<a href=\"" + req.getContextPath() + "/Betreiber.jsp\">Zurück zur Betreiber Startseite</a>");
    }
}
