package se.inte.group5;

public class Inventory {

    private Item[] inventoryArray;

    public Inventory(int slots) {
        inventoryArray = new Item[slots];
    }

    public Item[] getInventoryArray() {
        return inventoryArray;
    }

}
