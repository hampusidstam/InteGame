package se.inte.group5;

public abstract class Creature extends GameObject{
    private int healthPoints, maxSpeed;

    public Creature(int healthPoints, int maxSpeed, char symbol, Color color) {
        super(symbol, color);
        this.healthPoints = healthPoints;
        this.maxSpeed = maxSpeed;
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
    }

    public int pickUpItem(int item){
        return 0;
    }
}
