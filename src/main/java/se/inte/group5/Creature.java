package se.inte.group5;

public abstract class Creature extends GameObject {

    protected int healthPoints;
    protected Inventory inventory;
    protected boolean alive;
    protected int[] position = new int[2];

    public Creature(int healthPoints, char symbol, Color color, int inventorySize) {
        super(symbol, color);

        if (healthPoints < 1) {
            throw new IllegalArgumentException();
        }

        this.healthPoints = healthPoints;
        this.alive = true;
        inventory = new Inventory(inventorySize);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setPosition(int y, int x) {
        position[0] = y;
        position[1] = x;
    }

    public int[] moveCreature(char ch) {

        int dir = 0;
        int[] newPos;

        if (this instanceof Hero)
            dir = ((Hero)this).move(ch);
        else if (this instanceof Monster)
            dir = ((Monster)this).move();

        switch (dir) {
            // One step north
            case 1:
                newPos = new int[] {position[1], position[0], position[1]-1, position[0]};
                break;
            // One step east
            case 2:
                newPos = new int[] {position[1], position[0], position[1], position[0]+1};
                break;
            // One step south
            case 3:
                newPos = new int[] {position[1], position[0], position[1]+1, position[0]};
                break;
            // One step west
            case 4:
                newPos = new int[] {position[1], position[0], position[1], position[0]-1};
                break;
            default:
                newPos = new int[] {position[1], position[0], position[1], position[0]};
        }
        return newPos;
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            healthPoints -= damage;
        }

        if (healthPoints < 1) {
            dies();
        }
    }

    public boolean isAlive() {
        return alive;
    }

    private void dies() {
        alive = false;
    }
    
}
