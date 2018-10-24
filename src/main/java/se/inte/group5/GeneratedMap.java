package se.inte.group5;

import java.util.ArrayList;
import java.util.Random;

public class GeneratedMap {
    private int width, height;
    private GameObject[][] generatedMap;
    private Wall wall = new Wall();
    private ArrayList<Creature> creatures = new ArrayList<>();
    private Hero hero;

    public GeneratedMap(int width, int height, Hero hero) {
        this.width = width;
        this.height = height;
        this.hero = hero;
        generateMap();
        putHeroOnMap(hero);
        putMonstersOnMap();
        putConsumablesOnMap();
        renderGeneratedToConsole();
    }

    private void putHeroOnMap(Hero hero) {
        generatedMap[1][1] = hero;
        hero.setPosition(1, 1);
        creatures.add(hero);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }


    private void putMonstersOnMap() {
        int numberOfMonsters = height * width / 50;
        for (int i = 0; i < numberOfMonsters; i++) {
            Monster monster = new Monster(10);
            int[] position = putGameObjectsOnMap(monster);
            monster.setPosition(position[0], position[1]);
            creatures.add(monster);
        }
    }

    private void putConsumablesOnMap() {
        putPlantsOnMap();
        putPotionsOnMap();
    }

    private void putPlantsOnMap() {
        int numberOfPlants = height * width / 30;
        for (int i = 0; i < numberOfPlants; i++) {
            Plant plant = new Plant();
            putGameObjectsOnMap(plant);
        }
    }

    private void putPotionsOnMap() {
        int numberOfPotions = height * width / 75;
        for (int i = 0; i < numberOfPotions; i++) {
            Potion potion = new Potion();
            putGameObjectsOnMap(potion);
        }
    }

    private int[] putGameObjectsOnMap(GameObject obj) {
        while (true) {
            int column = new Random().nextInt(width);
            int row = new Random().nextInt(height);
            if (generatedMap[row][column] == null) {
                generatedMap[row][column] = obj;
                int[] position = {row, column};
                return position;
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    private void generateMap() {
        generatedMap = new GameObject[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    generatedMap[i][j] = wall;
                }
            }
        }
        generateVerticalWall(0, width - 1, 0);
    }

    private int generateMapGenerateWallIndex(int start, int finish, int index) {
        int length = finish - start;
        if (length < 7) {
            return 0;
        }
        int newIndex;
        while (true) {
            newIndex = new Random().nextInt(length - 4);
            newIndex += 2;
            if (generatedMap[newIndex][index] instanceof Stationary) {
                return newIndex;
            }
        }
    }

    private void generateVerticalWall(int start, int finish, int index) {
        int newIndex = generateMapGenerateWallIndex(start, finish, index);
        if (newIndex == 0) {
            return;
        }
        generateVerticalWallDown(start, finish, index, newIndex);

        newIndex = generateMapGenerateWallIndex(start, finish, index);
        if (newIndex == 0) {
            return;
        }
        generateVerticalWallUp(start, finish, index, newIndex);
    }

    private void generateVerticalWallDown(int start, int finish, int index, int newIndex) {
        for (int row = index + 1; row < height; row++) {
            innerVerticalLoop(row, index, newIndex);
        }
    }

    private void generateVerticalWallUp(int start, int finish, int index, int newIndex) {
        for (int row = index - 1; row >= 0; row--) {
            innerVerticalLoop(row, index, newIndex);
        }
    }

    private void innerVerticalLoop(int row, int index, int newIndex) {
        if (generatedMap[row][newIndex] instanceof Stationary) {
            if (row - index > 1) {
                int doorIndex = new Random().nextInt(row - index - 1);
                generatedMap[doorIndex + 1 + index][newIndex] = null;
            }
            else if (row - index < -1) {
                int doorIndex = new Random().nextInt(index - row - 1);
                generatedMap[doorIndex + 1 + row][newIndex] = null;
            }
            else {
                return;
            }
            generateHorizontalWall(index, row, newIndex);
        }
        else {
            generatedMap[row][newIndex] = wall;
        }
    }

    private void generateHorizontalWall(int start, int finish, int index) {
        int newIndex = generateMapGenerateWallIndex(start, finish, index);
        if (newIndex == 0) {
            return;
        }
        generateHorizontalWallRight(start, finish, index, newIndex);

        newIndex = generateMapGenerateWallIndex(start, finish, index);
        if (newIndex == 0) {
            return;
        }
        generateHorizontalWallLeft(start, finish, index, newIndex);
    }

    private void generateHorizontalWallRight(int start, int finish, int index, int newIndex) {
        for (int column = index + 1; column < width; column++) {
            innerHorizontalLoop(column, index, newIndex);
        }
    }

    private void generateHorizontalWallLeft(int start, int finish, int index, int newIndex) {
        for (int column = index - 1; column >= 0; column--) {
            innerHorizontalLoop(column, index, newIndex);
        }
    }

    private void innerHorizontalLoop(int column, int index, int newIndex) {
        if (generatedMap[newIndex][column] instanceof Stationary) {
            if (column - index > 1) {
                int doorIndex = new Random().nextInt(column - index - 1);
                generatedMap[newIndex][doorIndex + 1 + index] = null;
            }
            else if (column - index < -1) {
                int doorIndex = new Random().nextInt(index - column - 1);
                generatedMap[newIndex][doorIndex + 1 + column] = null;
            }
            else {
                return;
            }
            generateVerticalWall(index, column, newIndex);
        }
        else {
            generatedMap[newIndex][column] = wall;
        }
    }


    public GameObject[][] getGeneratedMap() {
        GameObject[][] temp = generatedMap;
        return temp;
    }

    private String printConsoleSymbolWithColor(GameObject obj) {
        String color = "\033[0m";
        //RED, YELLOW, BLUE, GRAY, GREEN, ORANGE
        switch (obj.getColor()) {
            case BLUE:
                color = "\033[0;34m";
                break;
            case RED:
                color = "\033[0;31m";
                break;
            case GRAY:
                color = "\033[0;90m";
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
        }

        return color + obj.getSymbol() + "\033[0m";
    }

    public void renderGeneratedToConsole() {
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
}
