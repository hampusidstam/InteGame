package se.inte.group5;

public abstract class Consumable extends Item {

    private int energy;

    public Consumable(char symbol, Color color, int energy) {
        super(symbol, color);

        if (energy < 1) {
            throw new IllegalArgumentException("HP must be above 0");
        }

        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
}
