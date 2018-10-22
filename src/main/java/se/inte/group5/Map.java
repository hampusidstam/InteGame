package se.inte.group5;

import java.util.Arrays;

public class Map {
    private int width, height;
    private GameObject[][] map, generatedMap;

    Map(int width, int height) {
        this.width = width;
        this.height = height;

        createMap();
        generateMap();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    private void createMap(){
        map = new GameObject[height][width];
    }

    private void generateMap(){
        generatedMap = new GameObject[height][width];
        Wall w = new Wall();

        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                if (i == 0 || i == height-1 || j == 0 || j == width-1){
                    generatedMap[i][j] = w;
                }
            }
        }
    }

    public GameObject[][] getGeneratedMap() {
        return generatedMap;
    }

    public GameObject[][] getMap(){
        return map;
    }

    public GameObject removeItem(int x, int y){
        if(x > width){
            throw new IndexOutOfBoundsException();
        }else if (y > height){
            throw new IndexOutOfBoundsException();
        }
        GameObject obj = map[x][y];
        map[x][y] = null;
        return obj;
    }

    public void addObjectToMap(GameObject obj, int x, int y){
        map[x][y] = obj;
    }

    private void renderFrameStars(){
        for(int i = 0; i < width; i++){
            System.out.print("*****");
        }
        System.out.print("**");
        System.out.println();
    }

    private String printConsoleSymbolWithColor(GameObject obj){
        String color;
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
            case YELLOW: color = "\033[0;33m";
                break;

            default:
                color = "\033[0m";
        }

        return color +obj.getSymbol() + "\033[0m";
    }

    public void renderToConsole(){
        System.out.println("Map["+ width +"][" + height + "]:");
        renderFrameStars();
        for(int i = 0; i<height; i++)
        {
            System.out.print("*");
            for(int j = 0; j<width; j++)
            {
                if(map[i][j] == null){
                    System.out.print("  .  ");
                }else{
                    System.out.print("  " + printConsoleSymbolWithColor(map[i][j]) + "  ");
                }
            }
            System.out.print("*");
            System.out.println();
        }
        renderFrameStars();
    }
}
