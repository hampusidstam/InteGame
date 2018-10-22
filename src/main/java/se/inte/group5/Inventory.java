package se.inte.group5;

public class Inventory {

    private Equipment[] inventoryArray;

    public Inventory(int slots) {
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

    public Equipment[] getInventoryArray() {
        return inventoryArray;
    }

    public int getSize() {
        return inventoryArray.length;
    }

}
