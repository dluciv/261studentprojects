/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

import java.util.LinkedList;
import java.util.List;
import junit.framework.*;

public class CollectionTests extends TestCase {
	private static final int NUMBER_PEOPLE = 5;
	private static final int DUMMY_MONEY = 10;
	private static final int EXPECTED_AMOUNT = 30;
	
	// a dummy family class
	private class DummyFamily {
		private Student kid1;
		private Student kid2;
		private CoolParent parent;
		
		public DummyFamily(Student kid1, Student kid2, CoolParent parent){
			this.kid1 = kid1;
			this.kid2 = kid2;
			this.parent = parent; 
		}
	}
	
	public void test_getMoney(){
		Student kid1 = new Student("", "", "", Sex.MALE, 12, "");
		Student kid2 = new Student("", "", "", Sex.MALE, 12, "");
		Student[] children = {kid1, kid2};
		CoolParent parent = new CoolParent("","", "",Sex.MALE, 40,children);
		
		DummyFamily family = new DummyFamily(kid1, kid2, parent);
		
		/*the amount of money that this parent has is:
		 * NUMBER_KIDS = 2
		 * NUMBER_EXAMS = 5
		 * every kid is a Student and every mark is 3
		 * so SUM_MARKS = NUMBER_KIDS*NUMBER_EXAMS*3 = 30
		 * AVERAGE_MARK = SUM_MARKS / NUMBER_EXAMS = 3
		 * EXPECTED_AMOUNT = AVERAGE_MARK * 10 = 30
		 * */		
		assertEquals(EXPECTED_AMOUNT,family.parent.getMoney());

	}
	
	
	private class DummyParent extends CoolParent{
		public DummyParent(){
			super("","","",Sex.MALE, 40, null);
		}
		public int getMoney(){
			return DUMMY_MONEY;
		}
	}
	
	public void test_getMoneyInCollection(){
		List<IHuman> list = new LinkedList<IHuman>();
		
		//add 5 dummy parents
		for (int i = 0; i < NUMBER_PEOPLE; i++) {
            list.add(new DummyParent());
        }
		//add 5 random children
		for (int i = 0; i < NUMBER_PEOPLE; i++) {
            list.add(RandomListGenerator.generateStudent("",""));
        }
		int money = CollectionFunctions.getMoneyInCollection(list);
		//no matter what kind of grades the children have
		//DummyParent has a fixed amount of money (DUMMY_MONEY)
		assertEquals(NUMBER_PEOPLE*DUMMY_MONEY, money);
	}
	
	public void test_getAverageGradeInCollection(){
		int sumMarks = 0,
			numMarks = 0,
		    testedMark = 0;
		List<IHuman> list = new LinkedList<IHuman>();
		
		//add 5 dummy parents
		for (int i = 0; i < NUMBER_PEOPLE; i++) {
            list.add(new DummyParent());
        }
		//add 5 Botans
		for (int i = 0; i < NUMBER_PEOPLE; i++) {
			Botan b = new Botan("","","",Sex.MALE,17,""); 
            list.add(b);
            
            int numCurrExams = b.getNumberOfExams();
            numMarks += numCurrExams;
            for (int j = 0; j < numCurrExams; j++){
            	sumMarks += b.getMark(j);
            }
        }
		
		testedMark = CollectionFunctions.getAverageGradeInCollection(list);
		assertEquals(sumMarks/numMarks, testedMark);
	}
}
