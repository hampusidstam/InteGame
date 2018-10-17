package se.inte.group5;

public class Hero extends Creature {

    private int healthPoints, maxSpeed;
    private boolean alive;

    public Hero(int healthPoints, int speed){

        super(healthPoints, speed, '@', Color.YELLOW);
    }

    public void increaseHealthPoints(int hp) {
        healthPoints += hp;
    }

}
