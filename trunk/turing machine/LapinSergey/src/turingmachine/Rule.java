/**
 *
 * @author Lapin Sergey 261group
 */
package turingmachine;

import java.util.Vector;

public class Rule {
    private InitialCondition initCondition;
    private Action act;

    public String getString()
    {
        return initCondition.getString() + "->" + act.getString();
    }

    public InitialCondition getInitCondition() {
        return initCondition;
    }

    public void setInitCondition(InitialCondition initCondition) {
        this.initCondition = initCondition;
    }

    public Action getAct() {
        return act;
    }

    public void setAct(Action act) {
        this.act = act;
    }

    public Object[] createRuleInfo(){
        Object res[] = {initCondition.state, initCondition.symbol, act.state, act.symbol, act.getDirection()};
        return res;
    }

    public Vector<Object> createRuleVectorInfo(){
        Vector<Object> res = new Vector<Object>();
        res.add(initCondition.state);
        res.add(initCondition.symbol);
        res.add(act.state);
        res.add(act.symbol);
        res.add(act.getDirection());
        return res;
    }
}
