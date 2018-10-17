package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArmorTest {

    Armor armor;

    @Before
    public void createArmor() {
        armor = new Armor(10);
    }

    @Test
    public void armorResistance_adds10hp_true() {
        assertEquals(10, armor.getResistance());
    }
}
