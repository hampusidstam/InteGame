package se.inte.group5;

public class Monster extends Creature {

    private Equipment equipment;

    public Monster(int healthPoints, int maxSpeed) {

        super(healthPoints, maxSpeed, 'M', Color.GREEN);
    }

   // TODO slumpgenerator för om equipment är weapon eller armor

    public Equipment getEquipment(){
        return equipment;
    }

    public int move(){
        int random = (int )(Math.random() * 3);
        return random;
    }

}
