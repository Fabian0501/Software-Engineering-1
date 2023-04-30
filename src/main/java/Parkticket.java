public class Parkticket  {
    private boolean bezahlt;
    private boolean belegt;
    private boolean gezogen;

    private static int id = 1;
    private int meineID;

    // TODO: Bitte in array list umwandeln und entfern-methode anpassen
    private Parkticket[] parkticketsArray = new Parkticket[1000];
    private int size = 0; // zum vereinfachten zugriff auf den array um nicht jedes Mal alles durchgehen zu müssen


    public Parkticket(){
        bezahlt = false;
        belegt = false;     //ticket erst belegt, wenn man reinfährt(ist dann nicht mehr ziehbar)
        gezogen = true;     //Ein Ticket wird nur erstellt, wenn es gezogen wird. Also gilt ein Ticket als gezogen, wenn es erstellt wurde.
        meineID = id++;     //aktueller id wert wird angenommen und danach erhöht
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

    public int getSize() {
        return size;
    }
    public int getMeineID(){
        return meineID;
    }



    public static int getId() {
        return id;
    }


    // hinzufügen-methode eigentlich void aber fürs Testen rückgabewert gegeben
    public Parkticket hinzufügen(Parkticket parkticket){
        if (getSize() < 1000){
            parkticketsArray[size] = parkticket;
        }else {
            for (Parkticket parkticketIndex : parkticketsArray){
                if (parkticketIndex == null){
                    parkticketIndex = parkticket;
                }
            }
        }
        return parkticket;
    }
    public Parkticket   entfernen(Parkticket parkticket){
        Parkticket entferntesTicket = null;
        for (Parkticket parkticketIndex : parkticketsArray){
            if (parkticketIndex.equals(parkticket)){
                entferntesTicket = parkticketIndex;
                parkticketIndex = null;
            }
        }
        return entferntesTicket;
    }
}
