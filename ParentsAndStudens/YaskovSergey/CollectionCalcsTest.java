/*
 * tests for "Fathers and Children" program: CollectionClacs-class tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package tests;

import org.junit.*;
import org.junit.Assert.*;
import java.util.LinkedList;
import fathersandchildren.*;

public class CollectionCalcsTest {
    // there we will create two students and one father;
    private static Parent createParent() {
        Student student = new Student("Грубая", "Вазя", "Калиновна", Sex.FEMALE, 22,
            "Древообработки", 3);
        Botan botan = new Botan("Добрый", "Егор", "Генрихович", Sex.MALE, 27,
            "Сексологии и права", 4);
        int[] botansMarks = {3, 5, 4, 4};

        // we've to eliminate random to check collection claculation functions;
        botan.marksList = botansMarks;
        botan.averageGrades = 4;

        Student[] kids = {student, botan};
        CoolParent father = new CoolParent("Странный", "Аристарх", "Иванович", Sex.MALE,
            35, kids);

        return father;
    }

    private static LinkedList<IHuman> createCollection() {
        LinkedList<IHuman> result = new LinkedList<IHuman>();
        Parent father = createParent();

        result.add(father);
        for (int j = 0; j < father.students.length; j++) {
                result.add(father.students[j]);
        }

        return result;
    }

    @Test
    public void coolParentsMoneyTest() {
        Assert.assertEquals(CollectionCalcs.calcAllMoney(createCollection()), 35, 0.01);
    }

    @Test
    public void averageGradesTest() {
        Assert.assertEquals(CollectionCalcs.calcAverageGradesOfAllBotans(createCollection()), 4, 0.01);
    }
}
