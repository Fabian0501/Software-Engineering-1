package Parkhauspackege;

import java.io.IOException;
import java.io.PrintWriter;
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
        openingHours = new HashMap<>();
        openingHours.put("Montag", new String[]{"08:00", "18:00"});
        openingHours.put("Dienstag", new String[]{"08:00", "18:00"});
        openingHours.put("Mittwoch", new String[]{"08:00", "18:00"});
        openingHours.put("Donnerstag", new String[]{"08:00", "18:00"});
        openingHours.put("Freitag", new String[]{"08:00", "18:00"});
        openingHours.put("Samstag", new String[]{"10:00", "16:00"});
        openingHours.put("Sonntag", new String[]{"geschlossen", "geschlossen"});
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
        out.println("<form method=\"post\" action=\"\">");

        out.println("Wochentag: ");
        out.println("<select name=\"day\">");
        for (HashMap.Entry<String, String[]> entry : openingHours.entrySet()) {
            String day = entry.getKey();
            out.println("<option value=\"" + day + "\">" + day + "</option>");
        }
        out.println("</select><br/>");

        out.println("Öffnet um: <input type=\"text\" name=\"openingTime\"><br/>");
        out.println("Schließt um: <input type=\"text\" name=\"closingTime\"><br/>");

        out.println("<input type=\"submit\" value=\"Ändern\">");
        out.println("</form>");
        out.println("</body></html>");
        out.println("<a href=\"" + req.getContextPath() + "/Betreiber.jsp\">Zurück zur Betreiber Startseite</a>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String day = request.getParameter("day");
        String openingTime = request.getParameter("openingTime");
        String closingTime = request.getParameter("closingTime");

        if (day != null && openingTime != null && closingTime != null) {
            openingHours.put(day, new String[]{openingTime, closingTime});
        }

        response.sendRedirect(request.getContextPath() + "/parkhaus");
    }
    @Override
    public String getServletInfo() {
        return "Parkhaus Servlet";
    }
}
