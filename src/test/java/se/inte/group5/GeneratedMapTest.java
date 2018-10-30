package se.inte.group5;

import org.junit.Test;

import static org.junit.Assert.*;


public class GeneratedMapTest {
    private StationaryFill filler = new StationaryFill();
    private Hero hero = new Hero(10);


    // Lagt till variabler:

    private Plant plant = new Plant();
    private Potion potion = new Potion();
    private Weapon weakWeapon = new Weapon(20);
    private Weapon strongWeapon = new Weapon(50);
    private Armor armor = new Armor(50);

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

    private class StationaryFill extends Stationary{
        private StationaryFill() {
            super('~', Color.BLUE);
        }
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
    public void generateMap_mapIsSolvable_True(){
        GeneratedMap m = new GeneratedMap(30, 30, hero);
        GameObject[][] gameMap = m.getGeneratedMap();
        int firstPosition[] = {1,1};
        floodMap(gameMap, firstPosition);
        boolean filled = mapIsFilledWithStationary(gameMap);
        assertTrue(filled);
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
    public void innerLoops_WallBuiltIntoDoor_True(){
        GeneratedMap gm = new MapWithDoorBorderInject();
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

    private class MapWithDoorBorderInject extends GeneratedMap{
        private MapWithDoorBorderInject(){
            super(8, 8, new Hero(10));
        }

        @Override
        protected void generateSurroundingWalls() {
            for (int i=0; i<getHeight(); i++){
                for (int j=0; j<getWidth(); j++){
                    if (i == getHeight()-1 || j == 0 || j == getWidth()-1){
                        getActualMap()[i][j] = new Door();
                    }
                    if (i == 0){
                        getActualMap()[i][j] = new Wall();
                    }
                }
            }
        }
    }

    @Test
    public void generateWallIndex_WallBuiltIntoDoor_True(){
        GeneratedMap gm = new MapWithOnlyDoorBorderInject();
        boolean noWalls = true;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++) {
                if (gm.getGeneratedMap()[j][i] instanceof Wall) {
                    noWalls = false;
                }
            }
        }
        assertTrue(noWalls);
    }

    private class MapWithOnlyDoorBorderInject extends GeneratedMap{
        private MapWithOnlyDoorBorderInject(){
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
        }
    }

    class GeneratedMapInject extends GeneratedMap{

        private GeneratedMapInject(int width, int height) {
            super(width, height, new Hero(10));

            generateMap();
        }

        @Override
        protected void generateAll(){}

        private void putCreatureOnMap(Creature creature, int row, int column){
            getActualMap()[row][column] = creature;
            creature.setPosition(row, column);
            getCreatures().add(creature);
        }

        private void putItemOnMap(Item item, int row, int column){
            getActualMap()[row][column] = item;
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
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 1, 1);
        hero.setDirectionInput('0');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[1][1]);
    }

    @Test
    public void moveCreatures_heroAttemptsToMoveThroughWall_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 1, 1);
        hero.setDirectionInput('N');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[1][1]);
    }

    @Test
    public void moveCreatures__heroMovesNorth_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 1);
        hero.setDirectionInput('N');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[2][1]);
    }

    @Test
    public void moveCreatures__heroMovesEast_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 1);
        hero.setDirectionInput('E');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[3][2]);
    }

    @Test
    public void moveCreatures__heroMovesSouth_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 1);
        hero.setDirectionInput('S');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[4][1]);
    }

    @Test
    public void moveCreatures__heroMovesWest_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 2);
        hero.setDirectionInput('W');
        map.moveCreatures();
        assertEquals(hero, map.getActualMap()[3][1]);
    }


    // Kolla nedan tester


    @Test
    public void moveCreatures_heroMovesOnPlant_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 3);
        map.putItemOnMap(plant, 2, 3);
        hero.setDirectionInput('N');
        map.moveCreatures();
        assertNotEquals(plant, map.getActualMap()[2][3]);
        // Kollar att växten försvunnit, eller ska den kolla att heros hp ökat?
    }

    @Test
    public void moveCreatures_heroMovesOnPlantAtFullHP_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 3);
        map.putItemOnMap(plant, 2, 3);
        hero.setDirectionInput('N');
        map.moveCreatures();
        hero.pickUpItem(plant);
        assertEquals(30, hero.getHealthPoints());
        // Ska kolla att heros hp ökat från 10 till 20? Eller att heros hp redan var 100? sätts i injection till 10.
    }

    @Test
    public void moveCreatures_heroMovesOnPotion_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 3);
        map.putItemOnMap(potion, 3, 4);
        hero.setDirectionInput('E');
        map.moveCreatures();
        hero.pickUpItem(potion);
        assertNotEquals(potion, map.getActualMap()[2][4]);
    }

    @Test
    public void moveCreatures_heroMovesOnPotionAtFullHP_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 3);
        map.putItemOnMap(potion, 2, 3);
        hero.setDirectionInput('N');
        map.moveCreatures();
        hero.pickUpItem(potion);
        assertEquals(100, hero.getHealthPoints());
        // Kollar att heros hp ökat till 100
    }


    @Test
    public void moveCreatures_weaponIsNotEquipped_false(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 3);
        map.putItemOnMap(strongWeapon, 2, 3);
        map.putItemOnMap(weakWeapon, 2, 4);

        hero.setDirectionInput('N');
        map.moveCreatures();
        hero.pickUpItem(strongWeapon);

        hero.setDirectionInput('E');
        map.moveCreatures();
        hero.pickUpItem(weakWeapon);

        assertNotEquals(20, hero.getStrength());
    }

    @Test
    public void moveCreatures_weaponIsEquipped_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 3);
        map.putItemOnMap(strongWeapon, 4, 3);
        hero.setDirectionInput('S');
        map.moveCreatures();
        hero.pickUpItem(strongWeapon);
        assertEquals(50, hero.getStrength());
    }

    @Test
    public void moveCreatures_armorIsNotEquipped_true(){        // ska denna testa att heron hittar en sämre armor som ej sätts till aktiv?
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        map.putCreatureOnMap(hero, 3, 3);
        map.putItemOnMap(armor, 2, 4);
        hero.setDirectionInput('E');
        map.moveCreatures();
        hero.pickUpItem(armor);
        assertEquals(50, hero.getStrength());
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

    /*
    @Test
    public void moveCreatures_monsterAttemptsToMoveThroughWall_true(){
        GeneratedMapInject map = new GeneratedMapInject(10, 10);
        MonsterRandomInject monster = new MonsterRandomInject(30, 1);
        map.putCreatureOnMap(monster, 3, 1);
        map.moveCreatures();
        assertEquals(monster, map.getActualMap()[1][1]);
    }
    */

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