package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MonsterTest {

    Monster m;

    @Before
    public void createMonster() {
        m = new Monster(10, 20);
    }

    @Test
    public void getHealthPoints_hpIs10_true() {
        assertEquals(10, m.getHealthPoints());
    }

    @Test
    public void getHealthPoints_hpIs10_false() {
        assertNotEquals(20, m.getHealthPoints());
    }

}
