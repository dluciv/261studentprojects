package eda.tm;

public class StateSymbolMove {

    private String state;
    private char symbol;
    private Moving move;

    public StateSymbolMove(Moving move, String state, char symbol) {
        this.move = move;
        this.state = state;
        this.symbol = symbol;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Moving getMove() {
        return move;
    }

    public void setMove(Moving move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return "(" + state + ")" + symbol + (move == Moving.RIGHT ? "R" : (move == Moving.LEFT ? "L" : "N"));
    }
}
