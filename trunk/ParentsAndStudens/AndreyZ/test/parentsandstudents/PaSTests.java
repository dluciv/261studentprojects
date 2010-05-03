/*
 * SubClass PaSTests
 * Zubrilin Andrey,261 gr (c) 2009
 */
package parentsandstudents;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaSTests {

    @Test
    public void PeopleGenerationTest() {
        List<IHuman> crowd = PeopleGeneration.generateCollection();
        for (IHuman person : crowd)
            PeopleGeneration.info(person);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullHumanTest() {
        PeopleGeneration.info(null);
    }

    @Test
    public void moneyCounterTest(){
        LinkedList<IHuman> list = new LinkedList();
        Student[] crowd = new Student[1];
        Student student = new Student("", "", "", true, 0, "");
        crowd[0] = student;
        CoolParent parent = new CoolParent("", "", "", true, 0, crowd);
        parent.money = 30;
        list.add(parent);
        assertEquals(parent.money, CoolParent.parentMoneyCounter(list));
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoRichManTest() {
        CoolParent.parentMoneyCounter(null);
    }

}
