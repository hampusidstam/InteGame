package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MonsterTest {
    private Monster m;
    private Monster monsterFullHp;

    @Before
    public void createMonster() {
        m = new Monster(13, 20, 'X', Color.BLUE);
        monsterFullHp = new Monster(100, 5, 'X', Color.BLUE);
    }

    @Test
    public void getHealthPoints_hpIs13_true() {
        assertEquals(13, m.getHealthPoints());
    }

    @Test
    public void getHealthPoints_negativeHp_invalid() {
        //TODO: Negative HP in constructor should not be possible
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs20_true() {
        assertEquals(20, m.getMaxSpeed());
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs10_false() {
        assertNotEquals(10, m.getMaxSpeed());
    }

    @Test
    public void takeDamage_decreaseHp_lose15hp() {
        monsterFullHp.takeDamage(15);
        assertEquals(85, monsterFullHp.getHealthPoints());
    }

    @Test
    public void takeDamage_decreaseByNegative_healthPointsNotChanged() {
        monsterFullHp.takeDamage(-1);
        assertEquals(100, monsterFullHp.getHealthPoints());
    }

}
