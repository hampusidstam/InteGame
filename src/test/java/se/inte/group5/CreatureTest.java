package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CreatureTest {
    private Creature a;
    private Creature creatureFullHp;

    @Before
    public void createCreatures() {
        a = new Creature(13, 20);
        creatureFullHp = new Creature(100, 5);
    }

    @Test
    public void getHealthPoints_hpIs13_true() {
        assertEquals(13, a.getHealthPoints());
    }

    @Test
    public void getHealthPoints_hpIs13_false() {
        assertNotEquals(10, a.getHealthPoints());
    }

    @Test
    public void getHealthPoints_negativeHp_invalid() {
        //TODO: Negative HP in constructor should not be possible
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs20_true() {
        assertEquals(20, a.getMaxSpeed());
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs10_false() {
        assertNotEquals(10, a.getMaxSpeed());
    }

    @Test
    public void takeDamage_decreaseHp_lose15hp() {
        creatureFullHp.takeDamage(15);
        assertEquals(85, creatureFullHp.getHealthPoints());
    }

    @Test
    public void takeDamage_decreaseByNegative_healthPointsNotChanged() {
        creatureFullHp.takeDamage(-1);
        assertEquals(100, creatureFullHp.getHealthPoints());
    }
}
