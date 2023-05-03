package Parkhauspackege;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

        super.doGet(req, resp);
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
