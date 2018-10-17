package se.inte.group5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;


public class PlantTest {

    private Plant p;

    @Before
    public void createPlant() {
        p = new Plant(10, Color.RED);
    }

    @Test
    public void getHealthPoints_hpIs10_true() {
        assertEquals(10, p.getHealthPoints());
    }

    @Test
    public void getHealthPoints_hpIs10_false() {
        assertNotEquals(13, p.getHealthPoints());
    }

    @Test
    public void getColor_colorIsRed_true() {
        assertEquals(Color.RED, p.getColor());
    }

}
