/*
 * Generic
 * soldatov dmitry ©, 2009
 */
package generic;

public class Student implements IHuman {

    private int examsNumber;
    private String name;
    private String lastName;
    private String fatherName;
    private String faculty;
    private Gender gender;
    private int age;
    private int[] marks;

    //Абсолютно все поля генерируются рандомом в PropertiesGenerator, кроме
    //отчества и фамилии - онb передаются от отца, конечно же
    @SuppressWarnings("empty-statement")
    public Student(String name, String lastName, String fatherName, Gender gender,
            int age, String faculty, int examsNumber, int[] marks) {
        this.name = name;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.age = age;
        this.faculty = faculty;
        this.examsNumber = examsNumber;
        this.marks = marks;

        try {
            if (this.marks == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("No marks");
        }
        ;
    }

    //Здесь же реализованы только геттеры и подсчет средней оценки
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getFaculty() {
        return faculty;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int[] getMarks() {
        return marks;
    }

    public float getAverageMark() {
        int marksSum = 0;
        for (int i = 0; i < marks.length; i++) {
            marksSum += marks[i];
        }
        return ((float) marksSum / marks.length);
    }
}
