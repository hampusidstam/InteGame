package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeaponTest {

    Weapon weapon;

    @Before
    public void createItem() {
        weapon = new Weapon(15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Weapon_newWeaponWithNoStrength_exceptionThrown() {
        new Weapon(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Weapon_newWeaponWithOver100Strength_exceptionThrown() {
        new Weapon(101);
    }

    @Test
    public void dealDamage_strikeWeapon_15damageGiven() {
        assertEquals(15, weapon.dealDamage());
    }
}
