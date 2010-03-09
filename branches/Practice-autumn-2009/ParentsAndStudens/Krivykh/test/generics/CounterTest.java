/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generics;

import generics.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class CounterTest {

    @Test
    public void testMeanMarks() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        List<IHuman> humans = new LinkedList<IHuman>();
        List<Student> children = new LinkedList<Student>();
        Student botan = new Botan("firstName", "secondName", "patronymic",
                EnumSex.MALE, 17, "faculty");
        children.add(botan);
        IHuman coolParent = new CoolParent("firstName", "secondName", "patronymic",
                                           EnumSex.MALE, 43, children);
        humans.add(coolParent);

        //не работает хз как сделать :)
        int[] mark = {4,4,4,4};
        Class botanClass = botan.getClass();
        Field marks = botanClass.getDeclaredField("marks");
        marks.setAccessible(true);
        marks.set(botan, mark);

        assertTrue(Counter.meanMarks(humans)== 4);
    }

    @Test
    public void testMoney()throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        List<IHuman> humans = new LinkedList<IHuman>();
        List<Student> children = new LinkedList<Student>();
        children.add(new Student("firstName", "secondName", "patronymic",
                EnumSex.MALE, 17, "faculty"));
        IHuman coolParent = new CoolParent("firstName", "secondName", "patronymic",
                                           EnumSex.MALE, 43, children);
        //рефлексия
        Class parentClass = coolParent.getClass();
        Field money = parentClass.getDeclaredField("money");
        money.setAccessible(true);
        money.set(coolParent, 150);

        humans.add(coolParent);
        assertTrue(Counter.money(humans)== 150);
    }

}