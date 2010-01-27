/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

import java.util.Random;

public class Botan extends Student {
	
	int marks[];
	
	public Botan (String name, String surname, String patronymic, Sex sex, int age, String faculty){
		super (name, surname, patronymic, sex, age, faculty);
		marks = new int[this.getNumberOfExams()];
        Random rnd = new Random();
        for(int i = 0; i<marks.length; i++)
            marks[i] = 3 + rnd.nextInt(3);
	}
	
	public int getMark(int index){
		super.getMark(index);
		return marks[index];
	}

}
