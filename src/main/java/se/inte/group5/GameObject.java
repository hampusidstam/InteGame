package se.inte.group5;

public abstract class GameObject {
    private char symbol;
    private Color color;

    public GameObject(char symbol, Color color){
        this.symbol = symbol;
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public char getSymbol(){
        return this.symbol;
    }
}
