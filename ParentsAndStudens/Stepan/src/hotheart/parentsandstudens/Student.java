/*
 * Class of Students extends BasicHuman, contains information about exams.
 * Korshakov Stepan - 261 Group - (c) 2009
 */
package hotheart.parentsandstudens;

/**
 * @author Korshakov Stepan
 */
public class Student extends BasicHuman {

    private static final int EXAMS_COUNT = 10;
    String faculty;

    public Student(String name, String surname, String patronymic, Sex sex, int age, String faculty) {
        super(name, surname, patronymic, sex, age);
        this.faculty = faculty;
    }

    public int getExamsCount() {
        return EXAMS_COUNT;
    }

    public int getExamMark(int index) {
        if ((index < 0) || (index >= getExamsCount())) {
            throw new IllegalArgumentException();
        }

        return 3;
    }

    public String getFaculty() {
        return faculty;
    }
}
