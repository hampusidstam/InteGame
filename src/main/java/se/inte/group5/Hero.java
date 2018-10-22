package se.inte.group5;

public class Hero extends Creature {

    private final int MAXIMUM_HP = 100; //TODO: Should not have max hp?
    protected Inventory inventory;

    public Hero(int healthPoints, int speed) {

        super(healthPoints, speed, '@', Color.YELLOW);
        inventory = new Inventory(10);
    }

    public void pickUpItem(Object item) {
        if ((healthPoints > 90 && item instanceof Plant) || item instanceof Potion) {
            healthPoints = MAXIMUM_HP;
        }
        else if (item instanceof Plant) {
            increaseHealthPoints(((Plant) item).getHealthPoints());
        }
        else if (item instanceof Equipment) {
            inventory.addItem((Equipment) item);
        }
    }

    private void increaseHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public int getInventorySize() {
        return inventory.getSize();
    }
}
