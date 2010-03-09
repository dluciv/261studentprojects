/*
 * Tests for ParentsAndStudents application.
 * Korshakov Stepan - 261 Group - (c) 2009
 */

import hotheart.parentsandstudens.Botan;
import hotheart.parentsandstudens.CoolParent;
import hotheart.parentsandstudens.Generator;
import hotheart.parentsandstudens.IHuman;
import hotheart.parentsandstudens.Processor;
import hotheart.parentsandstudens.Sex;
import hotheart.parentsandstudens.Student;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
  * @author Korshakov Stepan
 */
public class GenericsTests {

    static final int PARENTS_COUNT = 10;
    static final int MONEY_PER_PARENT = 10;
    static final int BOTAN_COUNT = 10;

    private static class CoolParentTestImpl extends CoolParent {

        public CoolParentTestImpl() {
            super("", "", "", Sex.MALE, 10, null);
        }

        @Override
        public int getMoney() {
            return MONEY_PER_PARENT;
        }
    }

    @Test
    public void ProcessorTest_calcMoney() {

        List<IHuman> humans = new LinkedList<IHuman>();
        for (int i = 0; i < PARENTS_COUNT; i++) {
            humans.add(new CoolParentTestImpl());
        }

        for (int i = 0; i < BOTAN_COUNT; i++) {
            humans.add(Generator.generateStudent("", ""));
        }

        int money = Processor.calcMoney(humans);

        assertEquals(PARENTS_COUNT * MONEY_PER_PARENT, money);
    }

    @Test
    public void ProcessorTest_calcMark() {
        List<IHuman> humans = new LinkedList<IHuman>();
        int markSum = 0;
        int markCount = 0;
        for (int i = 0; i < BOTAN_COUNT; i++) {
            Botan bt = new Botan("", "", "", Sex.MALE, 16, "");

            humans.add(bt);

            int examsCount = bt.getExamsCount();
            markCount += examsCount;
            for (int markId = 0; markId < examsCount; markId++) {
                markSum += bt.getExamMark(markId);
            }
        }

        for (int i = 0; i < PARENTS_COUNT; i++) {
            humans.add(new CoolParentTestImpl());
        }

        int mark = Processor.calcMark(humans);

        assertEquals(markSum / markCount, mark);
    }

    @Test
    public void CoolParentTest_getMoney() {
        Student[] students = new Student[BOTAN_COUNT];
        for(int i = 0; i<students.length; i++)
            students[i] = new Student("", "", "", Sex.MALE, 16, "");
        
        CoolParent parent = new CoolParent("", "", "", Sex.MALE, 16, students);
        int money = parent.getMoney();
        
        assertEquals(30, money);
    }
}
