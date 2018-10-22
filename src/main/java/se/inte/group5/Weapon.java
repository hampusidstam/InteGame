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

    @Override
    public boolean equals(Object other) {
        if (other instanceof Weapon) {
            Weapon weapon = (Weapon) other;
            return strength == weapon.strength;
        }
        return false;
    }

}
