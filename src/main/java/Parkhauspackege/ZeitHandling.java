package Parkhauspackege;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ZeitHandlingServlet", value ={ "/ZeitHandling-Servlet" })
public class ZeitHandling extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
