/* @author Eugene Todoruk
 * group 261
 */

import parentsAndChildren.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;

public class MyCollectionTest {

    @Test
    public void testGetParentsMoney() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        List<IHuman> people = new LinkedList();
        Student[] children = new Student[1];
        Student child = new Student("a", "a", "a", Sex.MALE, 10, "s");
        children[0] = child;
        Parent parent = new CoolParent("b", "b", "b", Sex.MALE, 60, children);

        //Мега hack =)
        Class parentClass = parent.getClass();
        Field money = parentClass.getDeclaredField("money");
        money.setAccessible(true);
        money.set(parent, 100);

        people.add(parent);

        int expResult = 100;
        int result = MyCollection.getParentsMoney(people);

        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionInGetParentsMoney() {
        MyCollection.getParentsMoney(null);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroInGetMeanRating() {
        List<IHuman> people = new LinkedList();
        Student child = new Student("a", "a", "a", Sex.MALE, 10, "s");

        people.add(child);

        MyCollection.getMeanRating(people);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionInGetMeanRating() {
        MyCollection.getMeanRating(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionInShow() {
        MyCollection.show(null);
    }

    @Test
    public void testShow() {
        List<IHuman> people = Generator.generatePeople();

        MyCollection.show(people);
    }
}