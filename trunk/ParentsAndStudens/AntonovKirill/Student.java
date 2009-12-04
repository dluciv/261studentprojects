//(c) Antonov Kirill 2009
//class Student contains student's marks but they are all 3 & and contains faculty

package parentsstudents;

public class Student implements IHuman {
    private String surname;
    private String name;
    private String FatherName;
    private Sex sex;
    private int age;
    public String faculty;
    public int[]marks;
    //private static final int examsCounter = 5;
    public int exams_counter;
    private static final int ExamsMark = 3;

    public Student(String surname, String name, String FatherName, Sex sex, int age,
            String faculty, int exams_counter){
        this.name = name;
        this.FatherName = FatherName;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.faculty = faculty;
        this.exams_counter = exams_counter;
        marks = new int[exams_counter];
        for (int i = 0; i < exams_counter; i++) { //array 3 ratings
            marks[i] = ExamsMark;
        }
    }
      public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFatherName() {
        return FatherName;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

}

  