import java.util.HashMap;
public class Parkhaus implements ParkhausIF{

    private final int verfügbareParkplätze = 1000;

    HashMap<Integer, Parkticket> parkhaus = new HashMap<>();

    @Override
    public Parkticket ticketZiehen() throws IllegalStateException {
        if (getVerfügbareParkplätze() == 0){
            throw new IllegalStateException();
        }
        Parkticket parkticket = new Parkticket();
        this.schranke(parkticket);
        parkticket.hinzufügen(parkticket); // das gezogene parkticket wird in den array von Parktickets eingefügt
        parkhaus.put(parkticket.getMeineID(), parkticket); // fügt ein neues Auto ein in das parkhaus
        return new Parkticket();
    }
    @Override
    public void schranke(Parkticket ticket) {
        if(ticket.getTicketBelegt() == false){      //Vor dem Reinfahren und nach dem Ziehen ist das Ticket noch nicht belegt == false
            System.out.println("Schranke öffnet sich zum reinfahren!");
            System.out.println("Schranke schließt sich!");
            ticket.setBelegt();
        }
        if(ticket.getTicketStatus() == true){       //wenn bezahlt ist, kann man rausfahren und das Ticket wird nicht mehr belegt sein
            ticket.setBelegtFalse();
            System.out.println("Schranke öffnet sich zum rausfahren!");
            System.out.println("Schranke schließt sich!");
           // ticket.entfernen(ticket);
            parkhaus.remove(ticket.getMeineID());


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

        return parkhaus.size();
    }

    public int getVerfügbareParkplätze() {
        return verfügbareParkplätze - getBelegtePlätze();
    }
}
