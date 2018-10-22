package se.inte.group5;

public class Weapon extends Equipment {

    private int strength;

    public Weapon(int strength) {
        super('W', Color.BLUE);

        if (strength < 1 || strength > 100) {
            throw new IllegalArgumentException("Strength invalid");
        }

        this.strength = strength;
    }

    public int dealDamage() {
        return strength;
    }
}
