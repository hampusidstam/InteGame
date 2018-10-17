package se.inte.group5;

public class Hero extends Creature {

    public Hero(int healthPoints, int speed){

        super(healthPoints, speed, '@', Color.YELLOW);
    }

    public void increaseHealthPoints(int hp) {
        this.healthPoints += hp;
    }

}
