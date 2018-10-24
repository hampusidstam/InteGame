package se.inte.group5;

public class Hero extends Creature {

    private final int MAXIMUM_HP = 100; //TODO: Should not have max hp?
    private Weapon equippedWeapon;

    public Hero(int healthPoints, int speed) {

        super(healthPoints, speed, '@', Color.YELLOW, 10);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public int getInventorySize() {
        return inventory.getSize();
    }

    private void increaseHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
        assert healthPoints <= MAXIMUM_HP && healthPoints > 0;
    }

    public int move(char ch) {
        switch (ch) {
            case 'N':
                return 1;
            case 'E':
                return 2;
            case 'S':
                return 3;
            case 'W':
                return 4;
            default:
                return 0;
        }
    }

    public void pickUpItem(Object item) {

        if (item instanceof Consumable) {
            pickUpConsumable((Consumable) item);
        }
        else if (item instanceof Equipment) {
            pickUpEquipment((Equipment) item);
        }
    }

    private void pickUpConsumable(Consumable item) {
        if (healthPoints >= 90 || item instanceof Potion) {
            healthPoints = MAXIMUM_HP;
        }
        else {
            increaseHealthPoints(item.getEnergy());
        }
    }

    private void pickUpEquipment(Equipment item) {
        if (equippedWeapon == null && item instanceof Weapon) {
            equippedWeapon = (Weapon) item;
        }

        inventory.addItem(item);
        setEquippedWeapon();
    }

    private void setEquippedWeapon() {
        for (Equipment w : inventory.getInventoryArray()) {
            if (w instanceof Weapon && w.strength > equippedWeapon.strength) {
                equippedWeapon = (Weapon) w;
            }
        }
    }

}
