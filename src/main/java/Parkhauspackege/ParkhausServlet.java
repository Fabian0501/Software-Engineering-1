package Parkhauspackege;

import Parkhauspackege.Parkhaus;
import Parkhauspackege.Parkticket;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

//wir müssen urlPattern nutzen , da es in der webApp ist, aber nicht im sleben oder oder in unterverzeichnissen von der jsp-datei ausgesehen
@WebServlet(name = "ParkhausTable", urlPatterns ={ "/table-servlet" })
public class ParkhausServlet extends HttpServlet  {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");


        ////request.getAttribut() meint die in der query
        //bekomme damit eine dynamische Ausgabe bezüglich der Tickets in HTML
      //  parkhaus.ticketZiehen();
        PrintWriter out = response.getWriter();
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Uhrzeit</th>");
        out.println("</tr>");
        for(Map.Entry<Integer, Parkticket> entry : parkhaus.getAllTickets().entrySet()){
            //gebe hier die ID und

            out.println("<tr>");
            out.println("<td>" + entry.getKey()+ "</td>");
            out.println("<td>" + entry.getValue().getTimeStamp() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");


    }
    public void doPost( HttpServletRequest request, HttpServletResponse response) throws IOException{

    }
    public void init() {}
}
