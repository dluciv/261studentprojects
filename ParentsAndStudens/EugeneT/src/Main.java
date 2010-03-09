/**
 * program Parents and children
 * @author Eugene Todoruk
 * group 261
 */
import parentsAndChildren.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<IHuman> people = Generator.generatePeople();

        MyCollection.show(people);

        System.out.println();
        System.out.println("Кол-во денег у хороших родителей: " + MyCollection.getParentsMoney(people));
        System.out.println("Средняя оценка всех ботанов: " + MyCollection.getMeanRating(people));
    }
}
