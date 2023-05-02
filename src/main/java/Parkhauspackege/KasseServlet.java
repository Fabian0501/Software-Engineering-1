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

@WebServlet(name = "Kasse",value = "/Kasse-Servlet")
public class KasseServlet extends HttpServlet {
    Parkhaus parkhaus = null;

    public void init(){
        parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        if (parkhaus.getBelegtePlätze() == 0){
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Bitte geben Sie ihre Ticket-ID ein!<h3>");
        out.println("<form method=\"post\">");
        out.println("<label for=\"TicketID\"> TicketID:</label>");
        out.println("<input type=\"TicketID\" id=\"TicketID\" name=\"TicketID\"><br>");
        out.println("<input type=\"submit\"value=\"Bezahlen!\"");
        out.println("</html></body");
        int x = Integer.parseInt(request.getParameter("TicketID"));
        getServletContext().setAttribute("TicketID",x);
    }


    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        int id = Integer.parseInt(request.getParameter("TicketID"));
        Parkticket ticket = parkhaus.getAllTickets().get(id);
        //Parkticket ticket = ;

        String timestmp=ticket.getTimeStamp();
       PrintWriter out = response.getWriter();
       out.println("<html><body>");
       out.println("TicketID"+id+"<br><br");
       out.println("Einfahrtzeit: "+timestmp+"<br><br>");
       out.println("Preis:" + parkhaus.getPreisProStunde());
       out.println("<a href=\"" + request.getContextPath() + "/index.jsp\">Zurück zur Startseite</a>");
       out.println("<html><body>");
       parkhaus.bezahlen(ticket);

    }
}
