//by Skorodumov Kirill gr.: 261
//parentsandstudents task
//tests

package parentsandstudents;

import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedList;

public class Tests{
	
	private static final Integer PARENTS_COUNT = 10;
    private static final Integer MONEY_PER_PARENT = 10;
    private static final Integer BOTAN_COUNT = 10;

    private class CoolParentTest extends CoolParent {

        public CoolParentTest() {
            super("", "", "", Sex.MALE, 10, null);
        }

        @Override
        public Integer getMoney() {
            return MONEY_PER_PARENT;
        }
    }
    
    @Test
    public void CalculatorTest2()
    {
    	LinkedList<IHuman> humans = new LinkedList<IHuman>();
    	
    	for(int i = 0; i < PARENTS_COUNT; i++)
    	{
    		CoolParentTest parent = new CoolParentTest();
    		humans.add(parent);
    	}
    	Integer actMoney = Calculator.calcMoney(humans);
    	Integer expMoney = PARENTS_COUNT*MONEY_PER_PARENT;
    	
    	Assert.assertEquals(expMoney, actMoney);
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
