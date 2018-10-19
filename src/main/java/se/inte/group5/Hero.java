package se.inte.group5;

public class Hero extends Creature {

    private final int MAXIMUM_HP = 100; //TODO: Should not have max hp?

    public Hero(int healthPoints, int speed) {

        super(healthPoints, speed, '@', Color.YELLOW);
    }

    public void pickUpItem(Object item) {
        if (healthPoints > 90 || item instanceof Potion) {
            healthPoints = MAXIMUM_HP;
        }
        else if (item instanceof Plant) {
            increaseHealthPoints(((Plant) item).getHealthPoints());
        }
    }

    private void increaseHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }
}
