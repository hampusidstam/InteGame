package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {
    private Monster monster;
    private Monster monsterFullHp;

    @Before
    public void createMonster() {
        monster = new Monster(13);
        monsterFullHp = new Monster(100);
    }

    @Test
    public void getHealthPoints_healthPointsIs13_true() {
        assertEquals(13, monster.getHealthPoints());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Monster_negativeHp_invalid() {
        new Monster(-1);
    }

    @Test
    public void takeDamage_decreaseHp_lose15hp() {
        monsterFullHp.takeDamage(15);
        assertEquals(85, monsterFullHp.getHealthPoints());
    }

    @Test
    public void takeDamage_decreaseByNegative_healthPointsNotChanged() {
        monsterFullHp.takeDamage(-1);
        assertEquals(100, monsterFullHp.getHealthPoints());
    }

    @Test
    public void isAlive_killMonster_isNotAlive() {
        monster.takeDamage(monster.getHealthPoints());
        assertFalse(monster.isAlive());
    }

    @Test
    public void move_moveInDirectionOptionNotAbove4_positionChanged() {
        assertFalse(monster.move() > 4);
    }

    @Test
    public void move_moveInDirectionOptionNotNegative_positionChanged() {
        assertFalse(monster.move() < 0);
    }

    @Test
    public void setEquipment_addEquipment_strengthNotLowerThan1() {
        assertFalse(monster.getEquipment().strength < 1);
    }

    @Test
    public void setEquipment_addEquipment_strengthNotHigherThan100() {
        assertFalse(monster.getEquipment().strength > 100);
    }

    @Test
    public void setEquipment_addWeaponWhenCreated_equippedWithWeapon() {
        MonsterRandomWeaponInject monster = new MonsterRandomWeaponInject();
        assertEquals(new Weapon(10), monster.getEquipment());
    }

    @Test
    public void setEquipment_addArmorWhenCreated_equippedWithArmor() {
        MonsterRandomArmorInject monster = new MonsterRandomArmorInject();
        assertEquals(new Armor(5), monster.getEquipment());
    }

    class MonsterRandomWeaponInject extends Monster {
        public MonsterRandomWeaponInject() {
            super(100);

            setEquipment();
        }

        @Override
        protected void setEquipment() {
            inventory.addItem(new Weapon(10));
        }
    }

    class MonsterRandomArmorInject extends Monster {
        public MonsterRandomArmorInject() {
            super(100);
            setEquipment();
        }

        @Override
        protected void setEquipment() {
            inventory.addItem(new Armor(5));
        }
    }
}
