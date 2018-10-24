package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class PlantTest {

    private Plant plant;

    @Before
    public void createPlant() {
        plant = new Plant();
    }

    @Test
    public void getEnergy_plantEnergyIs10_true() {
        assertEquals(10, plant.getEnergy());
    }

    @Test
    public void getEnergy_plantEnergyIs10_false() {
        assertNotEquals(13, plant.getEnergy());
    }

    @Test
    public void getColor_colorIsGreen_true() {
        assertEquals(Color.GREEN, plant.getColor());
    }

    @Test
    public void getColor_colorIsBlue_false() {
        assertNotEquals(Color.BLUE, plant.getColor());
    }

}
