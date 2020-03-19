/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hiira
 */
public class KassapaateTest {
    private Kassapaate paate;
    private Maksukortti kortti;
    private Maksukortti koyhaKortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        //kassassa rahaa 1000€
        kortti = new Maksukortti(1000);
        //kortilla 10€
        koyhaKortti = new Maksukortti(200);
        //kortilla 2€
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void alussaRahaJaLounaatOikein() {
        assertEquals(100000,paate.kassassaRahaa());
        assertEquals(0,paate.edullisiaLounaitaMyyty()+paate.maukkaitaLounaitaMyyty());
    }
    
    //Edullisesti käteisosto
    @Test
    public void EdullisenKateisostoKasvattaaKassanSaldoaJosMaksuRiittava() {
        paate.syoEdullisesti(300);
        assertEquals(100240,paate.kassassaRahaa());
    }
    
    @Test
    public void EdullisenKateisostossaOikeaVaihtorahaJosMaksuRiittava() {
        assertEquals(60,paate.syoEdullisesti(300));
    }
    
    @Test
    public void EdullisenKateisostossaLoudaidenMaaraKasvaaJosMaksuRiittava() {
        paate.syoEdullisesti(300);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void EdullisenKateisostossaKassanRahamaaraEiMuutuJosMaksuEiRiittava() {
        paate.syoEdullisesti(200);
        assertEquals(100000,paate.kassassaRahaa());
    }
    
    @Test
    public void EdullisenKateisostossaKaikkiRahatPalautetaanJosMaksuEiRiittava() {
        assertEquals(200,paate.syoEdullisesti(200));
    }
    
    @Test
    public void EdullisesKateisostossaMyydytLounaatEiMuutuJosMaksuEiRiittava() {
        paate.syoEdullisesti(200);
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    
    //Maukkaasti käteisosto:
     @Test
    public void MaukkaastiKateisostoKasvattaaKassanSaldoaJosMaksuRiittava() {
        paate.syoMaukkaasti(500);
        assertEquals(100400,paate.kassassaRahaa());
    }
    
    @Test
    public void MaukkaanKateisostossaOikeaVaihtorahaJosMaksuRiittava() {
        assertEquals(100,paate.syoMaukkaasti(500));
    }
    
    @Test
    public void MaukkaanKateisostossaLounaidenMaaraKasvaaJosMaksuRiittava() {
        paate.syoMaukkaasti(500);
        assertEquals(1,paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void MaukkaanKateisostossaKassanRahamaaraEiMuutuJosMaksuEiRiittava() {
        paate.syoMaukkaasti(200);
        assertEquals(100000,paate.kassassaRahaa());
    }
    
    @Test
    public void MaukkaanKateisostossaKaikkiRahatPalautetaanJosMaksuEiRiittava() {
        assertEquals(200,paate.syoMaukkaasti(200));
    }
    
    @Test
    public void MaukkaanKateisostossaMyydytLounaatEiMuutuJosMaksuEiRiittava() {
        paate.syoMaukkaasti(200);
        assertEquals(0,paate.maukkaitaLounaitaMyyty());
    }
    
    //Maksukortti Edullisille:
    
    @Test
    public void EdullisenKorttiostossaSummaVeloitettuJosRahaa() {
        paate.syoEdullisesti(kortti);
        assertEquals(760,kortti.saldo());
        
    }
    
    @Test
    public void EdullisenKorttiostossaPalautetaanTrueJosRahaa() {
        assertEquals(true,paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void EdullisenKorttiostossaMyydytLounaatKasvaaJosRahaa() {
        paate.syoEdullisesti(kortti);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void EdullisenKorttiostossaKortinRahaMääräEiMuutuJosEiRahaa() {
        paate.syoEdullisesti(koyhaKortti);
        assertEquals(200,koyhaKortti.saldo());
    }
    
    @Test
    public void EdullisenKorttiostossaMyydytLounaatEiMuutuJosEiRahaa() {
        paate.syoEdullisesti(koyhaKortti);
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void EdullisenKorttiostossaPalautetaanFalseJosEiRahaa() {
        assertEquals(false,paate.syoEdullisesti(koyhaKortti));
    }
    
    @Test
    public void EdullisenKorttiostossaKassanRahamaaraEiMuutuJosEiRahaa() {
        paate.syoEdullisesti(koyhaKortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    
    
    //Maksukortti maukkaalle:
     @Test
    public void MaukkaanKorttiostossaSummaVeloitettuJosRahaa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(600,kortti.saldo());
        
    }
    
    @Test
    public void MaukkaanKorttiostossaPalautetaanTrueJosRahaa() {
        assertEquals(true,paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void MaukkaanKorttiostossaMyydytLounaatKasvaaJosRahaa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1,paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void MaukkaanKorttiostossaKortinRahaMääräEiMuutuJosEiRahaa() {
        paate.syoMaukkaasti(koyhaKortti);
        assertEquals(200,koyhaKortti.saldo());
    }
    
    @Test
    public void MaukkaanKorttiostossaMyydytLounaatEiMuutuJosEiRahaa() {
        paate.syoMaukkaasti(koyhaKortti);
        assertEquals(0,paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void MaukkaanKorttiostossaPalautetaanFalseJosEiRahaa() {
        assertEquals(false,paate.syoMaukkaasti(koyhaKortti));
    }
    
    @Test
    public void MaukkaanKorttiostossaKassanRahamaaraEiMuutuJosEiRahaa() {
        paate.syoMaukkaasti(koyhaKortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    
    
    //Kortin lataaminen
    @Test
    public void kortilleRahanLatausKasvattaaKassanSummaa() {
        paate.lataaRahaaKortille(kortti, 200);
        assertEquals(100200,paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleRahanLatausMuuttaaKortinSaldoa() {
        paate.lataaRahaaKortille(kortti, 200);
        assertEquals(1200,kortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSumaa() {
        paate.lataaRahaaKortille(kortti, -200);
        assertEquals(100000,paate.kassassaRahaa());
        assertEquals(1000,kortti.saldo());
    }
}
