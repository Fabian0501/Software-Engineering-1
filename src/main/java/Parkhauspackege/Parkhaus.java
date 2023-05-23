package Parkhauspackege;
import java.util.HashMap;

public class Parkhaus implements ParkhausIF{

    private final int verfügbareParkplätze = 1000;

    private HashMap<Integer, Parkticket> speicher = new HashMap<>(); // speichere hier die tickest ab
    private HashMap<Integer, Parkticket> SpeicherRausgefahren = new HashMap<>();  //Hier werden die rausgefahrenen Autos vermekt um eine Einnahmeliste zu bilden
    private final double preisProStunde = 1.50;

    @Override
    public Parkticket ticketZiehen() throws IllegalStateException {
        if (getVerfügbareParkplätze() == 0){
            throw new IllegalStateException();
        }
        Parkticket parkticket = new Parkticket();
        this.schranke(parkticket);
       // parkticket.hinzufügen(parkticket); // das gezogene parkticket wird in den array von Parktickets eingefügt
        speicher.put(parkticket.getMeineID(), parkticket); // fügt ein neues Auto ein in das parkhaus
       // return new Parkticket();
        return parkticket;
    }
    @Override
    public void schranke(Parkticket ticket) {
        if(ticket.getTicketBelegt() == false){      //Vor dem Reinfahren und nach dem Ziehen ist das Ticket noch nicht belegt == false
            System.out.println("Schranke öffnet sich zum reinfahren!");
            System.out.println("Schranke schließt sich!");
            ticket.setBelegt(true);
        }
        if(ticket.getTicketStatus() == true){       //wenn bezahlt ist, kann man rausfahren und das Ticket wird nicht mehr belegt sein
            ticket.setBelegt(false);
            System.out.println("Schranke öffnet sich zum rausfahren!");
            System.out.println("Schranke schließt sich!");
            //Füge vor dem Entfernen das Ticket in den Rausgefahren-Speicher hinzu
            SpeicherRausgefahren.put(ticket.getMeineID(), ticket);
           // ticket.entfernen(ticket);
            speicher.remove(ticket.getMeineID());
        }
    }

    @Override
    public void bezahlen(Parkticket ticket) {
        if(ticket.getTicketStatus() == true){
            throw new IllegalStateException("Ticket schon bezahlt!");
        }
        ticket.setBezahlt();
    }

    public int getBelegtePlätze() {
        return speicher.size();
    }
    //
    /**
     um eine Kopie von der Hashmap zu bekommen, brauche ich in der Tabelle für Servlet. Ansonsten könnte man das eigentlich Parkhaus unbefugt manipulieren
     */
    public HashMap<Integer, Parkticket> getAllTickets(){
        return new HashMap<>(speicher);
    }

    public HashMap<Integer, Parkticket> getAllUsedTickets(){
        return new HashMap<>(SpeicherRausgefahren);
    }


    public int getVerfügbareParkplätze() {
        return verfügbareParkplätze - getBelegtePlätze();
    }

    public double getPreisProStunde() {
        return preisProStunde;
    }
}
