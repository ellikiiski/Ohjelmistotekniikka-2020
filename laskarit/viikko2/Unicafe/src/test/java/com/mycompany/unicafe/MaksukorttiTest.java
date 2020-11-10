package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortinSaldometodiPalauttaaOikeanSaldon() {
        assertTrue(1000 == kortti.saldo());
    }
    
    @Test
    public void kortinSaldoKasvaaOikein() {
        kortti.lataaRahaa(200);
        assertEquals("saldo: 12.0", kortti.toString());
    }
    
    @Test
    public void ostaminenVahentaaSaldoaOikein() {
        kortti.otaRahaa(200);
        assertEquals("saldo: 8.0", kortti.toString());
    }
    
    @Test
    public void ostaminenEiOnnistuJosEiOoTarpeeksFyrkkaa() {
        kortti.otaRahaa(1200);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void liikaaOstaminenPalauttaaFalsen() {
        assertTrue(!(kortti.otaRahaa(1200)));
    }
    
    @Test
    public void onnistunutOstaminenPalauttaaTruen() {
        assertTrue(kortti.otaRahaa(200));
    }
}
