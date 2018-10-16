package se.inte.group5;

public class Creature {
    private int healthPoints, maxSpeed;

    public Creature(int healthPoints, int maxSpeed) {
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
}
