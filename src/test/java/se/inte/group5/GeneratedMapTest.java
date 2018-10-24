package se.inte.group5;

import org.junit.Test;

import static org.junit.Assert.*;


public class GeneratedMapTest {
    private StationaryFill filler = new StationaryFill();
    private Hero hero = new Hero(10, 10);

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

    class StationaryFill extends Stationary{
        public StationaryFill() {
            super('~', Color.BLUE);
        }
    }
}