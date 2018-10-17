package se.inte.group5;

public class Position {
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Position){
            Position otherPosition = (Position)other;
            if (otherPosition.getX() == x && otherPosition.getY() == y) return true;
        }
        return false;
    }
}
