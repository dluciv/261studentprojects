//Кривых Алексей 2009г.
//Интерфейс
package zoo;

public class Lion implements Animal {

    public State eat(Food food) {
        if (food == Food.MEET) {
            return State.CHEESED;
        } else {
            return State.HUNGRY;
        }
    }
}
