//(с) Кривых Алексей 2009г.
//exception
package exception;

public class Pig implements Animal {

    public State eat(Food food) {
        if (food == Food.TURNIP) {
            return State.CHEESED;
        } else {
            return State.HUNGRY;
        }
    }
}
