import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ParkhausTest {

    @Test
    public void ticketZiehenTest(){
        //Arrange
        Parkhaus Parkhaus = new Parkhaus();

        //Act
        Parkticket actual = Parkhaus.ticketZiehen(); // Methode Ticketziehen gibt ein Objekt "Parkticket" zurück

        //Assert
        assertEquals(true,actual.ticketWurdeGezogen()); // Parkticket hat ein boolean-Attribut, ob es gezogen wurde
    }

    @Test
    public void schrankeEingangTest(){
        //Arrange
        Parkhaus Parkhaus = new Parkhaus();
        Parkticket ticket = Parkhaus.ticketZiehen();

        //Act
        Parkhaus.schranke(ticket); // Methode schranke() setzt das Attribut "belegt" von Parkticket auf "true" beim Reinfahren

        //Arrange
        assertEquals(true,ticket.getTicketBelegt()); // prüfe das Attribut "belegt" auf "true"

    }

    @Test
    void bezahlen(){
        //Arrange
        Parkhaus Parkhaus = new Parkhaus();
        Parkticket ticket = Parkhaus.ticketZiehen();

        //Act
        Parkhaus.bezahlen(ticket); // Makiert das Ticket als wurde bezahlt(also Attribut bezahlt ist true)

        //Assert
        assertEquals(true,ticket.getTicketStatus()); // Prüfe,ob das Ticket bezahlt wurde
    }


    @Test
    public void schrankeAusgangTest(){
        //Arrange
        Parkhaus Parkhaus = new Parkhaus();
        Parkticket ticket = Parkhaus.ticketZiehen();

        //Act
        Parkhaus.schranke(ticket); //bevor man rausfährt, muss man reinfahren.
        Parkhaus.bezahlen(ticket); //bevor man rausfährt, muss man das Ticket auch bezahlen.
        Parkhaus.schranke(ticket); /* Methode schranke() setzt das Attribut "belegt" von Parkticket auf "false" beim Rausfahren,
         setze Attribut "belegt" nur auf false, wenn Ticket bezahlt wurde sonst nicht*/

        //Arrange
        assertEquals(false,ticket.getTicketBelegt());// prüfe das Attribut "belegt" auf "false"
    }










}
