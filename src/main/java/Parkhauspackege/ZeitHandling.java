package Parkhauspackege;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ZeitHandlingServlet", value ={ "/ZeitHandling-Servlet" })
public class ZeitHandling extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        RequestDispatcher dispatcher = req.getRequestDispatcher("Betreiber.jsp");

        if (req.getParameter("button2") != null) {
            parkhaus.incparkhausUhr(300000);
        } else if (req.getParameter("button3") != null) {
            parkhaus.incparkhausUhr(900000);
        } else if (req.getParameter("button4") != null) {
            parkhaus.incparkhausUhr(3600000);
        } else if (req.getParameter("button1") != null) {
            String selectedDateTimeStr = req.getParameter("partydate");
            LocalDateTime selectedDateTime = LocalDateTime.parse(selectedDateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime now = parkhaus.getParkhausUhr().toLocalDateTime();
            if (selectedDateTime.isBefore(now)) {
                throw new ServletException("Ungültiges Datum: Die Zeit darf nicht zurückgespult werden.");
            } else {
                Timestamp timestamp = Timestamp.valueOf(selectedDateTime);
                parkhaus.setParkhausUhr(timestamp);
            }
        }

        dispatcher.forward(req, resp);
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<a href=\"Betreiber.jsp\">Zurück zum Betreiber!</a>");
        out.close();
    }
}
