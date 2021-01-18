package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;


import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Enhetstest_Sikkerhet {

    @InjectMocks
    private Sikkerhet sikkerhet;

    @Mock
    private BankRepository repository;

    @Mock
    private HttpSession session;



    @Test
    public void sjekkLoggInn_Ok() {
        //arrange
        String personnummer = "01010110523";
        String passorod = "HeiHei";

        when(repository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        session.setAttribute(anyString(), anyString());

        //act
        String resultat = sikkerhet.sjekkLoggInn(personnummer, passorod);

        //assert
        assertEquals("OK", resultat);

    }

    @Test
    public void sjekkLoggInn_Feil() {
        //arrange
        String personnummer = "01010110523";
        String passorod = "HeiHei";

        when(repository.sjekkLoggInn(anyString(), anyString())).thenReturn("Feil");


        //act
        String resultat = sikkerhet.sjekkLoggInn(personnummer, passorod);

        //assert
        assertEquals("Feil i personnummer eller passord", resultat);

    }

    @Test
    public void sjekkLoggInn_FeilPersonnummer() {
        //arrange
        String personnummer = " ";
        String passorod = "HeiHei";

        //act
        String resultat = sikkerhet.sjekkLoggInn(personnummer, passorod);

        //assert
        assertEquals("Feil i personnummer", resultat);

    }

    @Test
    public void sjekkLoggInn_FeilPassord() {
        //arrange
        String personnummer = "01010110523";
        String passorod = " ";


        //act
        String resultat = sikkerhet.sjekkLoggInn(personnummer, passorod);

        //assert
        assertEquals("Feil i passord", resultat);

    }

    @Test
    public  void loggUt() {

        //act
        sikkerhet.loggUt();

        //assert

        assertNull(session.getAttribute("Innlogget"));
    }

    @Test
    public void loggInnAdmin_LoggetInn() {
        //arrange
        String bruker = "Admin";
        String passord = "Admin";


        //act
        String resultat = sikkerhet.loggInnAdmin(bruker, passord);

        //assert
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void loggInnAdmin_IkkeLoggetInn() {
        //arrange
        String bruker = "Admin";
        String passord = "passord";

        //act
        String resultat = sikkerhet.loggInnAdmin(bruker, passord);

        //assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void loggetInn_Innlogget() {
        //arrange
        when(session.getAttribute(anyString())).thenReturn("Innlogget");

        //act
        String resultat = sikkerhet.loggetInn();

        //act
        assertEquals("Innlogget", resultat);

    }

    @Test
    public void loggetInn_IkkeInnlogget() {
        //arrange
        when(session.getAttribute(anyString())).thenReturn(null);

        //act
        String resultat = sikkerhet.loggetInn();

        //act
        assertNull(resultat);

    }
}
