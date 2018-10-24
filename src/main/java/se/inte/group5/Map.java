package se.inte.group5;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private int width, height;
    private GameObject[][] map, generatedMap;
    private Wall wall = new Wall();
    private ArrayList<Creature> creatures = new ArrayList<>();
    private Hero hero;

    //TODO: Map should probably take hero as an argument (that way a new level can be created without loosing hero progress)
    Map(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
        createMap();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    private void createMap() {
        map = new GameObject[height][width];
    }

    public void placeGameObject(int y, int x, GameObject gameObject) {
        map[y][x] = gameObject;
        if (gameObject instanceof Creature) {
            ((Creature) gameObject).setPosition(y, x);
            creatures.add((Creature) gameObject);
        }
    }

    public void moveCreatures(char ch) {
        for (Creature c : creatures) {
            int pos[] = c.moveCreature(ch);

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
                    map[pos[0]][pos[1]] = null;
                    map[pos[2]][pos[3]] = c;
                    c.setPosition(pos[2], pos[3]);
                }
            }
        }
        //repaint();
    }

    public GameObject[][] getMap() {
        return map;
    }

    public GameObject removeItem(int x, int y) {
        //Removes item from map
        if (x > width) {
            throw new IndexOutOfBoundsException();
        }
        else if (y > height) {
            throw new IndexOutOfBoundsException();
        }
        GameObject obj = map[x][y];
        map[x][y] = null;
        return obj;
    }


    private void renderFrameStars() {
        for (int i = 0; i < width; i++) {
            System.out.print("*****");
        }
        System.out.print("**");
        System.out.println();
    }

    private String printConsoleSymbolWithColor(GameObject obj) {
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
            case YELLOW:
                color = "\033[0;33m";
                break;

            default:
                color = "\033[0m";
        }

        return color + obj.getSymbol() + "\033[0m";
    }

    public void renderToConsole() {
        System.out.println("Map[" + width + "][" + height + "]:");
        renderFrameStars();
        for (int i = 0; i < height; i++) {
            System.out.print("*");
            for (int j = 0; j < width; j++) {
                if (map[i][j] == null) {
                    System.out.print("  .  ");
                }
                else {
                    System.out.print("  " + printConsoleSymbolWithColor(map[i][j]) + "  ");
                }
            }
            System.out.print("*");
            System.out.println();
        }
        renderFrameStars();
    }
}