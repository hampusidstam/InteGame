package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class MonsterTest {
    private Monster monster;
    private Monster monsterFullHp;

    @Before
    public void createMonster() {
        monster = new Monster(13, 20);
        monsterFullHp = new Monster(100, 5);
    }

    @Test
    public void getHealthPoints_healthPointsIs13_true() {
        assertEquals(13, monster.getHealthPoints());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Monster_negativeHp_invalid() {
        new Monster(-1, 5);
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs20_true() {
        assertEquals(20, monster.getMaxSpeed());
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs10_false() {
        assertNotEquals(10, monster.getMaxSpeed());
    }

    @Test
    public void takeDamage_decreaseHp_lose15hp() {
        monsterFullHp.takeDamage(15);
        assertEquals(85, monsterFullHp.getHealthPoints());
    }

    @Test
    public void takeDamage_hitMonsterWithAllHp_monsterDies() {
        monster.takeDamage(monster.getHealthPoints());
        assertFalse(monster.isAlive());
    }

    @Test
    public void takeDamage_decreaseByNegative_healthPointsNotChanged() {
        monsterFullHp.takeDamage(-1);
        assertEquals(100, monsterFullHp.getHealthPoints());
    }

}
