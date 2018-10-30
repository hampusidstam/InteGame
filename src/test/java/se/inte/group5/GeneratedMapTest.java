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

    class MapWithDoorBorder extends GeneratedMap{

        public MapWithDoorBorder(){
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

        public GeneratedMapInject(int width, int height, Hero hero, int heroY, int heroX, GameObject gameObject, int objY, int objX) {
            super(width, height, hero);
            this.gameObject = gameObject;
            this.heroY = heroY;
            this.heroX = heroX;
            this.objY = objY;
            this.objX = objX;
        }

        public GeneratedMapInject(int width, int height, Hero hero, int heroY, int heroX) {
            super(width, height, hero);
            this.heroY = heroY;
            this.heroX = heroX;
        }

        @Override
        protected void generateAll(){
            generateMap();

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
        //hero.setDirectionInput('0');
        //map.moveCreatures();
        //assertEquals(hero, map.getActualMap()[1][1]);

    }

    @Test
    public void moveCreatures_heroAttemptsToMoveThroughWall_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10, hero, 1, 1);
        //hero.setDirectionInput('N');
        //map.moveCreatures();
        //assertEquals(hero, map.getActualMap()[1][1]);
    }


    /*
    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasNotMoved() {
        Hero hero = new Hero(10);
        generatedMap.placeGameObject(5,5, hero);
        map.moveCreatures('0');
        assertTrue(map.getMap()[5][5] instanceof Hero);
    }

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedNorth() {
        Hero hero = new Hero(10);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('N');
        assertTrue(map.getMap()[4][5] instanceof Hero);
    }

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedEast() {
        Hero hero = new Hero(10);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('E');
        assertTrue(map.getMap()[5][6] instanceof Hero);
    }

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedSouth() {
        Hero hero = new Hero(10);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('S');
        assertTrue(map.getMap()[6][5] instanceof Hero);
    }

    @Test
    public void moveCreatures_OneHeroOnEmptyMap_HeroHasMovedWest() {
        Hero hero = new Hero(10);
        map.placeGameObject(5,5, hero);
        map.moveCreatures('W');
        assertTrue(map.getMap()[5][4] instanceof Hero);
    }

    @Test
    public void moveCreatures_MonsterMoves_MonsterHasMoved() {
        Map temp = new Map(10, 10);

        MonsterRandomInject monster = new MonsterRandomInject();
        temp.placeGameObject(2,2, monster);

        temp.moveCreatures('X');

        assertNotNull(temp.getMap()[2][2]);
    }

    @Test
    public void moveCreatures_HeroNextToWall_HeroHasNotMoved() {
        Map temp = new Map(10, 10);

        Hero hero = new Hero(20);
        temp.placeGameObject(1,1, hero);
        Wall wall = new Wall();
        temp.placeGameObject(1,0, wall);

        System.out.println("moveCreatures_HeroNextToWall_HeroHasNotMoved");
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

        Hero hero = new Hero(20);
        hero.takeDamage(15);
        temp.placeGameObject(1,1, hero);
        Plant plant = new Plant();
        temp.placeGameObject(2,1, plant);

        System.out.println("moveCreatures_MapWithHeroAndConsumable_HeroHas15HP");
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

        Hero hero = new Hero(20);
        temp.placeGameObject(1,1, hero);
        Armor armor = new Armor(20);
        temp.placeGameObject(1,0, armor);

        System.out.println("moveCreatures_MapWithHeroAndEquipment_HeroHasEquipmentInInventory");
        System.out.println("Before moved");
        temp.renderToConsole();

        temp.moveCreatures('W');

        System.out.println("After moved");
        temp.renderToConsole();

        assertEquals(hero.getInventory().getInventoryArray()[0], armor);
    }

    class MonsterRandomInject extends Monster {
        public MonsterRandomInject() {
            super(20);
        }

        @Override
        public int move() {
            return 0;
        }
    }
    */
}