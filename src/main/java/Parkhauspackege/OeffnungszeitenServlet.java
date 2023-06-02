package Parkhauspackege;

//import sun.security.krb5.internal.Ticket;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@WebServlet(name = "oeffnungszeiten", value = "/Oeffnungszeiten-Servlet")
public class OeffnungszeitenServlet extends HttpServlet {
    private HashMap<String, String[]> openingHours;

    @Override
    public void init() throws ServletException {
        if(getServletContext().getAttribute("openingHours") == null){       //falls noch nicht initialisiert
            openingHours = new HashMap<>();
            openingHours.put("Montag", new String[]{"00:00", "23:59"});
            openingHours.put("Dienstag", new String[]{"00:00", "23:59"});
            openingHours.put("Mittwoch", new String[]{"00:00", "23:59"});
            openingHours.put("Donnerstag", new String[]{"00:00", "23:59"});
            openingHours.put("Freitag", new String[]{"00:00", "23:59"});
            openingHours.put("Samstag", new String[]{"00:00", "23:59"});
            openingHours.put("Sonntag", new String[]{"00:00", "23:59"});
            getServletContext().setAttribute("openingHours",openingHours);
        }else{      //falls doch initialisiert
            openingHours = (HashMap<String, String[]>) getServletContext().getAttribute("openingHours");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Öffnungszeiten des Parkhauses:</h1>");


        for (HashMap.Entry<String, String[]> entry : openingHours.entrySet()) {
            String day = entry.getKey();
            String[] hours = entry.getValue();

            out.println("<p>" + day + ": " + hours[0] + " - " + hours[1] + "</p>");
        }

        out.println("<hr/>");
        out.println("<h2>Öffnungszeiten ändern:</h2>");
        out.println("<form method=\"post\"\">");

        out.println("Wochentag: ");
        out.println("<select name=\"day\">");
        for (HashMap.Entry<String, String[]> entry : openingHours.entrySet()) {
            String day = entry.getKey();
            out.println("<option value=\"" + day + "\">" + day + "</option>");
        }
        out.println("</select><br/>");

        out.println("Öffnet um: <input type=\"time\" name=\"openingTime\"><br/>");
        out.println("Schließt um: <input type=\"time\" name=\"closingTime\"><br/>");

        out.println("<input type=\"submit\" value=\"Ändern\">");
        out.println("</form>");
        out.println("</body></html>");
        out.println("<a href=\"" + req.getContextPath() + "/Betreiber.jsp\">Zurück zur Betreiber Startseite</a>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String day = req.getParameter("day");
        String openingTime = req.getParameter("openingTime");
        String closingTime = req.getParameter("closingTime");

        if (day != null && openingTime != null && closingTime != null) {
            openingHours.put(day, new String[]{openingTime, closingTime});
        }
        getServletContext().setAttribute("openingHours", openingHours);

        // Die aktuelle Seite erneut laden
        resp.setHeader("Refresh", "0.1");

    }
    @Override
    public String getServletInfo() {
        return "Parkhaus Servlet";
    }
}
