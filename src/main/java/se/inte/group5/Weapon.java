package se.inte.group5;

public class Weapon extends Equipment {

    public Weapon(int strength) {
        super('W', Color.BLUE, strength);
    }

    public int getDamage() {
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
