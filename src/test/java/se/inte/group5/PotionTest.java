package se.inte.group5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PotionTest {

    @Test
    public void getEnergy_newPotion100Energy_energyIs100() {
        Potion potion = new Potion();
        assertEquals(100, potion.getEnergy());
    }
}
