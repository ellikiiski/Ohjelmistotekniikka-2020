
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUpKassapaate() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void uudessaKassapaatteessaOikeaMaaraRahaa() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void uudessaKassapaatteessaOikeaMaaraOstoja() {
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0 && kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void oikeaVaihtorahaKatisellaMaksettaessaEdullinenLounas() {
        assertTrue(kassa.syoEdullisesti(500) == 260);  
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaOstettaessaKateisella() {
        kassa.syoEdullisesti(500);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void josOnNiinKoyhaEtteiOleKateistaEdesEdulliseenLounaaseenNiinFyrkattPalautataanKokonaan() {
        assertTrue(kassa.syoEdullisesti(100) == 100);
    }
    
    @Test
    public void josEdulliseenLounaaseenkaanEiRiitaKateinenNiinMyytyjenLounaidenMaaraEiKasva() {
        kassa.syoEdullisesti(100);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void oikeaVaihtorahaKatisellaMaksettaessaMaukasLounas() {
        assertTrue(kassa.syoMaukkaasti(500) == 100);
    }

    @Test
    public void myytyjenaMaukkaidenLounaidenMaaraKasvaaOstettaessaKateisella() {
        kassa.syoMaukkaasti(500);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void josEiOoTarpeeksiKateistaSyodaMaukkaastiNiinFyrktPalautataanKokonaan() {
        assertTrue(kassa.syoMaukkaasti(100) == 100);
    }

    @Test
    public void josMaukaaseenLounaaseenkaanEiRiitaKateinenNiinMyytyjenLounaidenMaaraEiKasva() {
        kassa.syoMaukkaasti(100);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Before
    public void setUpMaksukortti() {
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void toimivaltaKortiltaVeloitetaanEdullinenLounasOikein() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullinenKorttiostoKasvattaaMyytyjaLounaita() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void josEiSaldoRiitaEdesEdulliseenLounaaseenNiinOstoEiOnnistu() {
        kortti.otaRahaa(900);
        assertTrue(!kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void josEiSaldoRiitaEdesEdulliseenLounaaseenNiinKortiltaEiVeloitetaMitaan() {
        kortti.otaRahaa(900);
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 100);
    }
    
    @Test
    public void josEiSaldoRiitaEdesEdulliseenLounaaseenNiinMyytyjenMaaraEiKasva() {
        kortti.otaRahaa(900);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void toimivaltaKortiltaVeloitetaanMaukasLounasOikein() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void maukasKorttiostoKasvattaaMyytyjaLounaita() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void josEiSaldoRiitaMaukkaaseenLounaaseenNiinOstoEiOnnistu() {
        kortti.otaRahaa(900);
        assertTrue(!kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void josEiSaldoRiitaMaukkaaseenLounaaseenNiinKortiltaEiVeloitetaMitaan() {
        kortti.otaRahaa(900);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
    }

    @Test
    public void josEiSaldoRiitaMaukkaaseenLounaaseenNiinMyytyjenMaaraEiKasva() {
        kortti.otaRahaa(900);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void korttiostoEiLisaaKassanRahamaaraa() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kortilleLadatessaKortinSaldoKasvaa() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertTrue(kortti.saldo() == 1010);
    }
    
    @Test
    public void kortilleLadatessaKortinKassanRahamaaraKasvaa() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertTrue(kassa.kassassaRahaa() == 100010);
    }
    
    @Test
    public void nagatiivisenSummanLataaminenKortilleEiOnnistu() {
        kassa.lataaRahaaKortille(kortti, -10);
        assert(kassa.kassassaRahaa() == 100000 && kortti.saldo() == 1000);
    }

}
