package Parkhauspackege;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
@WebServlet(name = "einnahmen", value = "/Einnahmen-Servlet")
public class EinnahmenServlet extends HttpServlet  {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        PrintWriter out = resp.getWriter();
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Uhrzeit</th>");
        out.println("<th> gezahlt (in Euro)</th>");
        out.println("</tr>");
        for(Map.Entry<Integer, Parkticket> entry : parkhaus.getAllUsedTickets().entrySet()){
            out.println("<tr>");
            out.println("<td>" + entry.getKey()+ "</td>");
            out.println("<td>" + entry.getValue().getTimeStamp() + "</td>");
            out.println("<td>" + entry.getValue().getPreis() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

        out.println("<h4> Bisherige Einnahmen:</h4>");
        double summe = 0;
        for(Map.Entry<Integer, Parkticket> entry : parkhaus.getAllUsedTickets().entrySet()){
            summe += entry.getValue().getPreis();
        }
        out.println(summe + " Euro");
        out.println("<br>");

        out.println("<a href=\"" + req.getContextPath() + "/index.jsp\">Zurück zum Parkhaus!</a>");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
