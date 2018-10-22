package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MapTest {
    private Map map;

    @Before
    public void createMaps(){
        map = new Map(200, 100);
    }

    @Test
    public void getWidth_widthIs200_True() {
        assertEquals(200, map.getWidth());
    }

    @Test
    public void getHeight_heightIs200_False() {
        assertEquals(100, map.getHeight());
    }

    @Test
    public void createMap_width200_True(){
        assertEquals(200, map.getMap()[0].length);
    }

    @Test
    public void createMap_height100_True(){
        assertEquals(100, map.getMap().length);
    }

    @Test
    public void addItem_wall_8_5(){
        map.addObjectToMap(new Wall(), 8, 5);
        assertTrue(map.getMap()[8][5] instanceof Wall);
    }

    @Test
    public void renderToConsole_empty10x10(){
        Map map = new Map(10, 10);
        map.renderToConsole();
    }

    @Test
    public void renderToConsole_10x10_Hero00_Vatten01_Vägg02_Monster22_Vapen67_Potion78(){
        Map map = new Map(10, 10);
        map.addObjectToMap(new Hero(100, 10), 0, 0);
        map.addObjectToMap(new Water(), 0, 1);
        map.addObjectToMap(new Wall(), 0, 2);
        map.addObjectToMap(new Monster(100, 10), 2, 2);
        map.addObjectToMap(new Weapon(12), 6, 7);
        map.addObjectToMap(new Potion(), 7, 8);
        map.renderToConsole();
    }

    @Test
    public void renderToConsole_40x40_Hero00_vägg01_vägg11_monster1010_monster3020_vatten1515_vatten1516_vatten1517_vapen174_vapen_94_potion124_potion198(){
        Map map = new Map(40, 40);
        map.addObjectToMap(new Hero(100, 10), 0, 0);
        map.addObjectToMap(new Wall(), 0, 1);
        map.addObjectToMap(new Wall(), 1, 1);
        map.addObjectToMap(new Monster(100, 10), 10, 10);
        map.addObjectToMap(new Monster(100, 10), 30, 20);
        map.addObjectToMap(new Water(), 15, 15);
        map.addObjectToMap(new Water(), 15, 16);
        map.addObjectToMap(new Water(), 15, 17);
        map.addObjectToMap(new Weapon(12), 17, 4);
        map.addObjectToMap(new Weapon(12), 9, 4);
        map.addObjectToMap(new Potion(), 12, 4);
        map.addObjectToMap(new Potion(), 19, 8);
        map.renderToConsole();
    }
}
