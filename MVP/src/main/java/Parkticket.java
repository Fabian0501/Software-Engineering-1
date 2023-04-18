public class Parkticket {
    private boolean bezahlt;
    private boolean belegt;
    private boolean gezogen;
    public Parkticket(){
        bezahlt = false;
        belegt = false;     //ticket erst belegt, wenn man reinfährt(ist dann nicht mehr ziehbar)
        gezogen = true;     //Ein Ticket wird nur erstellt, wenn es gezogen wird. Also gilt ein Ticket als gezogen, wenn es erstellt wurde.
    }

    public boolean getTicketStatus(){
        return bezahlt;
    }

    public boolean getTicketBelegt(){
        return belegt;
    }

    public boolean ticketWurdeGezogen(){
        return gezogen;
    }

    public void setBezahlt(){
        bezahlt = true;
    }

    public void setBelegt(){
        belegt = true;
    }

    public void setBelegtFalse(){
        belegt = false;
    }

}
