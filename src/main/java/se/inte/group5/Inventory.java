package se.inte.group5;

public class Inventory {

    private Item[] inventoryArray;

    public Inventory(int slots) {
        inventoryArray = new Item[slots];
    }

    public boolean addItem(Item item) {
        for (int i = 0; i < inventoryArray.length; i++) {
            if (inventoryArray[i] == null) {
                inventoryArray[i] = item;
                return true;
            }
        }
        return false;
    }

    public Item getItem(int item) {
        if (inventoryArray[item] == null) {
            //TODO
        }
        return inventoryArray[item];
    }

    public Item[] getInventoryArray() {
        return inventoryArray;
    }

    public int getSize() {
        return inventoryArray.length;
    }

}
