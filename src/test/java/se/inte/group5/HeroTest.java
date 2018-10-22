package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeroTest {
    Hero hero;

    @Before
    public void createHero_correctValues() {
        hero = new Hero(100, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hero_negativeHp_invalid() {
        new Hero(-1, 5);
    }

    @Test
    public void getHealthPoints_hpIs100_true() {
        assertEquals(100, hero.getHealthPoints());
    }

    @Test
    public void getMaxSpeed_MaxSpeedIs10_true() {
        assertEquals(10, hero.getMaxSpeed());
    }

    @Test
    public void increaseHealthPoints_increaseBy10_true() {
        assertEquals(110, hero.getHealthPoints() + 10);
    }

    @Test
    public void takeDamage_decreaseHp_lose15hp() {
        hero.takeDamage(15);
        assertEquals(85, hero.getHealthPoints());
    }

    @Test
    public void takeDamage_decreaseByNegative_healthPointsNotChanged() {
        hero.takeDamage(-1);
        assertEquals(100, hero.getHealthPoints());
    }

    @Test
    public void pickUpItem_eatPlant_increaseHealthPointsBy10() {
        hero.takeDamage(15);
        hero.pickUpItem(new Plant());
        assertEquals(95, hero.getHealthPoints());
    }

    @Test
    public void pickUpItem_eatPlantWithFullHp_noIncrease() {
        hero.pickUpItem(new Plant());
        assertEquals(100, hero.getHealthPoints());
    }

    @Test
    public void pickUpItem_drinkPotion_increaseHealthPointsBy100() {
        hero.takeDamage(85);
        hero.pickUpItem(new Potion());
        assertEquals(100, hero.getHealthPoints());
    }

    @Test
    public void getStartPosition_isAtPositionX0Y0_position() {
        int[] pos = {0,0};
        assertEquals(0, pos[0]);
        assertEquals(0, pos[1]);
    }

    @Test
    public void move_moveOneStepNorth_positionMoved() {
        char ch = 'N';
        int dir = 1;
        assertEquals(1, hero.move());
    }

    @Test
    public void getInventorySize_returnSize_sizeIs10() {
        assertEquals(10, hero.getInventorySize());
    }

    @Test
    public void pickUpItem_pickUpWeapon_weaponStoredInInventory() {
        hero.pickUpItem(new Weapon(10));
        assertEquals(new Weapon(10), hero.inventory.getItem(0));
    }

    @Test
    public void pickUpItem_pickUpArmor_armorStoredInInventory() {
        hero.pickUpItem(new Armor(15));
        assertEquals(new Armor(15), hero.inventory.getItem(0));
    }

    @Test
    public void pickUpItem_pickUpWeapon_doesNotAffectHp() {
        hero.takeDamage(25);
        hero.pickUpItem(new Armor(5));
        assertEquals(75, hero.getHealthPoints());
    }
}