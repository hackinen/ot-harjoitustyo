package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(5);
        assertEquals(15,kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaa() {
        kortti.otaRahaa(5);
        assertEquals(5,kortti.saldo());
    }
    
    @Test
    public void saldoEiMuutuJosEiVaraa() {
        kortti.otaRahaa(11);
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void otaRahaaTulostaaFalse() {
        assertEquals(false,kortti.otaRahaa(11));
    }
    
    @Test
    public void otaRahaaTulostaaTrue() {
        assertEquals(true,kortti.otaRahaa(2));
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals("saldo: 0.10",kortti.toString());
    }
}
