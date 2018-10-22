package se.inte.group5;

public class Armor extends Equipment {

    private int resistance;

    public Armor(int resistance) {
        super('A', Color.GRAY);
        this.resistance = resistance;
    }

    public int getResistance() {
        return resistance;
    }
}
