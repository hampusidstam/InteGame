package se.inte.group5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class GeneratedMapTest {
    private StationaryFill filler = new StationaryFill();
    private Hero hero = new Hero(10, 10);

    @Test
    public void renderGeneratedToConsole_empty10x10(){
        GeneratedMap map = new GeneratedMap(10, 10, hero);
        map.renderGeneratedToConsole();
    }

    @Test
    public void generateMap_mapIsSolvable_True(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        m.generateMap();
        GameObject[][] gameMap = m.getGeneratedMap();
        int firstPosition[] = {1,1};
        floodMap(gameMap, firstPosition);
        boolean filled = mapIsFilledWithStationary(gameMap);
        m.renderGeneratedToConsole();
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

    @Test
    public void renderGeneratedToConsole_10x10_isRendered(){
        GeneratedMap m = new GeneratedMap(10, 10, hero);
        m.generateMap();
        m.renderGeneratedToConsole();
    }

    class StationaryFill extends Stationary{
        public StationaryFill() {
            super('~', Color.BLUE);
        }
    }
}