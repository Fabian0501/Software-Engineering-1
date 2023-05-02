package Parkhauspackege;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ticket", value = "/ticketZiehen")
public class TicketZiehenServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        Parkticket parkticket = parkhaus.ticketZiehen();

        req.setAttribute("ticket", parkticket);

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h3>" + "Willkomen in unserem Parkhaus!" + "</h3>");
        out.println("<h3> deine Ticket-ID ist: " + parkticket.getMeineID() + "</h3>");
        out.println("</body></html>");

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
