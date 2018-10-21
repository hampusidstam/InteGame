package se.inte.group5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {

    Inventory inventory;

    @Before
    public void createInventory() {
        inventory = new Inventory(5);
    }

    @Test
    public void getInventoryArray_Empty5SlotInventory_true() {
        Item[] expected = new Item[5];
        Assert.assertArrayEquals(expected, inventory.getInventoryArray());
    }

}
