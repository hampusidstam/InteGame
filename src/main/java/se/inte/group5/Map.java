package se.inte.group5;

import java.util.ArrayList;

public class Map {
    private int width, height;
    private GameObject[][] map;
    private ArrayList<Creature> creatures;

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
        Wall w = new Wall();
        map[0][0] = w;
    }

    //Behövs för test
    public void placeGameObject(int y, int x, GameObject gameObject) {
        map[y][x] = gameObject;
    }

    //Anropas i intervall
    public void moveCreatures() {
        for(Creature c: creatures) {
            int[] pos = c.moveCreature();

            if (!(map[pos[2]][pos[3]] instanceof Stationary)) {
                boolean allowed = false;
                if (c instanceof Hero) {
                    allowed = true;
                    if (map[pos[2]][pos[3]] instanceof Item) {
                        ((Hero) c).pickUpItem(map[pos[2]][pos[3]]);
                    }
                } else {
                    if (map[pos[2]][pos[3]] == null) {
                        allowed = true;
                    }
                }
                if (allowed) {
                    map[pos[2]][pos[3]] = c;
                    map[pos[0]][pos[1]] = null;
                    c.setPosition(pos[2], pos[3]);
                }
            }
        }
        //repaint();
    }

    public GameObject[][] getMap(){
        return map;
    }

    public void removeItem(){
        //TODO
    }
}
