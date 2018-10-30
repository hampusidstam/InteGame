package se.inte.group5;

import java.util.Random;

public class Monster extends Creature {

    public Monster(int healthPoints) {
        super(healthPoints, 'M', Color.RED, 1);
        setEquipment();
    }

    protected void setEquipment() {
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

    public int[] move(){
        return moveCreature(new Random().nextInt(5));
    }
}