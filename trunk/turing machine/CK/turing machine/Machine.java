
package turingmachine;

/**
 *
 * @author Кирилл
 */
import java.util.ArrayList;
import java.util.HashMap;
public class Machine {
    ArrayList<String> tape = new ArrayList<String> ();
    String first, fin;
    HashMap<InitialCondition, Action> rules = new HashMap < InitialCondition, Action >();
    int tapePointer = 0;
    
}
