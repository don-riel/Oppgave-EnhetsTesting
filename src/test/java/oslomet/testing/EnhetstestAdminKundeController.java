package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;



    @Test
    public void hentAlle_LoggetInn() {

        // arrange
        List<Kunde> kunder = new ArrayList<>();
        Kunde kunde1 = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        Kunde kunde2 = new Kunde("105010123456",
                "Per", "Hansen", "Baerumsveien 22", "1234",
                "Baerum", "22224444", "HeiHei");
        kunder.add(kunde1);
        kunder.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentAlleKunder()).thenReturn(kunder);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertEquals(kunder, resultat);
    }

    @Test
    public void hentAlle_IkkeLoggetInn() {
        //arrange

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Kunde> resultat = adminKundeController.hentAlle();

        //assert
        assertNull(resultat);
    }

    @Test
    public void lagreKunde_LoggetInn() {
        //arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerKunde(any(Kunde.class))).thenReturn("OK");

        //act
        String resultat = adminKundeController.lagreKunde(enKunde);

        //assert
        assertEquals("OK", resultat);

    }

    @Test
    public void lagreKunde_IkkeLoggetInn() {
        //arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKundeController.lagreKunde(enKunde);

        //assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void endre_LoggetInn() {
        //arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        //act
        String resultat = adminKundeController.endre(enKunde);

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void endre_IkkeLoggetInn() {
        //arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKundeController.endre(enKunde);

        //assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void slett_LoggetInn() {
        //arrange
        String personnummer = "01010110523";

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.slettKunde(anyString())).thenReturn("OK");

        //act
        String resultat = adminKundeController.slett("01010110523");

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void slett_IkkeLoggetInn() {
        //arrange
        String personnummer = "01010110523";

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKundeController.slett("01010110523");

        //assert
        assertEquals("Ikke logget inn", resultat);
    }





}

