package se.inte.group5;

public class Hero extends Creature {
    private final int MAXIMUM_HP = 100;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int directionInput;

    public Hero(int healthPoints) {
        super(healthPoints, '@', Color.YELLOW, 10);
        this.directionInput = 0;
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

    protected void heal(int healthPoints) {
        if (healthPoints < 1) {
            throw new IllegalArgumentException();
        }
        this.healthPoints += healthPoints;
        if (this.healthPoints > MAXIMUM_HP) {
            this.healthPoints = MAXIMUM_HP;
        }
        assert healthPoints <= MAXIMUM_HP && healthPoints > 0;
    }

    protected void setDirectionInput(char direction){
        switch (direction) {
            case 'N':
                directionInput = 1;
                break;
            case 'E':
                directionInput = 2;
                break;
            case 'S':
                directionInput = 3;
                break;
            case 'W':
                directionInput = 4;
                break;
            default:
                directionInput = 0;
        }
    }

    public int[] move(){
        return moveCreature(directionInput);
    }

    protected int getDirectionInput(){
        return directionInput;
    }

    public void pickUpItem(Item item) {
        if (item instanceof Consumable) {
            useConsumable((Consumable) item);
        }
        else if (item instanceof Equipment) {
            setActiveEquipment((Equipment) item);
            inventory.addItem((Equipment) item);
        }
        else if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void useConsumable(Consumable item) {
        if (healthPoints >= 90 || item instanceof Potion) {
            healthPoints = MAXIMUM_HP;
        }
        else {
            heal(item.getEnergy());
        }
    }

    private void setActiveEquipment(Equipment item) {
        if (item instanceof Armor) {
            if (equippedArmor == null || item.strength > equippedArmor.strength) {
                equippedArmor = (Armor) item;
            }
        }
        else if (item instanceof Weapon) {
            if (equippedWeapon == null || item.strength > equippedWeapon.strength) {
                equippedWeapon = (Weapon) item;
            }
        }
    }

    public int getStrength(){
        int total = 0;
        if(equippedWeapon != null){
            total += equippedWeapon.getDamage();
        }
        if(equippedArmor != null){
            total += equippedArmor.getResistance();
        }
        return total;
    }
}
