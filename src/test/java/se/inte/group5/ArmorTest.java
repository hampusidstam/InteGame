package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ArmorTest {

    private Armor armor;

    @Before
    public void createArmor() {
        armor = new Armor(10);
    }

    @Test
    public void armorResistance_adds10hp_true() {
        assertEquals(10, armor.getResistance());
    }

    @Test
    public void equals_differentResistance_notSameArmor() {
        assertNotEquals(armor, new Armor(5));
    }

    @Test
    public void equals_sameResistance_sameArmor() {
        assertEquals(armor, new Armor(10));
    }

    @Test
    public void equals_TwoDifferentEquipments_false() {
        assertFalse(armor.equals(new Weapon(10)));
    }
}
