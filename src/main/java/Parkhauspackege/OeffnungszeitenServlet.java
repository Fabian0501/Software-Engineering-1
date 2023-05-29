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

@WebServlet(name = "oeffnungszeiten", value = "/Oeffnungszeiten-Servlet")
public class OeffnungszeitenServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("<html><body>");
        out.println("<h3>Öffnungszeiten festlegen: <h3>");
        out.println("<form method=\"post\">");
        out.println("<label for=\"TicketID\"> Von:</label>");
        out.println("<input type=\"Zeit\" id=\"Zeitvon\" name=\"Zeitvon\"><br>");
        out.println("<label for=\"Zeitvon\"> Bis:</label>");
        out.println("<input type=\"Zeit\" id=\"Zeitbis\" name=\"Zeitbis\"><br>");
        out.println("<input type=\"submit\"value=\"setzen!\"<br>");
        out.println("</html></body");
        out.println();
    }
}
