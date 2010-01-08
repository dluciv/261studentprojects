//by Skorodumov Kirill gr.: 261
//parentsandstudents task
//class Botan

package parentsandstudents;

import java.util.Random;

public class Botan extends Student {
	private Integer[] marks;
	
	public Botan(String name, String surname, String patronymic, Sex sex, Integer age, 
			String faculty) {
		super(name,surname,patronymic,sex,age,faculty);
		Random rnd = new Random();
		marks = new Integer[getExamNum()];
		for (int i = 0; i < getExamNum(); i++) {
			marks[i] = 3 + rnd.nextInt(3);
		}
	}
	
	@Override
	public Integer getMark(Integer i)
	{
		if ((i > getExamNum()) || (i <= 0))
		{
			throw new IllegalArgumentException();
		}
		return marks[i];
	}
}
