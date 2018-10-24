package se.inte.group5;

import java.util.Random;

public class Monster extends Creature {

    public Monster(int healthPoints, int maxSpeed) {

        super(healthPoints, maxSpeed, 'M', Color.RED, 1);

        setEquipment();
    }

    private void setEquipment() {
        int equipmentStrength = (int) ((100 * Math.random()) + 1);
        if (equipmentStrength % 2 != 0) {
            inventory.addItem(new Weapon(equipmentStrength));
        }
        else {
            inventory.addItem(new Armor(equipmentStrength));
        }
    }

    public Equipment getEquipment() {
        return inventory.getItem(0);
    }

    public int move() {

        return new Random().nextInt(5);
        // alt. getRandomDir();
    }
}
