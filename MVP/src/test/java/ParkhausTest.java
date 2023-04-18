import org.junit.jupiter.api.Test;

public class ParkhausTest {

    @Test
    public void ticketZiehenTest(){
        //Arrange

        //Act
        Parkticket actual = ticketZiehen(); // Methode Ticketziehen gibt ein Objekt "Parkticket" zurück

        //Assert
        assertEquals(true,actual.ticketWurdeGezogen()); // Parkticket hat ein boolean-Attribut, ob es gezogen wurde
    }

    @Test
    public void schrankeEingangTest(){
        //Arrange
        Parkticket ticket = new Parkticket();
        //Act
        ticket.schranke(); // Methode schranke() setzt das Attribut "belegt" von Parkticket auf "true" beim Reinfahren
        //Arrange
        assertEquals(true,ticket.getTicketBelegt()); // prüfe das Attribut "belegt" auf "true"

    }

    @Test
    void bezahlen(){
        //Arrange
        Parkticket ticket = new Parkticket();

        //Act
        a.bezahlen(); // Makiert das Ticket als wurde bezahlt

        //Assert
        assertEquals(true,a.getTicketStatus()); // Prüfe,ob das Ticket bezahlt wurde

    }


    @Test
    public void schrankeAusgangTest(){
        //Arrange
        Parkticket ticket = new Parkticket();

        //Act
       ticket.schranke(); /* Methode schranke() setzt das Attribut "belegt" von Parkticket auf "false" beim Rausfahren,
         setze Attribut "belegt" nur auf false, wenn Ticket bezahlt wurde sonst nicht*/

        //Arrange
        assertEquals(false,ticket.getTicketBelegt());// prüfe das Attribut "belegt" auf "false"


    }










}
