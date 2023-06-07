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


        //Regelt den logout prozess, der durch das Drücken vom logout-button im Betreiber.jsp eingeleitet wird
        if (req.getParameter("logout") != null){
            accessAttempt = 0; // zurücksetzten der anmeldeversuche
            getServletContext().setAttribute("isLoggedIn",null); //flag auf ihren default wert
            resp.sendRedirect("index.jsp");
            return;
        }



        // eingabefeld für passwort
        out.println("<h3>Geben Sie das Passwort ein<h3>");
        out.println();
        out.println("<form method=\"post\">");
        out.println("<label for=\"password\">Password: </label>");

        out.print("<input type=\"password\" id=\"password\" name=\"password\">"); // ...
        if (accessAttempt == 2){
            out.println(" Letzter Versuch!!!"); //wird bei dem out.print eine zeile darüber hinten dran gehangen
        }else {
            out.println(" verbleibende Versuche: " + (3-accessAttempt)); //...
        }
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

        ++accessAttempt; //anzahl der zugriffsversuche wird um eins erhöht jedes Mal, wenn die doPost aufgerufen wird

        //dispatcher
        RequestDispatcher dispatcherBetreiber = req.getRequestDispatcher("Betreiber.jsp");
        RequestDispatcher dispatcherIndex = req.getRequestDispatcher("index.jsp");

        //Verhalten
        if (accessAttempt <= 3){ //beschränkung der login versuche (erlaubt 3) ist noch nicht erreicht

            if (inputString.equals(password)){ //eingabe entspricht password
                getServletContext().setAttribute("isLoggedIn" , 1); //erfolgreicher login
                //dispatcherBetreiber.forward(req,resp); //weiterleitung zum ziel jsp
                resp.sendRedirect("Betreiber.jsp");
                return;
            }
            else { //eingabe entspricht nicht dem password

                if (accessAttempt == 3){ //der dritte anmeldeversuch ist auch gescheitert
                    getServletContext().setAttribute("isLoggedIn" , -1); // ändert die flag für den login button im index.jsp
                }

                //dispatcherIndex.forward(req,resp); //user wird auf die startseite geschickt
                resp.sendRedirect("index.jsp");
                return;
            }
        }
        /*
        maximale zugriffsversuche wurden überschritten ==> user wird auf die startseite geschickt

        Redirect zur index.jsp
        Erzwingt im gegensatz zum dispatcher das neu laden,
        des HTTP-Anforderungs-/Antwortzyklus und ermöglicht das Neuladen der Zielseite.
        Somit haben wir beim Erscheinen auf dem jsp ein frisch geladenes jsp und sehen die entstandenen änderungen
         */
        resp.sendRedirect("index.jsp");
    }

    @Override
    public void destroy() {

    }
}
