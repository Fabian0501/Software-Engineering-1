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

@WebServlet(name = "leave" ,value = "/Parkhaus-Verlassen")
public class VerlassenServlet extends HttpServlet {

    Parkhaus parkhaus = null;

    @Override
    public void init() throws ServletException {
        parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (parkhaus.getBelegtePlätze() == 0){
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req,resp);
        }


        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h3>Bitte geben Sie ihre Ticket-ID ein!<h3>");
        out.println("<form method=\"post\">");
        out.println("<label for=\"TicketID\"> TicketID:</label>");
        out.println("<input type=\"TicketID\" id=\"TicketID\" name=\"TicketID\"><br>");
        out.println("<input type=\"submit\"value=\"Rausfahren!\"");
        out.println("</html></body");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");

        resp.setContentType("text/html");

        String value = req.getParameter("TicketID");
        ServletContext context = getServletContext();
        context.setAttribute("TicketID", value);


        if (context.getAttribute("TicketID") != null){
            int ticketNummer = Integer.parseInt(req.getParameter("TicketID"));


            HashMap <Integer, Parkticket> allTickets = parkhaus.getAllTickets();
            Parkticket parkticket = allTickets.get(ticketNummer);

            if (allTickets.containsKey(ticketNummer) && allTickets.get(ticketNummer).getTicketStatus()){
                parkhaus.schranke(allTickets.get(ticketNummer)); //löscht auch das Ticket

                    resp.getWriter().println("<h1>Auf Wiedersehen</h1>");
                    resp.getWriter().println("<a href=\"" + req.getContextPath() + "/index.jsp\">Zurück zum Parkhaus!</a>");

            }
            else {
                //Fixme => zur tabelle servlet aka Parkhaus weitergeleitet werden
                resp.getWriter().println("<h1> Ihre eingegebene ID ist Falsch. Bitte überprüfen sie ihre TicketID </h1>");
                resp.getWriter().println("<a href=\"" + req.getContextPath() + "/index.jsp\">Zurück zum Parkhaus!</a>");
            }

        }

    }

    @Override
    public void destroy() {
    }
}
