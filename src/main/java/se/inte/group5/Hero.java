package se.inte.group5;

public class Hero extends Creature {

    private final int MAXIMUM_HP = 100;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public Hero(int healthPoints) {

        super(healthPoints, '@', Color.YELLOW, 10);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public int getInventorySize() {
        return inventory.getSize();
    }

    private void heal(int healthPoints) {
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

    public void pickUpItem(Item item) {

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
            heal(item.getEnergy());
        }
    }

    private void pickUpEquipment(Equipment item) {
        if (item instanceof Armor) {
            if (equippedArmor == null) {
                equippedArmor = (Armor) item;
            }
        }
        else if (item instanceof Weapon) {
            if (equippedWeapon == null) {
                equippedWeapon = (Weapon) item;
            }
        }

        inventory.addItem(item);
        setEquipment();
    }

    private void setEquipment() {
        for (Equipment item : inventory.getInventoryArray()) {
            if (item instanceof Weapon && item.strength > equippedWeapon.strength) {
                equippedWeapon = (Weapon) item;
            }
            else if (item instanceof Armor && item.strength > equippedArmor.strength) {
                equippedArmor = (Armor) item;
            }
        }
    }

    public int getStrength(){
        return -1;
    }
}
