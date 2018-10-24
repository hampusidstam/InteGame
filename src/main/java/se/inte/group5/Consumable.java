package se.inte.group5;

public abstract class Consumable extends Item {

    private int energy;

    public Consumable(char symbol, Color color, int energy) {
        super(symbol, color);

        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
}
