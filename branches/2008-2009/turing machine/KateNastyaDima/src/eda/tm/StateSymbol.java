package eda.tm;

public class StateSymbol {

    private String state;
    private char symbol;

    public StateSymbol(String state, char symbol) {
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

    @Override
    public String toString() {
        return "(" + state + ")" + symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StateSymbol other = (StateSymbol) obj;
        if (this.state.equals(state) && (this.state == null || !this.state.equals(other.state))) {
            return false;
        }
        if (this.symbol != other.symbol) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.state != null ? this.state.hashCode() : 0);
        hash = 73 * hash + this.symbol;
        return hash;
    }
    
    
    
    
}
