package se.inte.group5;

public class Monster extends Creature {

    private Equipment equipment;

    public Monster(int healthPoints, int maxSpeed, char symbol, Color color) {

        super(healthPoints, maxSpeed, symbol, color);

    }

   // TODO slumpgenerator för om equipment är weapon eller armor

    public Equipment getEquipment(){
        return equipment;
    }

}
