package se.inte.group5;

import java.util.ArrayList;
import java.util.Random;

public class GeneratedMap {
    private int width, height;
    private GameObject[][] generatedMap;
    private Wall wall = new Wall();
    private ArrayList<Creature> creatures = new ArrayList<>();
    private Hero hero;
    private ArrayList<int[]> doors = new ArrayList<>();

    public GeneratedMap(int width, int height, Hero hero) {
        this.width = width;
        this.height = height;
        this.hero = hero;
        if (width < 10) this.width = 10;
        if (height < 10) this.height = 10;
        generatedMap = new GameObject[height][width];
        generateAll();
    }

    protected void generateAll(){
        generateMap();
        putHeroOnMap(hero);
        putMonstersOnMap();
        putConsumablesOnMap();
        putEquipmentOnMap();
        renderGeneratedToConsole();
    }

    private void putHeroOnMap(Hero hero){
        generatedMap[1][1] = hero;
        hero.setPosition(1, 1);
        creatures.add(hero);
    }

    protected ArrayList<Creature> getCreatures(){
        return creatures;
    }

    private void putMonstersOnMap(){
        int numberOfMonsters = height*width/50;
        for (int i = 0; i < numberOfMonsters; i++){
            Monster monster = new Monster(10);
            int[] position = putGameObjectsOnMap(monster);
            monster.setPosition(position[0], position[1]);
            creatures.add(monster);
        }
    }

    private void putConsumablesOnMap(){
        putPlantsOnMap();
        putPotionsOnMap();
    }

    private void putPlantsOnMap(){
        int numberOfPlants = height*width/30;
        for (int i = 0; i < numberOfPlants; i++){
            Plant plant = new Plant();
            putGameObjectsOnMap(plant);
        }
    }

    private void putPotionsOnMap(){
        int numberOfPotions = height*width/75;
        for (int i = 0; i < numberOfPotions; i++){
            Potion potion = new Potion();
            putGameObjectsOnMap(potion);
        }
    }

    private void putEquipmentOnMap(){
        putWeaponOnMap();
        putArmorOnMap();
    }

    private void putWeaponOnMap(){
        Weapon weapon = new Weapon(10);
        putGameObjectsOnMap(weapon);
    }

    private void putArmorOnMap(){
        Armor armor = new Armor(10);
        putGameObjectsOnMap(armor);
    }

    private int[] putGameObjectsOnMap(GameObject obj){
        while (true){
            int column = new Random().nextInt(width);
            int row = new Random().nextInt(height);
            if (generatedMap[row][column] == null){
                generatedMap[row][column] = obj;
                int[] position = {row, column};
                return position;
            }
        }
    }

    protected void moveCreatures() {
        for (Creature creature : creatures) {
            int position[] = creature.move();
            boolean allowed = false;

            if (generatedMap[position[2]][position[3]] == null){
                allowed = true;
            }
            else if (generatedMap[position[2]][position[3]] instanceof Item) {
                if (creature instanceof Hero) {
                    allowed = true;
                    ((Hero) creature).pickUpItem((Item) generatedMap[position[2]][position[3]]);
                }
            }
            if (allowed) {
                generatedMap[position[0]][position[1]] = null;
                generatedMap[position[2]][position[3]] = creature;
                creature.setPosition(position[2], position[3]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void generateMap() {
        generateSurroundingWalls();
        generateVerticalWall(0, width-1, 0);
        removeDoors();

    }

    protected void generateSurroundingWalls() {
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                if (i == 0 || i == height-1 || j == 0 || j == width-1){
                    generatedMap[i][j] = wall;
                }
            }
        }
    }

    private int generateWallIndex(int start, int finish, int index, boolean vertical) {
        int length = finish - start;
        if (length < 7) return 0;
        int newIndex = 0;
        for (int a = 0; a < 2*length; a++){
            newIndex = new Random().nextInt(length - 4);
            newIndex += 2;
            if (vertical) {
                newIndex += start;
                if (!(generatedMap[index][newIndex] instanceof Door)){
                    break;
                }
            }
            else {
                newIndex += start;
                if (!(generatedMap[newIndex][index] instanceof Door)){
                    break;
                }
            }
            newIndex = -1;
        }
        return newIndex;
    }

    private void generateVerticalWall(int start, int finish, int index){
        int newIndex = generateWallIndex(start, finish, index, true);
        if (newIndex < 1) return;
        generateVerticalWallDown( index, newIndex);

        newIndex = generateWallIndex(start, finish, index, true);
        if (newIndex < 1) return;
        generateVerticalWallUp(index, newIndex);
    }

    private void generateVerticalWallDown(int index, int newIndex) {
        for (int row = index + 1; row < height; row++){
            row = innerVerticalLoop(row, index, newIndex, row-1);
        }
    }

    private void generateVerticalWallUp(int index, int newIndex) {
        for (int row = index - 1; row >= 0; row--){
            row = innerVerticalLoop(row, index, newIndex, row+1);
            if (row == width) row = 0;
        }
    }

    private int innerVerticalLoop(int row, int index, int newIndex, int previousRow) {
        if (generatedMap[row][newIndex] instanceof Stationary){
            if (generatedMap[row][newIndex] instanceof Door) {
                generateDoor(previousRow, newIndex);
            }
            if (row - index > 1) {
                int doorIndex = new Random().nextInt(row - index - 1);
                generateDoor(doorIndex+1+index, newIndex);
                generateHorizontalWall(index, row, newIndex);
            }
            else if (row - index < -1) {
                int doorIndex = new Random().nextInt(index - row - 1);
                generateDoor(doorIndex+1+row, newIndex);
                generateHorizontalWall(row, index, newIndex);
            }
            return height;
        }
        else{
            generatedMap[row][newIndex] = wall;
            return row;
        }
    }

    private void generateHorizontalWall(int start, int finish, int index){
        int newIndex = generateWallIndex(start, finish, index, false);
        if (newIndex < 1) return;
        generateHorizontalWallRight(index, newIndex);

        newIndex = generateWallIndex(start, finish, index, false);
        if (newIndex < 1) return;
        generateHorizontalWallLeft(index, newIndex);
    }

    private void generateHorizontalWallRight(int index, int newIndex) {
        for (int column = index + 1; column < width; column++){
            column = innerHorizontalLoop(column, index, newIndex, column-1);
        }
    }

    private void generateHorizontalWallLeft(int index, int newIndex) {
        for (int column = index - 1; column >= 0; column--){
            column = innerHorizontalLoop(column, index, newIndex, column+1);
            if (column == width) column = 0;
        }
    }

    private int innerHorizontalLoop(int column, int index, int newIndex, int previousColumn) {
        if (generatedMap[newIndex][column] instanceof Stationary){
            if (generatedMap[newIndex][column] instanceof Door) {
                generateDoor(newIndex, previousColumn);
            }
            if (column - index > 1) {
                int doorIndex = new Random().nextInt(column - index - 1);
                generateDoor(newIndex, doorIndex+1+index);
                generateVerticalWall(index, column, newIndex);
            }
            else if (column - index < -1) {
                int doorIndex = new Random().nextInt(index - column - 1);
                generateDoor(newIndex, doorIndex+1+column);
                generateVerticalWall(column, index, newIndex);
            }
            return width;
        }
        else{
            generatedMap[newIndex][column] = wall;
            return column;
        }
    }

    protected GameObject[][] getActualMap() {
        return generatedMap;
    }

    public GameObject[][] getGeneratedMap() {
        GameObject[][] temp = new GameObject[height][width];
        for (int i = 0; i < height; i++) {
            temp[i] = generatedMap[i].clone();
        }
        return temp;
    }

    private String printConsoleSymbolWithColor(GameObject obj) {
        String color;

        switch (obj.getColor()) {
            case BLUE:
                color = "\033[0;34m";
                break;
            case RED:
                color = "\033[0;31m";
                break;
            case GREEN:
                color = "\u001b[32m";
                break;
            case ORANGE:
                color = "\033[0;35m";
                break;
            case YELLOW:
                color = "\033[0;33m";
                break;
            default:
                color = "\033[0;90m";
        }
        return color + obj.getSymbol() + "\033[0m";
    }

    protected void renderGeneratedToConsole() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (generatedMap[i][j] == null) {
                    System.out.print("   ");
                }
                else {
                    System.out.print(" " + printConsoleSymbolWithColor(generatedMap[i][j]) + " ");
                }
            }
            System.out.println();
        }
    }

    private void generateDoor(int row, int column){
        generatedMap[row][column] = new Door();
        int[] position = {row, column};
        doors.add(position);
    }

    private void removeDoors() {
        for (int[] position: doors) {
            generatedMap[position[0]][position[1]] = null;
        }
        doors.clear();
    }

    class Door extends Stationary{
        protected Door() {
            super('.', Color.GRAY);
        }
    }
}