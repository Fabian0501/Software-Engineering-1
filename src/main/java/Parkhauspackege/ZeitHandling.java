package Parkhauspackege;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ZeitHandlingServlet", value ={ "/ZeitHandling-Servlet" })
public class ZeitHandling extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");


        if(req.getParameter("button2")!=null){
            parkhaus.incparkhausUhr(300000);
            dispatcher.forward(req,resp);
        }
        if(req.getParameter("button3")!=null){
            parkhaus.incparkhausUhr(900000);
            dispatcher.forward(req,resp);
        }
        if(req.getParameter("button4")!=null){
            parkhaus.incparkhausUhr(3600000);
            dispatcher.forward(req,resp);
        }

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
