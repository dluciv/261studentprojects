//by Skorodumov Kirill gr.: 261
//parentsandstudents task
//class Student

package parentsandstudents;

import java.util.Random;

public class Student extends BasicHuman {
	
	private String faculty;
	private Integer examNum;
	private static final Integer MARK = 3;
	
	public String getFaculty() {
		return faculty;
	}
	
	public Integer getExamNum() {
		return examNum;
	}
	
	public Student(String name, String surname, String patronymic, Sex sex, Integer age, 
			String faculty) {
		super(name,surname,patronymic,sex,age);
		this.faculty = faculty;
		Random random = new Random();
		this.examNum = 1 + random.nextInt(10);
	}
	
	public Integer getMark(Integer i)
	{
		if ((i > examNum) || (i <= 0))
		{
			throw new IllegalArgumentException();
		}
		return MARK;
	}
}
