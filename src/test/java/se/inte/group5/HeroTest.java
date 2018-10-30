package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HeroTest {
    private Hero hero;

    @Before
    public void createHero_correctValues() {
        hero = new Hero(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hero_negativeHp_IllegalArgumentException() {
        new Hero(-10);
    }

    @Test
    public void hero_HeroWith50Hp_true() {
        Hero temp = new Hero(50);
        assertNotNull(temp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hero_150Hp_IllegalArgumentException() {
        new Hero(150);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hero_0Hp_IllegalArgumentException() {
        new Hero(0);
    }

    @Test
    public void getHealthPoints_hpIs100_true() {
        assertEquals(100, hero.getHealthPoints());
    }

    @Test
    public void heal_healFor50_true() {
        hero.takeDamage(50);
        hero.heal(50);
        assertEquals(100, hero.getHealthPoints());
    }

    @Test(expected = IllegalArgumentException.class)
    public void heal_negativeHeal_IllegalArgumentException() {
        hero.heal(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heal_healFor0_IllegalArgumentException() {
        hero.heal(0);
    }

    @Test
    public void heal_HealMoreThanMaxHpMinusCurrentHp_HpIsMax() {
        hero.takeDamage(50);
        hero.heal(70);
        assertEquals(100, hero.getHealthPoints());
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

    @Test(expected = IllegalStateException.class)
    public void move_heroHasNoPosition_throwsIllegalArgumentException(){
        hero.setDirectionInput('N');
        hero.move();
    }

    @Test
    public void setDirectionInput_directionInputIsN_True(){
        hero.setDirectionInput('N');
        assertEquals(1, hero.getDirectionInput());
    }

    @Test
    public void setDirectionInput_directionInputIsE_True(){
        hero.setDirectionInput('E');
        assertEquals(2, hero.getDirectionInput());
    }

    @Test
    public void setDirectionInput_directionInputIsS_True(){
        hero.setDirectionInput('S');
        assertEquals(3, hero.getDirectionInput());
    }

    @Test
    public void setDirectionInput_directionInputIsW_True(){
        hero.setDirectionInput('W');
        assertEquals(4, hero.getDirectionInput());
    }

    @Test
    public void setDirectionInput_directionInputIs0_True(){
        hero.setDirectionInput('0');
        assertEquals(0, hero.getDirectionInput());
    }

    @Test
    public void setDirectionInput_directionInputIs¤_True(){
        hero.setDirectionInput('¤');
        assertEquals(0, hero.getDirectionInput());
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
        assertEquals(5, hero.getEquippedWeapon().getDamage());
    }

    @Test
    public void setEquipment_pickUpSecondWeaponThatIsWeaker_firstIsEquipped() {
        hero.pickUpItem(new Weapon(99));
        hero.pickUpItem(new Weapon(1));
        assertEquals(99, hero.getEquippedWeapon().getDamage());
    }

    @Test
    public void setEquipment_threeWeaponsInInventory_equippedWithStrongest() {
        hero.pickUpItem(new Weapon(50));
        hero.pickUpItem(new Weapon(89));
        hero.pickUpItem(new Weapon(42));
        assertEquals(89, hero.getEquippedWeapon().getDamage());
    }

    @Test
    public void pickUpItem_statematchine_test1() {
        hero.pickUpItem(new Weapon(50));
        hero.pickUpItem(new Weapon(15));
        hero.pickUpItem(new Weapon(100));
        hero.pickUpItem(new Armor(30));
        hero.pickUpItem(new Armor(56));
        hero.pickUpItem(new Weapon(75));
        assertEquals(new Weapon(100), hero.getEquippedWeapon());
        assertEquals(new Armor(56), hero.getEquippedArmor());
    }

    @Test
    public void pickUpItem_statemachine_test2() {
        hero.pickUpItem(new Armor(5));
        hero.pickUpItem(new Armor(3));
        hero.pickUpItem(new Armor(88));
        hero.pickUpItem(new Weapon(75));
        hero.pickUpItem(new Armor(50));
        hero.pickUpItem(new Weapon(98));
        assertEquals(new Weapon(98), hero.getEquippedWeapon());
        assertEquals(new Armor(88), hero.getEquippedArmor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void pickUpItem_tryPickingUpNull_IllegalArgumentException() {
        hero.pickUpItem(null);
    }

    @Test
    public void getStrength_EquipmentEmtpy_0strength(){
        Hero hero = new Hero(100);
        assertEquals(0, hero.getStrength());
    }

    @Test
    public void getStrength_EquipmentWeapon_5Strength(){
        Hero hero = new Hero(100);
        hero.pickUpItem(new Weapon(5));
        assertEquals(5, hero.getStrength());
    }

    @Test
    public void getStrength_EquipmentWeaponArmor_10Strength(){
        Hero hero = new Hero(100);
        hero.pickUpItem(new Weapon(5));
        hero.pickUpItem(new Armor(5));
        assertEquals(10, hero.getStrength());
    }
}