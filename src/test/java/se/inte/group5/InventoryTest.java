package se.inte.group5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class InventoryTest {

    Inventory inventory;

    @Before
    public void createInventory() {
        inventory = new Inventory(5);
    }

    @Test
    public void getInventoryArray_Empty5SlotInventory_true() {
        Assert.assertArrayEquals(new Item[5], inventory.getInventoryArray());
    }

    //Kollar hittills endast ifall det finns ett nytt item i arrayen, men inte ifall det är lika. Krävs isåfall equals metod för item/armor.
    @Test
    public void addItem_Empty5SlotInventory_true() {
        assertTrue(inventory.addItem(new Armor(20)));
        assertNotEquals(null, inventory.getInventoryArray()[0]);
    }

    @Test
    public void addItem_Full5SlotInventory_false() {
        for (int i = 0; i < inventory.getInventoryArray().length; i++) {
           inventory.addItem(new Armor(0));
        }
        assertFalse(inventory.addItem(new Weapon(10)));
    }

}