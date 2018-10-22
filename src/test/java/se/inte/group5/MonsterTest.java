package se.inte.group5;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void takeDamage_decreaseByNegative_healthPointsNotChanged() {
        monsterFullHp.takeDamage(-1);
        assertEquals(100, monsterFullHp.getHealthPoints());
    }

    @Test
    public void isAlive_killMonster_isNotAlive() {
        monster.takeDamage(monster.getHealthPoints());
        assertFalse(monster.isAlive());
    }

    @Test
    public void move_moveInDirectionOptionNotAbove4_positionChanged() {
        assertTrue(monster.move() > 4);
    }

    @Test
    public void move_moveInDirectionOptionNotNegative_positionChanged() {
        assertTrue(monster.move() < 0);
    }

    @Test
    public void setEquipment_addEquipment_strengthNotLowerThan1() {
        assertFalse(monster.getEquipment().strength < 1);
    }

    @Test
    public void setEquipment_addEquipment_strengthNotHigherThan100() {
        assertFalse(monster.getEquipment().strength > 100);
    }

    @Ignore
    @Test
    public void setEquipment_addWeaponWhenCreated_equippedWithWeapon() {
        assertEquals(new Weapon(10), monster.getEquipment());
    }

    @Ignore
    @Test
    public void setEquipment_addWeapon_equippedWithWeapon() {
        assertEquals(new Weapon(10), monster.getEquipment());
    }
}
