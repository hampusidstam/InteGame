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
    public void pickUpPotion_drinkPotion_increaseHealthPointsBy100() {
        hero.takeDamage(85);
        hero.pickUpItem(new Potion());
        assertEquals(100, hero.getHealthPoints());
    }

}