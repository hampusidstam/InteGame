package se.inte.group5;

public abstract class Equipment extends Item {

    protected int strength;

    public Equipment(char symbol, Color color, int strength) {
        super(symbol, color);

        if (strength < 1 || strength > 100) {
            throw new IllegalArgumentException("Strength invalid");
        }

        this.strength = strength;
    }
}
