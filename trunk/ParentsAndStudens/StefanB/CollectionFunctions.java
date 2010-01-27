/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

import java.util.List;

public class CollectionFunctions {

	public static void printHumanList (List<? extends IHuman> c){
		for (IHuman h : c){
	    	if ((h instanceof Botan)||(h instanceof Student)){
	    		System.out.print("	");
	    	}
			h.printIdentity();
		}
	}
	
	//returns the sum of money every parent has in the list
	public static int getMoneyInCollection(List<? extends IHuman> c){
		int money = 0;
		for(IHuman human : c){
			if (human instanceof CoolParent){
				money += ((CoolParent)human).getMoney();
			}
		}
		return money;
	}
	
	//returns the average grade in a list
	public static int getAverageGradeInCollection(List<? extends IHuman> c){
		int sumGrades = 0,
			numBotans = 0;
		for (IHuman human : c){
			if (human instanceof Botan){
				int numExams = ((Botan)human).getNumberOfExams();
				numBotans += numExams;
				for (int i = 0; i < numExams; i++){
					sumGrades += ((Botan)human).getMark(i);
				}
			}
		}
		return sumGrades / numBotans;
	}
}
