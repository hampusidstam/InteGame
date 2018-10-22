package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    public void fillMap_wallAt00_true(){
        assertTrue(map.getMap()[0][0] instanceof Wall);
    }

    @Test
    public void moveCreatures_OneCreatureOnEmptyMap_CreatureHasMoved() {
        Monster monster = new Monster(10, 1);
        map.placeGameObject(1,1, monster);
        map.moveCreatures();
        assertNull(map.getMap()[1][1]);
    }


}
