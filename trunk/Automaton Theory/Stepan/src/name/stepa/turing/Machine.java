package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Machine {

    public static enum Move {
        LEFT, RIGHT, NONE;

        private Move() {
        }
    }

    public static class MachineRule {

        public static char VALUE_UNKNOWN = '*';

        public int oldState;
        public char oldValue;
        public char newValue;
        public int newState;
        public Move movement;

        public MachineRule(int oldState, char oldValue, int newState, char newValue, Move movement) {
            this.oldState = oldState;
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.newState = newState;
            this.movement = movement;
        }
    }

    public int currentState;
    public int currentPos;
    public MachineRule[] rules;
    public InfiniteTape tape;


    public Machine(int startupState, int startupPos, String startupTape, MachineRule[] rules) {
        this.currentState = startupState;
        this.currentPos = startupPos;
        this.rules = rules;

        char[] data = startupTape.toCharArray();
        for (int i = 0; i < data.length; i++) {
            tape.set(i, data[i]);
        }
    }

    public boolean iterate() throws Exception {
        if (currentPos < 0)
            throw new Exception("Wrong state!");

        char currentValue = tape.get(currentPos);
        for (MachineRule rule : rules) {
            if ((rule.oldState == currentState) && ((rule.oldValue == currentValue) || (rule.oldValue == MachineRule.VALUE_UNKNOWN))) {

                if (rule.newValue != MachineRule.VALUE_UNKNOWN) {
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
}
