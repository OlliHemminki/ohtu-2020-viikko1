package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuus(){
        Varasto uusi1 = new Varasto(-10);
        assertEquals(0, uusi1.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusKuormitetusti(){
        Varasto uusi2 = new Varasto(-10, 0);
        assertEquals(0, uusi2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusOikeinKuormitetussaKonstruktorissa(){
        Varasto uusi3 = new Varasto(123, 0);
        assertEquals(123, uusi3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoNollaksi(){
        Varasto uusi4 = new Varasto(100.0, -10.0);
        assertTrue(uusi4.getSaldo() == 0.0);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa(){
        varasto.lisaaVarastoon(4);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(-3);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoKorkeintaanTilavuus(){
        varasto.lisaaVarastoon(123);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoEiOnnistu(){
        varasto.lisaaVarastoon(8);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto.otaVarastosta(-2);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void voidaanOttaaVainSaldonVerran(){
        varasto.lisaaVarastoon(8);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        double otto = varasto.otaVarastosta(13);
        assertEquals(8, otto, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysToimii(){
        Varasto uudehko = new Varasto(10);
        uudehko.lisaaVarastoon(7);
        assertEquals(7, uudehko.getSaldo(), vertailuTarkkuus);
        assertEquals(10, uudehko.getTilavuus(), vertailuTarkkuus);
        String haluttu = "saldo = 7.0, vielä tilaa 3.0";
        String palautettu = uudehko.toString();
        assertTrue(haluttu.equals(palautettu));
    }
}