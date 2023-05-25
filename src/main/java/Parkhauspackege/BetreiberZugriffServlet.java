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

@WebServlet(name = "Betreiber",  value = "/betreiberZugriff")
public class BetreiberZugriffServlet extends HttpServlet {
    private static final String password = "password";
    private static int accessAttempt = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // eingabefeld für passwort
        out.println("<h3>Geben Sie das Passwort ein<h3>");
        out.println();
        out.println("<form method=\"post\">");
        out.println("<label for=\"password\">Password: </label>");
        out.println("<input type=\"password\" id=\"password\" name=\"password\"><br>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"bestätigen\"");
        out.println("</form></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //eingabe des users
        String inputString = req.getParameter("password");

        //out.println(inputString); //prüfen ob der eingegebene wert wirklich gespeichert wurde

        //dispatcher
        RequestDispatcher dispatcherBetreiber = req.getRequestDispatcher("Betreiber.jsp");
        RequestDispatcher dispatcherIndex = req.getRequestDispatcher("index.jsp");

        //Verhalten
        if (accessAttempt < 2){ //beschränkung der login versuche ist noch nicht erreicht
            if (inputString.equals(password)){ //eingabe entspricht password
                dispatcherBetreiber.forward(req,resp); //weiterleitung zum ziel jsp
                return;
            }
            else { //eingabe entspricht nicht dem password
                accessAttempt++; //anzahl der zugriffsversuche wird um eins erhöht
                dispatcherIndex.forward(req,resp); //user wird auf die startseite geschickt
                return;
            }
        }
        //maximale zugriffsversuche wurden überschritten ==> user wird auf die startseite geschickt
        dispatcherIndex.forward(req,resp);
    }

    @Override
    public void destroy() {

    }
}
