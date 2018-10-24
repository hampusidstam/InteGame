package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MapTest {
    private Map map;
    private StationaryFill filler = new StationaryFill();

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
        map.placeGameObject(8, 5, new Wall());
        assertTrue(map.getMap()[8][5] instanceof Wall);
    }

    /*
    @Test
    public void moveCreatures_OneMonsterOnEmptyMap_CreatureHasMoved() {
        Map temp = new Map(10, 10);

        Monster monster = new Monster(10, 1);
        temp.placeGameObject(1,1, monster);

        System.out.println("Before moved");
        temp.renderToConsole();

        temp.moveCreatures('X');
        assertNull(temp.getMap()[1][1]);

        System.out.println("After moved");
        temp.renderToConsole();
    }*/

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedNorth() {
        Hero hero = new Hero(10, 5);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('N');
        assertTrue(map.getMap()[4][5] instanceof Hero);
    }

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedEast() {
        Hero hero = new Hero(10, 5);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('E');
        assertTrue(map.getMap()[5][6] instanceof Hero);
    }

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedSouth() {
        Hero hero = new Hero(10, 5);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('S');
        assertTrue(map.getMap()[6][5] instanceof Hero);
    }

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedWest() {
        Hero hero = new Hero(10, 5);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('W');
        assertTrue(map.getMap()[5][4] instanceof Hero);
    }

    @Test
    public void moveCreatures_HeroNextToWall_HeroHasNotMOved() {
        Map temp = new Map(10, 10);

        Hero hero = new Hero(20, 1);
        temp.placeGameObject(1,1, hero);
        Wall wall = new Wall();
        temp.placeGameObject(1,0, wall);

        System.out.println("Before moved");
        temp.renderToConsole();

        temp.moveCreatures('W');

        System.out.println("After moved");
        temp.renderToConsole();

        assertNotNull(temp.getMap()[1][1]);
    }

    @Test
    public void moveCreatures_MapWithHeroAndConsumable_HeroHas15HP() {
        Map temp = new Map(10, 10);

        Hero hero = new Hero(20, 1);
        hero.takeDamage(15);
        temp.placeGameObject(1,1, hero);
        Plant plant = new Plant();
        temp.placeGameObject(2,1, plant);

        System.out.println("Before moved");
        temp.renderToConsole();

        temp.moveCreatures('S');

        System.out.println("After moved");
        temp.renderToConsole();

        assertEquals(15, hero.getHealthPoints());
    }

    @Test
    public void moveCreatures_MapWithHeroAndEquipment_HeroHasEquipmentInInventory() {
        Map temp = new Map(10, 10);

        Hero hero = new Hero(20, 1);
        temp.placeGameObject(1,1, hero);
        Armor armor = new Armor(20);
        temp.placeGameObject(1,0, armor);

        System.out.println("Before moved");
        temp.renderToConsole();

        temp.moveCreatures('W');

        System.out.println("After moved");
        temp.renderToConsole();

        assertEquals(hero.getInventory().getInventoryArray()[0], armor);
    }

    @Test
    public void renderToConsole_empty10x10(){
        Map map = new Map(10, 10);
        map.renderToConsole();
    }

    @Test
    public void renderToConsole_10x10_OneOfEach(){
        Map map = new Map(10, 10);
        map.placeGameObject(0, 0, new Hero(100, 10));
        map.placeGameObject(1, 0, new Water());
        map.placeGameObject(2, 0, new Wall());
        map.placeGameObject(2, 2, new Monster(100, 10));
        map.placeGameObject(7, 6, new Weapon(12));
        map.placeGameObject(8, 7, new Potion());
        map.renderToConsole();
    }
    @Test
    public void removeItemFromMap_ok(){
        Map map = new Map(20,20);
        Wall wall = new Wall();
        assertNull(map.getMap()[10][10]);
        map.placeGameObject(10, 10, wall);
        assertEquals(wall, map.getMap()[10][10]);
        assertEquals(wall, map.removeItem(10,10));
    }

    @Test
    public void removeItemThatDontExist_ok(){
        assertNull(map.removeItem(10,10));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeItemFromMap_indexOutOfBound(){
        map.removeItem(400,500);
    }

    @Test
    public void renderToConsole_40x40_moreItems(){
        Map map = new Map(40, 40);
        map.placeGameObject(0, 0, new Hero(100, 10));
        map.placeGameObject( 1, 0, new Wall());
        map.placeGameObject(1, 1,new Wall());
        map.placeGameObject(10, 10, new Monster(100, 10));
        map.placeGameObject(30, 20, new Monster(100, 10));
        map.placeGameObject(15, 15, new Water());
        map.placeGameObject(15, 16, new Water());
        map.placeGameObject(15, 17, new Water());
        map.placeGameObject(17, 4,new Weapon(12));
        map.placeGameObject(9, 4, new Weapon(12));
        map.placeGameObject(12, 4, new Potion());
        map.placeGameObject(19, 8, new Potion());
        map.renderToConsole();
    }

    @Test
    public void generateMap_mapIsSolvable_True(){
        Map m = new Map(10, 10);
        m.generateMap();
        GameObject[][] gameMap = m.getGeneratedMap();
        int firstPosition[] = {1,1};
        floodMap(gameMap, firstPosition);
        boolean filled = mapIsFilledWithStationary(gameMap);
        assertTrue(filled);
    }

    private boolean mapIsFilledWithStationary(GameObject[][] gameMap){
        for (GameObject[] row: gameMap){
            for (GameObject occupant : row){
                if (!(occupant instanceof Stationary)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void floodMap(GameObject[][] gameMap, int[] position){
        if (!(gameMap[position[0]][position[1]] instanceof Stationary)){
            gameMap[position[0]][position[1]] = filler;

            int[] firstPosition = {position[0]+1, position[1]};
            int[] secondPosition = {position[0]-1, position[1]};
            int[] thirdPosition = {position[0], position[1]+1};
            int[] fourthPosition = {position[0], position[1]-1};
            floodMap(gameMap, firstPosition);
            floodMap(gameMap, secondPosition);
            floodMap(gameMap, thirdPosition);
            floodMap(gameMap, fourthPosition);
        }
        return;
    }

    class StationaryFill extends Stationary{
        public StationaryFill() {
            super('~', Color.BLUE);
        }
    }
}
