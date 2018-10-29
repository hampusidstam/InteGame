package se.inte.group5;

public class Inventory {

    private Equipment[] inventoryArray;

    public Inventory(int slots) {
        if (slots < 1) {
            slots = 1;
        }
        inventoryArray = new Equipment[slots];
    }

    public boolean addItem(Equipment item) {
        for (int i = 0; i < inventoryArray.length; i++) {
            if (inventoryArray[i] == null) {
                inventoryArray[i] = item;
                return true;
            }
        }
        return false;
    }

    public Equipment getItem(int item) {
        if (inventoryArray[item] == null) {
            return null;
        }
        return inventoryArray[item];
    }

    public Equipment[] getInventoryArray() {
        return inventoryArray.clone();
    }

    public int getSize() {
        return inventoryArray.length;
    }

}
