package se.inte.group5;

public class Plant extends Consumable {

    private int hp;
    private Color color;

    public Plant(int hp, Color color) {
        super();
        this.hp = hp;
        this.color = color.RED;
    }

    public int getHealthPoints() {
        return hp;
    }

    public Color getColor() {
        return color;
    }

}




