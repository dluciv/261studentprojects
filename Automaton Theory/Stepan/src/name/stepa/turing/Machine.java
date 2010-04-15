package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Machine {

    public static final int STATE_STOP = -1;

    public int currentState;
    public int currentPos;
    public MachineRule[] rules;
    public InfiniteTape tape;


    public Machine(int startState, int startPos, String startupTape, MachineRule[] rules) {
        this.currentState = startState;
        this.currentPos = startPos;
        this.rules = rules;
        this.tape = new InfiniteTape();

        char[] data = startupTape.toCharArray();
        for (int i = 0; i < data.length; i++) {
            tape.set(i, data[i]);
        }
    }

    public boolean iterate(boolean isLogging) {
        if (currentState == STATE_STOP)
            return false;

        char currentValue = tape.get(currentPos);
        for (MachineRule rule : rules) {
            if ((rule.oldState == currentState) && ((rule.oldValue == currentValue) ||
                    (rule.oldValue == MachineRule.ANY_VALUE))) {

                if (isLogging) {
                    System.out.println(String.format("Selected rule: (%d,%c)->(%d,%c,%s)", rule.oldState, rule.oldValue, rule.newState, rule.newValue, rule.movement.toString()));
                }

                if (rule.movement == Move.STOP) {
                    currentState = STATE_STOP;
                    return false;
                }

                if (rule.newValue != MachineRule.ANY_VALUE) {
                    tape.set(currentPos, rule.newValue);
                }

                currentState = rule.newState;

                if (rule.movement == Move.LEFT)
                    currentPos--;
                else if (rule.movement == Move.RIGHT)
                    currentPos++;

                return true;
            }
        }
        return false;
    }

    public String getStateString() {
        StringBuilder res = new StringBuilder();
        res.append("State:");
        res.append(currentState);
        res.append('\n');
        res.append("Tape:");
        res.append('\n');
        res.append(tape.getStateString(currentPos));
        res.append('\n');
        return res.toString();
    }

}
