package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeroTest {
    private Hero hero;

    @Before
    public void createHero_correctValues() {
        hero = new Hero(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hero_negativeHp_invalid() {
        new Hero(-1);
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
    public void heal_increaseBy10_true() {
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
    public void pickUpItem_drinkPotion_increaseHealthPointsTo100() {
        hero.takeDamage(85);
        hero.pickUpItem(new Potion());
        assertEquals(100, hero.getHealthPoints());
    }

    @Test
    public void move_standStill_dirIs0() {
        assertEquals(0, hero.move('0'));
    }

    @Test
    public void move_moveOneStepNorth_dirIs1() {
        assertEquals(1, hero.move('N'));
    }

    @Test
    public void move_moveOneStepEast_dirIs2() {
        assertEquals(2, hero.move('E'));
    }

    @Test
    public void move_moveOneStepSouth_dirIs3() {
        assertEquals(3, hero.move('S'));
    }

    @Test
    public void move_moveOneStepWest_dirIs4() {
        assertEquals(4, hero.move('W'));
    }

    @Test
    public void getInventorySize_returnSize_sizeIs10() {
        assertEquals(10, hero.getInventorySize());
    }

    @Test
    public void pickUpItem_pickUpWeapon_weaponStoredInInventoryAndIsEquipped() {
        hero.pickUpItem(new Weapon(10));
        assertEquals(new Weapon(10), hero.inventory.getItem(0));
        assertEquals(new Weapon(10), hero.getEquippedWeapon());
    }

    @Test
    public void pickUpItem_pickUpArmor_armorStoredInInventoryAndIsEquipped() {
        hero.pickUpItem(new Armor(15));
        assertEquals(new Armor(15), hero.inventory.getItem(0));
        assertEquals(new Armor(15), hero.getEquippedArmor());
    }

    @Test
    public void pickUpItem_pickUpArmor_strongerArmorGetsEquipped() {
        hero.pickUpItem(new Armor(15));
        hero.pickUpItem(new Armor(22));
        assertEquals(new Armor(22), hero.getEquippedArmor());
    }

    @Test
    public void pickUpItem_pickUpWeapon_doesNotAffectHp() {
        hero.takeDamage(25);
        hero.pickUpItem(new Armor(5));
        assertEquals(75, hero.getHealthPoints());
    }

    @Test
    public void setEquipment_pickUpFirstWeapon_isEquipped() {
        hero.pickUpItem(new Weapon(5));
        assertEquals(5, hero.getEquippedWeapon().dealDamage());
    }

    @Test
    public void setEquipment_pickUpSecondWeaponThatIsWeaker_firstIsEquipped() {
        hero.pickUpItem(new Weapon(99));
        hero.pickUpItem(new Weapon(1));
        assertEquals(99, hero.getEquippedWeapon().dealDamage());
    }

    @Test
    public void setEquipment_threeWeaponsInInventory_equippedWithStrongest() {
        hero.pickUpItem(new Weapon(50));
        hero.pickUpItem(new Weapon(89));
        hero.pickUpItem(new Weapon(42));
        assertEquals(89, hero.getEquippedWeapon().dealDamage());
    }
}