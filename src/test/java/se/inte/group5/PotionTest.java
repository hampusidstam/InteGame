package se.inte.group5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PotionTest {

    @Test
    public void healPotency_givePotionToHero_healFull() {
        Potion potion = new Potion();
        assertEquals(100, potion.getHealthPoints());
    }

}
