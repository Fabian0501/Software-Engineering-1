public class Parkhaus implements ParkhausIF{

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
        }
    }

    @Override
    public Parkticket ticketZiehen() {
        return new Parkticket();
    }

    @Override
    public void bezahlen(Parkticket ticket) {
        if(ticket.getTicketStatus() == true){
            throw new IllegalStateException("Ticket schon bezahlt!");
        }
        ticket.setBezahlt();
    }

}
