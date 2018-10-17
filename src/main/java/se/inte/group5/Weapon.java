package se.inte.group5;

public class Weapon extends Equipment {

    private int strength;

    public Weapon(int strength) {
        this.strength = strength;
    }

    public int dealDamage() {
        return strength;
    }
}
