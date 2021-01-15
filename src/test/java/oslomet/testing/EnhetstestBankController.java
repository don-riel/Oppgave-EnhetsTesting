package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;



    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void henTransaksjoner_loggetInn() {
        //arrange
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentTransaksjoner(anyString(),anyString(),anyString())).thenReturn(konto1);

        //act
        Konto resultat = bankController.hentTransaksjoner("105010123456", "", "");

        //assert
        assertEquals(konto1, resultat);
    }

    @Test
    public void henTransaksjoner_IkkeLoggetInn() {
        //arrange

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Konto resultat = bankController.hentTransaksjoner("105010123456", "", "");

        //assert
        assertNull(resultat);
    }

    @Test
    public void hentSaldi_LoggetInn() {
        //arrange

        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentSaldi(anyString())).thenReturn(konti);

        //act
        List<Konto> resultat = bankController.hentSaldi();

        //assert
        assertEquals(konti, resultat);

    }

    @Test
    public void hentSaldi_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertNull(resultat);

    }

    @Test
    public void resistrerBetaling_LoggetInn() {
        //arrange
        int id = 123;
        double belop = 1000;
        String fraTilKontonummer = "01010110523-12345678901", dato = " ", melding = "betaling", avventer = " ", kontonummer = "01010110523";
        Transaksjon betaling = new Transaksjon(id, fraTilKontonummer, belop, dato, melding, avventer, kontonummer);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerBetaling(any(Transaksjon.class))).thenReturn("OK");

        //act
        String resultat = bankController.registrerBetaling(betaling);

        //assert
        assertEquals("OK", resultat);

    }

    @Test
    public void resistrerBetaling_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = bankController.registrerBetaling(null);

        // assert
        assertNull(resultat);

    }

    @Test
    public void hentBetalinger_LoggetInn() {
        //arrange
        int id = 123, id2 = 321;
        double belop = 1000;
        String fraTilKontonummer = "01010110523-12345678901", dato = " ", melding = "betaling", avventer = " ", kontonummer = "01010110523";
        String fraTilKontonummer2 = "12345678901-01010110523";


        List<Transaksjon> betalinger = new ArrayList<>();
        Transaksjon betaling_1 = new Transaksjon(id, fraTilKontonummer, belop, dato, melding, avventer, kontonummer);
        Transaksjon betaling_2 = new Transaksjon(id2, fraTilKontonummer2, belop,dato,melding, avventer, kontonummer);
        betalinger.add(betaling_1);
        betalinger.add(betaling_2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentBetalinger(anyString())).thenReturn(betalinger);

        //act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        //assert
        assertEquals(betalinger, resultat);
    }

    @Test
    public void hentBetalinger_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> betalinger = bankController.hentBetalinger();

        // assert
        assertNull(betalinger);
    }

    @Test
    public void utforBetaling_LoggetInn() {
        //arrange
        int id = 123, id2 = 321;
        double belop = 1000;
        String fraTilKontonummer = "01010110523-12345678901", dato = " ", melding = "betaling", avventer = " ", kontonummer = "01010110523";
        String fraTilKontonummer2 = "12345678901-01010110523";


        List<Transaksjon> betalinger = new ArrayList<>();
        Transaksjon betaling_1 = new Transaksjon(id, fraTilKontonummer, belop, dato, melding, avventer, kontonummer);
        Transaksjon betaling_2 = new Transaksjon(id2, fraTilKontonummer2, belop,dato,melding, avventer, kontonummer);
        betalinger.add(betaling_1);
        betalinger.add(betaling_2);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.utforBetaling(anyInt())).thenReturn("OK");
        when(repository.hentBetalinger(anyString())).thenReturn(betalinger);

        //act
        List<Transaksjon> resultat = bankController.utforBetaling(id);

        //assert
        assertEquals(betalinger, resultat);

    }

    @Test
    public void utforBetaling_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> betalinger = bankController.utforBetaling(123);

        // assert
        assertNull(betalinger);
    }

    @Test
    public void enre_LoggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        //act
        String resultat = bankController.endre(enKunde);

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void endre_IkkeLoggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = bankController.endre(enKunde);

        // assert
        assertNull(resultat);
    }


}

