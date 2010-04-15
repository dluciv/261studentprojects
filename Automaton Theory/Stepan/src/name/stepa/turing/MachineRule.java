package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class MachineRule {

    public final static char ANY_VALUE = '_';

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
