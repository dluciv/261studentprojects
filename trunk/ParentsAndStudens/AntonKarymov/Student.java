/*Anton Karymov,2009,261gr.
  Student implements interface for all people
  Student has a faculty and mark(all mark - 3)
  Student can count own averege marks and print own information on the screen
 */
package generic;

public class Student implements IHuman {
    private String name;
    private String fathername;
    private String surname;
    private String faculty;
    private Sex sex;
    private int constantStudentMark = 3;
    private int age;
    private int minimalExamsNumber = 1;
    protected int examsNumber = minimalExamsNumber + MyRandom.getRandom().nextInt(5);
    protected int[] marks = new int[examsNumber];

//Student's class constructor(student's name,student's patronymic,student's sex,student's age,student's faculty)
    public Student(String name, String patronymic, String surname, Sex sex, int age, String faculty) {
        if (name == null || patronymic== null || surname == null || sex == null || age < 0 || faculty == null) {
            throw new java.lang.IllegalArgumentException();
        }
        this.name = name;
        this.fathername = patronymic;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.faculty = faculty;

        for (int i = 0; i < marks.length; i++) {
            marks[i] = constantStudentMark;
        }
    }

    public  String getName() {
        return name;
    }

    public String getPatronymic() {
        return fathername;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getFaculty() {
        return faculty;
    }
    
    public int[] getMarks() {
        return marks;
    }

//count student's averege mark
    public double getAverageMark() {
        double sum = 0.0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum / marks.length;
    }

//print student's information on the screen
    public static void printStudent(Student student) {
        System.out.printf("%s %s %s", student.getName(), student.getPatronymic(), student.getSurname());
        System.out.println();
        System.out.printf(
                "%s %s%s %s%s %s", "пол:",
                student.getSex(),
                ", возраст:",
                student.getAge(),
                ", факультет:",
                student.getFaculty());
        System.out.println();
    }
}
