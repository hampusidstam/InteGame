package se.inte.group5;

public abstract class Creature extends GameObject {

    protected int healthPoints, maxSpeed;
    protected boolean alive;
    protected int[] position = new int[2];

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

        if (healthPoints < 1) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract int[] getPosition();

    public void setPosition(int y, int x) {
        position[0] = y;
        position[1] = x;
    }

    public abstract int move();

    public int[] moveCreature() {

        int dir = move();
        int[] originalPos = getPosition();
        int[] newPos;

        switch (dir) {
            // One step north
            case 1:
                newPos = new int[] {originalPos[1], originalPos[0], originalPos[1]+1, originalPos[0]};
                break;
            // One step east
            case 2:
                newPos = new int[] {originalPos[1], originalPos[0], originalPos[1], originalPos[0]+1};
                break;
            // One step south
            case 3:
                newPos = new int[] {originalPos[1], originalPos[0], originalPos[1]-1, originalPos[0]};
                break;
            // One step west
            case 4:
                newPos = new int[] {originalPos[1], originalPos[0], originalPos[1], originalPos[0]-1};
                break;
            default:
                newPos = new int[] {originalPos[1], originalPos[0], originalPos[1], originalPos[0]};
        }
        return newPos;
    }
    
}
