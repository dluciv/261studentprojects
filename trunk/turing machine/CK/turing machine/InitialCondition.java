
package turingmachine;

/**
 *
 * @author Кирилл
 */
public class InitialCondition {
    protected String state;
    protected String symbol;
    public InitialCondition(String state, String symbol){
        this.state = state;
        this.symbol = symbol;
    }
    public String getState(){
        return state;
    }
    public String getSymbol(){
        return symbol;
    }
    public void setState(String state) {
       this.state = state;
   }
    public void setSymbol (String symbol) {
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
        final InitialCondition other = (InitialCondition) obj;
        if (this.state.equals(state) && (this.state == null || !this.state.equals(other.state))) {
            return false;
        }
        if (!this.symbol.equals(other.symbol) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.state != null ? this.state.hashCode() : 0);
        hash = 73 * hash + this.symbol.hashCode();
        return hash;
    }

}
