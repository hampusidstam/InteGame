package se.inte.group5;

public class Armor extends Equipment {

    public Armor(int strength) {
        super('A', Color.GRAY, strength);
    }

    public int getResistance() {
        return strength;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Armor) {
            Armor armor = (Armor) other;
            return strength == armor.strength;
        }
        return false;
    }
}
