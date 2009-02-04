package turingmachine;

/**
 *
 * @author Кирилл
 */
public class Action extends InitialCondition{

    private String direction;

    Action() {

    }

    Action(String state, Character symbol, String direction){
       super( state, symbol);
       this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String getString()
    {
        return state + " " + symbol + " " + direction;
    }
}
