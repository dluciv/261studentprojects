package fathers_sons;

public class Student extends Human {
	
	String faculty;
	private static final int NUMBER_OF_EXAMS = 5;
	
	public Student(String name, String surname, String patronymic, Sex sex, int age, String faculty){
		super (name, surname, patronymic, sex, age);
		this.faculty = faculty;
	}
	
	public int getNumberOfExams(){
		return NUMBER_OF_EXAMS;
	}
	
	public int getMark(int index){
		if ((index < 0) || (index >= NUMBER_OF_EXAMS)){
			throw new IllegalArgumentException();
		}
		return 3;
	}

}
