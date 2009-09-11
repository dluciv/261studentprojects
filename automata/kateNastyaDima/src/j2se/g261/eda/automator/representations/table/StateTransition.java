/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.representations.table;

/**
 *
 * @author nastya
 */
class StateTransition {
    private int state;
    private char symbol;
    
    StateTransition(int state, char symbol){
        this.state = state;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StateTransition other = (StateTransition) obj;
        if (this.state != other.state) {
            return false;
        }
        if (this.symbol != other.symbol) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.state;
        hash = 23 * hash + this.symbol;
        return hash;
    }
    
    
    
    
}
