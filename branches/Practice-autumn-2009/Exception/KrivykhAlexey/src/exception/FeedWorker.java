//(с) Кривых Алексей 2009г.
//exception
package exception;

public class FeedWorker {

    public State feedAnimal(Animal animal, Food food)
            throws Exception {
        if ((animal == null)|(food == null)) {
            throw new NullPointerException("Invalid parameter");
        } else {
            return animal.eat(food);
        }
    }
}
