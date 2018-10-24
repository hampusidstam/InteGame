package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WaterTest {

    private Water wt;

    @Before
    public void createWater() {
        wt = new Water();
    }

    @Test
    public void getSymbol_symbolIsW_true() {
        assertEquals('~', wt.getSymbol());
    }

    @Test
    public void getColor_colorIsBlue_true() {
        assertEquals(Color.BLUE, wt.getColor());
    }

}

