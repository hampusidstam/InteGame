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
        assertTrue(filled);
    }

    @Test
    public void getWidth_widthIs40_True(){
        GeneratedMap m = new GeneratedMap(40, 40, hero);
        assertEquals(40, m.getHeight());
    }
    @Test
    public void getWidth_widthIs40_False(){
        GeneratedMap m = new GeneratedMap(40, 40, hero);
        assertNotEquals(15, m.getHeight());
    }

    @Test
    public void getHeight_heightIs30_True(){
        GeneratedMap m = new GeneratedMap(30, 30, hero);
        assertEquals(30, m.getHeight());
    }

    @Test
    public void getHeight_heightIs40_False(){
        GeneratedMap m = new GeneratedMap(40, 40, hero);
        assertNotEquals(15, m.getHeight());
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

    private class StationaryFill extends Stationary{
        private StationaryFill() {
            super('~', Color.BLUE);
        }
    }

    @Test
    public void floodMap_mapIsSolvable_True(){
        GameObject[][] generatedMap = new GameObject[40][50];
        generateSolvableMap(generatedMap);
        int firstPosition[] = {1,1};
        floodMap(generatedMap, firstPosition);
        assertTrue(mapIsFilledWithStationary(generatedMap));
    }

    @Test
    public void floodMap_mapIsNotSolvable_False(){
        GameObject[][] generatedMap = new GameObject[40][50];
        generateUnsolvableMap(generatedMap);
        int firstPosition[] = {1,1};
        floodMap(generatedMap, firstPosition);
        assertFalse(mapIsFilledWithStationary(generatedMap));
    }

    @Test
    public void mapIsFilledWithStationary_mapIsFilledWithWalls_True(){
        GameObject[][] generatedMap = new GameObject[10][10];
        generateFilledMap(generatedMap);
        assertTrue(mapIsFilledWithStationary(generatedMap));
    }

    @Test
    public void mapIsFilledWithStationary_mapNotFilledWithWalls_False(){
        GameObject[][] generatedMap = new GameObject[40][50];
        generateSolvableMap(generatedMap);
        assertFalse(mapIsFilledWithStationary(generatedMap));
    }

    private void generateFilledMap(GameObject[][] generatedMap){
        for (int i=0; i<generatedMap.length; i++){
            for (int j=0; j<generatedMap[0].length; j++){
                generatedMap[i][j] = new Wall();
            }
        }
    }

    private void generateSolvableMap(GameObject[][] generatedMap){
        for (int i=0; i<generatedMap.length; i++){
            for (int j=0; j<generatedMap[0].length; j++){
                if (i == 0 || i == generatedMap.length-1 || j == 0 || j == generatedMap[0].length-1){
                    generatedMap[i][j] = new Wall();
                }
            }
        }
    }

    private void generateUnsolvableMap(GameObject[][] generatedMap){
        generateSolvableMap(generatedMap);
        for (int i=0; i<generatedMap.length-1; i++){
            generatedMap[i][generatedMap[0].length/2] = new Wall();
        }
    }

    @Test
    public void generateWallIndex_WallBuiltIntoDoor_True(){
        GeneratedMap gm = new MapWithDoorBorder();
        boolean noWalls = true;
        for (int i = 1; i<7; i++){
            if (gm.getGeneratedMap()[6][i] instanceof Wall){
                noWalls = false;
            }
            if (gm.getGeneratedMap()[i][1] instanceof Wall){
                noWalls = false;
            }
            if (gm.getGeneratedMap()[i][6] instanceof Wall){
                noWalls = false;
            }

        }
        assertTrue(noWalls);
    }

    private class MapWithDoorBorder extends GeneratedMap{
        private MapWithDoorBorder(){
            super(8, 8, new Hero(10));
        }

        @Override
        protected void generateSurroundingWalls() {
            for (int i=0; i<getHeight(); i++){
                for (int j=0; j<getWidth(); j++){
                    if (i == 0 || i == getHeight()-1 || j == 0 || j == getWidth()-1){
                        getActualMap()[i][j] = new Door();
                    }
                }
            }
            getGeneratedMap()[0][1] = new Wall();
            getGeneratedMap()[0][2] = new Wall();
            getGeneratedMap()[0][3] = new Wall();
            getGeneratedMap()[0][4] = new Wall();
            getGeneratedMap()[0][5] = new Wall();
            getGeneratedMap()[0][6] = new Wall();
        }
    }

    class GeneratedMapInject extends GeneratedMap{
        private GameObject gameObject;
        private int heroY, heroX, objY, objX;

        private GeneratedMapInject(int width, int height, Hero hero, int heroY, int heroX, GameObject gameObject, int objY, int objX) {
            super(width, height, hero);
            this.gameObject = gameObject;
            this.heroY = heroY;
            this.heroX = heroX;
            this.objY = objY;
            this.objX = objX;
        }

        private GeneratedMapInject(int width, int height, Hero hero, int heroY, int heroX) {
            super(width, height, hero);
            this.heroY = heroY;
            this.heroX = heroX;

            generateMap();
            putGameObjectsOnMap();
            renderGeneratedToConsole();
        }

        @Override
        protected void generateAll(){}

        private void putGameObjectsOnMap(){
            getActualMap()[heroY][heroX] = hero;
            hero.setPosition(heroY, heroX);
            getCreatures().add(hero);

            if (gameObject != null){
                getActualMap()[objY][objX] = gameObject;
                if (gameObject instanceof Monster){
                    Monster monster = (Monster) gameObject;
                    monster.setPosition(objY, objX);
                    getCreatures().add(monster);
                }
            }
        }

        private void generateMap() {
            for (int i = 0; i < getHeight(); i++) {
                for (int j = 0; j < getWidth(); j++) {
                    if (i == 0 || i == getHeight() - 1 || j == 0 || j == getWidth() - 1) {
                        getActualMap()[i][j] = new Wall();
                    }
                }
            }
        }
    }

    @Test
    public void moveCreatures_heroHasNotMoved_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10, hero, 1, 1);
        hero.setDirectionInput('0');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[1][1]);

    }

    @Test
    public void moveCreatures_heroAttemptsToMoveThroughWall_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10, hero, 1, 1);
        hero.setDirectionInput('N');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[1][1]);
    }

    @Test
    public void moveCreatures__heroMovesNorth_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10, hero, 3, 1);
        hero.setDirectionInput('N');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[2][1]);
    }

    @Test
    public void moveCreatures__heroMovesEast_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10, hero, 3, 1);
        hero.setDirectionInput('E');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[3][2]);
    }

    @Test
    public void moveCreatures__heroMovesSouth_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10, hero, 3, 1);
        hero.setDirectionInput('S');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[4][1]);
    }

    @Test
    public void moveCreatures__heroMovesWest_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10, hero, 3, 2);
        hero.setDirectionInput('W');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[3][1]);
    }

    @Test
    public void moveCreatures_heroMovesOnPlant_true(){

    }

    @Test
    public void moveCreatures_heroMovesOnPlantAtFullHP_true(){

    }

    @Test
    public void moveCreatures_heroMovesOnPotion_true(){

    }

    @Test
    public void moveCreatures_heroMovesOnPotionAtFullHP_true(){

    }

    @Test
    public void moveCreatures_weaponIsNotEquipped_false(){

    }

    @Test
    public void moveCreatures_weaponIsEquipped_true(){

    }

    @Test
    public void moveCreatures_armorIsNotEquipped_true(){

    }



    class MonsterRandomInject extends Monster {
        int direction;
        public MonsterRandomInject(int health, int direction) {
            super(health);
            this.direction = direction;
        }

        @Override
        public int[] move() {
            return moveCreature(direction);
        }
    }

    @Test
    public void moveCreatures_monsterAttemptsToMoveThroughWall_true(){

    }

    @Test
    public void moveCreatures_monsterMovesNorth_true(){

    }

    @Test
    public void moveCreatures_monsterMovesEast_true(){

    }

    @Test
    public void moveCreatures_monsterMovesSouth_true(){

    }

    @Test
    public void moveCreatures_monsterMovesWest_true(){

    }

    @Test
    public void moveCreatures_monsterMovesOnPlant_true(){

    }

    @Test
    public void moveCreatures_monsterMovesOnPotion_true(){

    }

    @Test
    public void moveCreatures_monsterMovesOnWeapon_true(){

    }

    @Test
    public void moveCreatures_monsterMovesOnArmor_true(){

    }

}