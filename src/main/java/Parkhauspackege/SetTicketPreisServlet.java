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

        //die ausgaben auf den bildschirm zeigen die jeweiligen Preise für das ausgewählte ticket an
        if (req.getParameter("Button1") != null){
            out.println("<h3>Möchten sie den aktuellen Preis pro Stunde von " + aktuellerPreise[0] + " ändern ? <h3>");
        }
        if (req.getParameter("Button2") != null){
            out.println("<h3>Möchten sie den aktuellen Preis pro Stunde von " + aktuellerPreise[1] + " ändern ? <h3>");
        }
        if (req.getParameter("Button3") != null){//muss if() bleiben, ansonsten spring er hier rein, nach dem er in eine der ersten beiden abfragen hineingegangen ist
            out.println("<h3>Möchten sie den aktuellen Preis pro Stunde von " + aktuellerPreise[2] + " ändern ? <h3>");
        }

        // eingabefeld für Ticketpreis
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

            if (req.getParameter("Button1") != null){
                parkhaus.setTicketpreise(0,newTicketPreis);
            }
            if (req.getParameter("Button2") != null){
                parkhaus.setTicketpreise(1,newTicketPreis);
            }
            if (req.getParameter("Button3") != null){ //muss aich eine if anweisung sein, sonst springt er nach einem der ersten beiden abfragen auch hier rein und ändern somit 2 preise auf einmal
                parkhaus.setTicketpreise(2,newTicketPreis);
            }

            //Feedback an den user, dass die änderungen erfolgreich war
            out.println("<h3> Der Preis pro Stunde wurde erfolgreich auf " + newTicketPreis + " gesetzt");
            out.println("<br>");
            out.println("<br>");

        }

        out.println("<a href=\"" + req.getContextPath() + "/Betreiber.jsp\">Zurück zur Betreiber Startseite</a>");
    }
}
