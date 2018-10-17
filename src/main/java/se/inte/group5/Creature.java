package se.inte.group5;

public abstract class Creature extends GameObject{
    protected int healthPoints, maxSpeed;
    protected boolean alive;

    public Creature(int healthPoints, int maxSpeed, char symbol, Color color) {
        super(symbol, color);
        if (healthPoints < 1 || maxSpeed < 0) {
            throw new IllegalArgumentException();
        }
        this.healthPoints = healthPoints;
        this.maxSpeed = maxSpeed;
        this.alive = true;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            healthPoints -= damage;
        }
        if(healthPoints<1){
            alive = false;
        }
    }

    public boolean isAlive(){
        return alive;
    }
}
