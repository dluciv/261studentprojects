//by Skorodumov Kirill gr.: 261
//parentsandstudents task

package parentsandstudents;

import java.util.Random;
import java.util.LinkedList;

public class generator {
	private final static Random rnd = new Random();
	private final static String[] maleNames = new String[]{
		"Иван","Александр","Антон","Кирилл","Соломон","Арарат"
	};
	private final static String[] femaleNames = new String[]{
		"Анна", "Анастасия", "Екатерина", "Кристина" 
	};
	private final static String[] surnames = new String[]{
		"Виноградов", "Галузин", "Дыбков", "Макеев"
	};
	private final static String[] faculties = new String[]{
		"Мат-Мех", "Физ-Фак", "Хим-фак", "Фил-фак"
	};
	
	private static String genName(Sex sex)
	{
		if (sex == Sex.FEMALE) {
            return femaleNames[rnd.nextInt(femaleNames.length)];
        } else {
            return maleNames[rnd.nextInt(maleNames.length)];
        }
	}
	
	private static String genSurname(Sex sex)
	{	
		if (sex == Sex.FEMALE) {
            return surnames[rnd.nextInt(surnames.length)];
        } else {
            return maleNames[rnd.nextInt(surnames.length)] + 'а';
        }
	}
	
	private static String genFaculty()
	{
		return faculties[rnd.nextInt(faculties.length)];
	}
	
	private static Sex genSex()
	{
		if (rnd.nextBoolean())
		{
			return Sex.MALE;
		}
		else {
			return Sex.FEMALE;
		}
	}
	
	private static String genPatronymic()
	{
		Sex sex = genSex();
		String name = genName(sex);
		if (sex == Sex.MALE)
		{
			return name + "ович";
		} else
		{
			return name + "овна";
		}
	}
	
	private static Student genStudent(String father)
	{
		Sex sex = genSex();
		String name = genName(sex);
		String surname = genSurname(sex);
		String faculty = genFaculty();
		String patronymic;
		if (sex == Sex.MALE)
		{
			patronymic = father + "ович";
		} else
		{
			patronymic = father + "овна";
		}
		Integer age = rnd.nextInt(100);
		if (rnd.nextBoolean())
		{
			return new Student(name,surname,patronymic,sex,age,faculty);
		} else
		{
			return new Botan(name,surname,patronymic,sex,age,faculty);
		}
	}
	
	private static Parent genParent()
	{	
		Sex sex = genSex();
		String name = genName(sex);
		String surname = genSurname(sex);
		String patronymic = genPatronymic();
		Integer age = rnd.nextInt(100);
		
		Student[] children = new Student[rnd.nextInt(10)];
		for (int i = 0; i < children.length; i++)
		{
			children[i] = genStudent(name);
		}
		
		if (rnd.nextBoolean())
		{
			return new Parent(name,surname,patronymic,sex,age,children);
		} else
		{
			return new CoolParent(name,surname,patronymic,sex,age,children);
		}
	}
	
	public static LinkedList<IHuman> genCollection()
	{
		LinkedList<IHuman> humans = new LinkedList<IHuman>();
		Integer count = rnd.nextInt(50);
		for (int i = 0; i < count; i++)
		{
			Parent nxtParent = genParent();
			humans.add(nxtParent);
			for (int j = 0; j < nxtParent.getChildren().length; j++)
			{
				humans.add(nxtParent.getChildren()[j]);
			}
		}
		
		return humans;
	}
	
	

}
