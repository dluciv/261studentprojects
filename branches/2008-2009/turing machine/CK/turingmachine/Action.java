
package turingmachine;

/**
 *
 * @author Кирилл
 */
public class Action extends InitialCondition{
   private String direction;
   Action(String state, String symbol, String direction){
       super( state, symbol);
       this.direction = direction;
   }
   public String getDirection() {
       return direction;
   }
   public void setDirection(String direction) {
       this.direction = direction;
   }
}
