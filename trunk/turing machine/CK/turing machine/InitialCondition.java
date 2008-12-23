
package turingmachine;

/**
 *
 * @author Кирилл
 */
public class InitialCondition {
    String state;
    String symbol;
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
}
