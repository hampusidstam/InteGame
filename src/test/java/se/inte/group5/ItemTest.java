package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    Item item;

    @Before
    public void createItem() {
        item = new Item();
    }

    @Test
    public void getItem_itemType0_true() {
        assertEquals(0, item.getType());
    }

}
