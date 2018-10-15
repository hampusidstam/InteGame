package se.inte.group5;

public class Creature {
    private int currentLife, maxSpeed;

    public Creature(int currentLife, int maxSpeed) {
     this.currentLife = currentLife;
     this.maxSpeed = maxSpeed;
    }

    public int getCurrentLife(){
        return currentLife;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
