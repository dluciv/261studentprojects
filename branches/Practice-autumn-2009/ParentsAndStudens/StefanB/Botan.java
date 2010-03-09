/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

import java.util.Random;

public class Botan extends Student {
	
	private int marks[];
	
	public Botan (String name, String surname, String patronymic, Sex sex, int age, String faculty){
		super (name, surname, patronymic, sex, age, faculty);
		marks = new int[this.getNumberOfExams()];
        Random rnd = new Random();
        for(int i = 0; i<marks.length; i++)
            marks[i] = 3 + rnd.nextInt(3);
	}
	
	@Override
	public int getMark(int index){
		//super.getMark(index);
		return marks[index];
	}
	
	private int getAverageMark(){
		int sum = 0;
		for (int index = 0; index < NUMBER_OF_EXAMS; index++){
			sum += this.getMark(index);
		}
		return (sum / NUMBER_OF_EXAMS);
	}
	
	@Override
	public void printIdentity(){
		System.out.println(surname + " " + name + " " + patronymic + " (" + this.getAverageMark() + ")");
	}

}
