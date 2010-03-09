/*
 * Parents and Students
 * Collection Tests
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollectionTest {

    @Test
    public void testGetCoolFathersMoney() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        List<IHuman> people = new LinkedList();
        Student[] kids = new Student[1];

        Student kid = new Student("Артем", "Панов", "Владимирович", Sex.MALE, 19, "Юридический");
        kids[0] = kid;
        Parent parent = new CoolParent("Владимир", "Панов", "Владимирович", Sex.MALE, 45, kids);
        people.add(parent);
        people.add(kid);
        int expResult = 30;
        int result = Collection.getCoolFathersMoney(people);

        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionInGetParentsMoney() {
        Collection.getCoolFathersMoney(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRating1() {
        Collection.getRating(null);
    }

    public void testGetRating2() {
        List<IHuman> people = Generator.generatePeople();

        Collection.getRating(people);
    }

    @Test(expected = ArithmeticException.class)
    public void testGetRating3() {
        List<IHuman> people = new LinkedList();
        Student kid = new Student("Артем", "Панов", "Юрьевич", Sex.MALE, 19, "Юридический");

        people.add(kid);
        Collection.getRating(people);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShow1() {
        Collection.show(null);
    }

    @Test
    public void testShow2() {
        List<IHuman> people = Generator.generatePeople();

        Collection.show(people);
    }
}