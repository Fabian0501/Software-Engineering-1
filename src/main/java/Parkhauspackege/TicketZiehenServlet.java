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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        Parkticket parkticket = parkhaus.ticketZiehen();

//        req.getServletContext().setAttribute("ticket" + parkticket.getMeineID(), parkticket);
        req.getServletContext().setAttribute("ticket", parkticket);
        PrintWriter out = resp.getWriter();

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
