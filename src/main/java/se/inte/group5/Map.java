package se.inte.group5;

public class Map {

    private int width;
    private int height;
    private GameObject[][] map;

    Map(int width, int height) {
        this.width = width;
        this.height = height;

        createMap();
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

    public GameObject[][] getMap(){
        return map;
    }
}
