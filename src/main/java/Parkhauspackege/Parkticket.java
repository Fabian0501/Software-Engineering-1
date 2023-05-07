package Parkhauspackege;

import java.util.Date;
import java.sql.Timestamp;

public class Parkticket  {
    private boolean bezahlt;
    private boolean belegt;
    private boolean gezogen;
    private Timestamp timestamp;

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
        timestamp = new Timestamp(System.currentTimeMillis());
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

    public void setBelegt(boolean x){
        belegt = x;
    }


    public int getSize() {
        return size;
    }
    public int getMeineID(){
        return meineID;
    }

    /**
     * liefer die Uhrzeit, wo es erstellt wurde
     * @return
     */
    public String getTimeStamp(){
        Date date  = new Date(timestamp.getTime());
        return date.getHours() +":" + date.getMinutes() + ":" + date.getSeconds();
    }

    public double getTimeInSec(){
        Date date = new Date(timestamp.getTime());
        double seconds = date.getHours()*3600+date.getMinutes()*60+date.getSeconds();
        return seconds;
    }

    public double calculateTicket(double preis){
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Date now  = new Date(current.getTime());
        double curentTime= now.getHours()*3600+now.getMinutes()*60+now.getSeconds();
        double stampTime = getTimeInSec();
        double diff = curentTime-stampTime;
        double endpreis = 0;
        while (diff>3600){
            endpreis+=preis;
            diff-=3600;
        }
        endpreis+=preis;
        return endpreis;
    }

    public String calculateParkdauer(){
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Date now  = new Date(current.getTime());
        double curentTime= now.getHours()*3600+now.getMinutes()*60+now.getSeconds();
        double stampTime = getTimeInSec();
        double diff = curentTime-stampTime;
        double dauer = 0;
        if(diff>=3600){
        while (diff>=3600){
            dauer+=1.0;
            diff-=3600;
        }
            dauer+=diff/3600;
            return dauer+"h";
        }
        if(diff>=60){
            while (diff>=60){
                dauer+=1.0;
                diff-=60;
            }
            return dauer+"min";
        }
        return diff + "s";
    }



    public static int getId() {
        return id;
    }


    // hinzufügen-methode eigentlich void aber fürs Testen rückgabewert gegeben
    public Parkticket hinzufügen(Parkticket parkticket){
        for (Parkticket parkticketIndex : parkticketsArray){
            if (parkticketIndex == null){
                parkticketIndex = parkticket;
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
        size--;
        return entferntesTicket;
    }
}
