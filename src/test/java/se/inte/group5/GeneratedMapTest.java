package se.inte.group5;

import org.junit.Test;

import static org.junit.Assert.*;


public class GeneratedMapTest {
    private StationaryFill filler = new StationaryFill();
    private Hero hero = new Hero(10);

    @Test
    public void generateMap_mapIsSolvable_True(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        GameObject[][] gameMap = m.getGeneratedMap();
        int firstPosition[] = {1,1};
        floodMap(gameMap, firstPosition);
        boolean filled = mapIsFilledWithStationary(gameMap);
        m.renderGeneratedToConsole();
        assertTrue(filled);
    }

    @Test
    public void getHeight_heightIs10_True(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        assertEquals(10, m.getHeight());
    }

    @Test
    public void getHeight_heightIs10_False(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        assertNotEquals(15, m.getHeight());
    }

    @Test
    public void getWidth_widthIs10_True(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        assertEquals(10, m.getWidth());
    }

    @Test
    public void getWidth_widthIs10_False(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        assertNotEquals(15, m.getWidth());
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

    @Test
    public void putHeroOnMap_HeroIsAt1x1InTheMatrix_true(){
        GeneratedMap map = new GeneratedMap(10, 10, hero);
        assertTrue(map.getGeneratedMap()[1][1] instanceof Hero);
    }

    @Test
    public void putHeroOnMap_HeroInArrayList_true(){
        GeneratedMap map = new GeneratedMap(10, 10, hero);
        Hero hero = (Hero)map.getGeneratedMap()[1][1];
        assertTrue(map.getCreatures().contains(hero));
    }

    @Test
    public void putMonsterOnMap_Map10x10_2monstersExistsOnMapMatrix(){
        int width = 10;
        int height = 10;
        GeneratedMap map = new GeneratedMap(width, height, hero);
        int monsterCount = 0;
        int expectedMonsterCount = (width*height)/50;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(map.getGeneratedMap()[i][j] instanceof Monster){
                    monsterCount++;
                }
            }
        }
        assertEquals(expectedMonsterCount, monsterCount);
    }


    @Test
    public void renderGeneratedToConsole_10x10_isRendered(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        m.renderGeneratedToConsole();
    }

    class StationaryFill extends Stationary{
        public StationaryFill() {
            super('~', Color.BLUE);
        }
    }
}