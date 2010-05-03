/*
 * Generic
 * soldatov dmitry ©, 2009
 */

import generic.*;
import java.util.LinkedList;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.Assert;

public class GeneralFunctionsTest {

    private int oneExamsNumber = 4;
    private int[] oneMarks = {3, 4, 4, 5};
    private Botan one = new Botan("  Студент Иван", "Иванов", "Иванович", Gender.Male, 28,
            "Geomex", oneExamsNumber, oneMarks);

    private int twoExamsNumber = 5;
    private int[] twoMarks = {3, 4, 4, 3, 5};
    private Botan two = new Botan("  Ботан Гадя", "Хренова", "Петрович", Gender.Female, 21,
            "матмех", twoExamsNumber, twoMarks);

    private Student[] kids = {one, two};
    private CoolParent parent = new CoolParent("Крутой Отец Ипполит", "Афансьеви", "Бендер",
            Gender.Male, 38, kids);
    
    private HumanList creator = new HumanList();
    private LinkedList humanList = new LinkedList();

    public void createHumanList() {
        humanList.add(parent);

        humanList.add(one);

        humanList.add(two);
    }

    @Test
    public void averageMarkTest() {
        createHumanList();
        Assert.assertEquals(creator.getBotansAverageMark(humanList), 3.9, 0.01);
    }

    @Test
    public void countMoneyTest() {
        createHumanList();
        Assert.assertEquals(creator.countParentMoney(humanList), 78.0, 0.1);
    }
}