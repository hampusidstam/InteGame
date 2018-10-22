package se.inte.group5;

public class Hero extends Creature {

    private final int MAXIMUM_HP = 100; //TODO: Should not have max hp?

    public Hero(int healthPoints, int speed) {

        super(healthPoints, speed, '@', Color.YELLOW, 10);
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

    private void increaseHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public int getInventorySize() {
        return inventory.getSize();
    }

    @Override
    public int[] getPosition() {
        int[] pos = {0,0};
        return pos;
    }

    @Override
    public int move() {
        char ch = 'N';
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
}
