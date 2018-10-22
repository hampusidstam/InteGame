package se.inte.group5;

public abstract class Consumable extends Item {

    private int healthPoints;

    public Consumable(char symbol, Color color, int healthPoints) {
        super(symbol, color);
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
