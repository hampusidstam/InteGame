package se.inte.group5;

public class Hero extends Creature {

    private final int MAXIMUM_HP = 100; //TODO: Should not have max hp?
    private Weapon equippedWeapon;

    public Hero(int healthPoints, int speed) {

        super(healthPoints, speed, '@', Color.YELLOW, 10);
    }

    private void increaseHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public int getInventorySize() {
        return inventory.getSize();
    }

    public int move(char ch) {
        int dir;

        switch (ch) {
            case 'N':
                dir = 1;
                break;
            case 'E':
                dir = 2;
                break;
            case 'S':
                dir = 3;
                break;
            case 'W':
                dir = 4;
                break;
            default:
                dir = 0;
        }

        return dir;
    }

    public void pickUpItem(Object item) {
        if ((healthPoints > 90 && item instanceof Plant) || item instanceof Potion) {
            healthPoints = MAXIMUM_HP;
        }
        else if (item instanceof Plant) {
            increaseHealthPoints(((Plant) item).getEnergy());
        }
        else if (item instanceof Equipment) {
            inventory.addItem((Equipment) item);
        }
    }

    public void setEquippedWeapon() {
        for (Equipment w : inventory.getInventoryArray()) {
            if (w instanceof Weapon) {
                if (w.strength > equippedWeapon.strength) {
                    equippedWeapon = (Weapon) w;
                }
            }
        }
    }
}
