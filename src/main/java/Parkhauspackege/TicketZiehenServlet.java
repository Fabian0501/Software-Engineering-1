package Parkhauspackege;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "ticket", value = "/ticketZiehen")
public class TicketZiehenServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        Parkticket parkticket = parkhaus.ticketZiehen();

        //hier stelle ich fest, welche art tickrs es ist, damit man damit später was anfagnen kann
        if(request.getParameter("Button2") != null){ //Normales Ticket + Ladestationberechtigung
            parkticket.setTicketart(1);
            parkticket.setPreis(parkhaus.getTicketPreis()[1]);//Setzt den Preis für Normales Ticket+Ladestation

        }else if(request.getParameter("Button3") != null){ //Monatsticket
            parkticket.setTicketart(2);
            parkticket.setPreis(parkhaus.getTicketPreis()[2]); //Setzt den Preis für Monatsticket
            request.getServletContext().setAttribute("ticket", parkticket);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
            // dürfen nicht aud tabelle entfernet werden
        }else{
            parkticket.setTicketart(0);
            parkticket.setPreis(parkhaus.getTicketPreis()[0]); //Setzt den Preis für normales Ticket
        } //für ein Normales Ticket muss man nicht weiter tun


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        out.println("<h1> Ihre eingegebene ID ist falsch. Bitte überprüfen Sie Ihre Ticket-ID. </h1>");
        out.println("<a href=\"index.jsp\">Zurück zum Parkhaus!</a>");
        out.close();
    }


    @Override
    public void destroy() {
        super.destroy();
    }

}
