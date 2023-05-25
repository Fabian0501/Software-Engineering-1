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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        Parkticket parkticket = parkhaus.ticketZiehen();

        //hier stelle ich fest, welche art tickrs es ist, damit man damit später was anfagnen kann
        if(request.getParameter("Button2") != null){ //Normales Ticket + Ladestationberechtigung
            parkticket.setTicketart(1);
        }else if(request.getParameter("Button3") != null){ //Monatsticket
            parkticket.setTicketart(2);
            //hier kommt noch code // dürfen nicht aud tabelle entfernet werden
        }else{
            parkticket.setTicketart(0);
        }
        //für ein Normales Ticket muss man nicht weiter tun

//        req.getServletContext().setAttribute("ticket" + parkticket.getMeineID(), parkticket);
        request.getServletContext().setAttribute("ticket", parkticket);
        PrintWriter out = response.getWriter();

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }



    @Override
    public void destroy() {
        super.destroy();
    }

}
