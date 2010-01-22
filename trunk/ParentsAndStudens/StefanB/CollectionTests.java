package fathers_sons;

import java.util.LinkedList;
import java.util.List;
import junit.framework.*;

/**
 * TODO:
 * 		-dummy families, dummy list
 * 		-getMoney Test
 * 		-getAverageGrade Test
 */

public class CollectionTests extends TestCase {
	static final int NUMBER_PEOPLE = 5;
	static final int DUMMY_MONEY = 10;
	
	private static class DummyParent extends CoolParent{
		public DummyParent(){
			super("","","",IHuman.Sex.male, 40, null);
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
		assertEquals(5*DUMMY_MONEY, money);
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
			Botan b = new Botan("","","",IHuman.Sex.male,17,""); 
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
