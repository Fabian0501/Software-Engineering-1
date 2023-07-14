package Parkhauspackege;

//import jdk.vm.ci.meta.Local;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ticket", value = "/ticketZiehen")
public class TicketZiehenServlet extends HttpServlet {
    HashMap<String, String[]> openingHours;
    @Override
    public void init() throws ServletException{
        //Folgendes ist die Initialisierung der Öffnungszeiten
        //Konnte leider nicht in OeffnungszeitenServlet durchgeführt werden, da diese Daten auch hier benötigt werden
        //muss in beiden Servlet auf "== null"geprüft und durchgeführt werden.
        if(getServletContext().getAttribute("openingHours") == null){       //falls noch nicht initialisiert
            openingHours = new HashMap<>();
            openingHours.put("Montag", new String[]{"00:00", "23:59"});
            openingHours.put("Dienstag", new String[]{"00:00", "23:59"});
            openingHours.put("Mittwoch", new String[]{"00:00", "23:59"});
            openingHours.put("Donnerstag", new String[]{"00:00", "23:59"});
            openingHours.put("Freitag", new String[]{"00:00", "23:59"});
            openingHours.put("Samstag", new String[]{"00:00", "23:59"});
            openingHours.put("Sonntag", new String[]{"00:00", "23:59"});
            getServletContext().setAttribute("openingHours",openingHours);
        }else{      //falls doch initialisiert
            openingHours = (HashMap<String, String[]>) getServletContext().getAttribute("openingHours");
        }
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Parkhaus parkhaus = (Parkhaus) getServletContext().getAttribute("parkhaus");
        PrintWriter out = response.getWriter();

        if (request.getParameter("TicketID") != null) {
            int x = Integer.parseInt(request.getParameter("TicketID"));
            Parkticket ticket = parkhaus.getAllTickets().get(x);

            if (ticket.getTicketart().equals("MonatsTicket") && !ticket.getTicketStatus()) { //jemand mit monatsticket betritt das parkhaus
               doPost(request,response);
            }
        }



        //ParkhausZeit
        //Hole die momentane Zeit des Parkhauses:
        int hours = parkhaus.getParkhausUhr().toLocalDateTime().getHour();     //to localdatetime weil date ist veraltet(deprecated) und man sollte lieber mit der neueren version localdatetime(ohne zeitzonen) arbeiten (alternativ: gethours() von date)
        int minutes = parkhaus.getParkhausUhr().toLocalDateTime().getMinute();
        // Erstelle ein LocalTime-Objekt für den Timestamp(macht den ganzen vergleich etwas angenehmer)
        LocalTime currentTime = LocalTime.of(hours, minutes);

        //Öffnungszeit
        //Hole die Hashmap openingHours aus dem ServletContext: Ist schon oben
        //Welcher Tag ist momentan im Parkhaus:
        DayOfWeek currentDay = parkhaus.getParkhausUhr().toLocalDateTime().getDayOfWeek();
        //Hole die Öffnungszeiten von dem jeweiligen Tag:
        String[] openingTimeS = openingHours.get(getGermanDayOfWeek(currentDay));


        //Folgender Abschnitt ist dazu, um jeweils die Strings openingTimeS und closingTimeS in 2 Strings aufzuteilen und die Stunden und Minuten zu extrahieren.
        String openingS = openingTimeS[0];
        String closingS = openingTimeS[1];

        String[] openingParts = openingS.split(":");
        int openingHour = Integer.parseInt(openingParts[0]);
        int openingMinute = Integer.parseInt(openingParts[1]);

        String[] closingParts = closingS.split(":");
        int closingHour = Integer.parseInt(closingParts[0]);
        int closingMinute = Integer.parseInt(closingParts[1]);

        //hier LocalTime-Objekt erstellen für den erleichterten Vergleich mit currentTime
        LocalTime openingTime = LocalTime.of(openingHour,openingMinute);
        LocalTime closingTime = LocalTime.of(closingHour,closingMinute);

        // Erstelle ein LocalTime-Objekt für den Timestamp

    if(currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)) {
            //hier stelle ich fest, welche art tickrs es ist, damit man damit später was anfagnen kann
            if (request.getParameter("Button2") != null) { //Normales Ticket + Ladestationberechtigung
                Parkticket parkticket = parkhaus.ticketZiehen();
                parkticket.setTicketart(1);
                parkticket.setTimestamp(new Timestamp(System.currentTimeMillis()));
                parkticket.setPreis(parkhaus.getTicketPreis()[1]);//Setzt den Preis für Normales Ticket+Ladestation
                request.getServletContext().setAttribute("ticket", parkticket);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

            } else if (request.getParameter("Button3") != null) { //Monatsticket
                Parkticket parkticket = parkhaus.ticketZiehen();
                parkticket.setTicketart(2);
                parkticket.setTimestamp(new Timestamp(System.currentTimeMillis()));
                parkticket.setPreis(parkhaus.getTicketPreis()[2]); //Setzt den Preis für Monatsticket
                request.getServletContext().setAttribute("ticket", parkticket);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                // dürfen nicht aud tabelle entfernet werden

            } else if (request.getParameter("Button1") != null) {
                Parkticket parkticket = parkhaus.ticketZiehen();
                parkticket.setTicketart(0);
                parkticket.setTimestamp(new Timestamp(System.currentTimeMillis()));
                parkticket.setPreis(parkhaus.getTicketPreis()[0]); //Setzt den Preis für normales Ticket
                request.getServletContext().setAttribute("ticket", parkticket);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } //für ein Normales Ticket muss man nicht weiter tun
        }else{
            throw new IllegalArgumentException("Das Parkhaus ist geschlossen!");
    }


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

    private static String getGermanDayOfWeek(DayOfWeek dayOfWeek) {     //Für das Übersetzen von dayOfWeek im Englischen zu Wochentagen auf Deutsch, damit  String[] openingTimeS = openingHours.get("currentDay");  erfolgreich und nicht null ist.
        switch (dayOfWeek) {
            case MONDAY:
                return "Montag";
            case TUESDAY:
                return "Dienstag";
            case WEDNESDAY:
                return "Mittwoch";
            case THURSDAY:
                return "Donnerstag";
            case FRIDAY:
                return "Freitag";
            case SATURDAY:
                return "Samstag";
            case SUNDAY:
                return "Sonntag";
            default:
                return "";
        }
    }

}
