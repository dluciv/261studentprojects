//by Skorodumov Kirill gr.: 261
//parentsandstudents task
//tests

package parentsandstudents;

import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedList;

public class Tests{
	
	static final Integer PARENTS_COUNT = 10;
    static final Integer MONEY_PER_PARENT = 10;
    static final Integer BOTAN_COUNT = 10;

    private static class CoolParentTest extends CoolParent {

        public CoolParentTest() {
            super("", "", "", Sex.MALE, 10, null);
        }

        @Override
        public Integer getMoney() {
            return MONEY_PER_PARENT;
        }
    }


    @Test
    public void ProcessorTest() {
        LinkedList<IHuman> humans = new LinkedList<IHuman>();
        int markSum = 0;
        int markCount = 0;
        for (int i = 0; i < BOTAN_COUNT; i++) {
            Botan bt = new Botan("", "", "", Sex.MALE, 16, "");

            humans.add(bt);

            int examsCount = bt.getExamNum();
            markCount += examsCount;
            for (int markId = 0; markId < examsCount; markId++) {
                markSum += bt.getMark(markId);
            }
        }

        for (int i = 0; i < PARENTS_COUNT; i++) {
            humans.add(new CoolParentTest());
        }

        int mark = Calculator.calcMark(humans);

        Assert.assertEquals(markSum / markCount, mark);
    }

    @Test
    public void CoolParentTest_getMoney() {
        Student[] students = new Student[BOTAN_COUNT];
        for(int i = 0; i<students.length; i++)
            students[i] = new Student("", "", "", Sex.MALE, 16, "");
        
        CoolParent parent = new CoolParent("", "", "", Sex.MALE, 16, students);
        int money = parent.getMoney();
        
        Assert.assertEquals(30, money);
    }
}
