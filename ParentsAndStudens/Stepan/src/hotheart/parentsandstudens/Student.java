/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

/**
 *
 * @author HotHeart
 */
public class Student extends BasicHuman {

    private static final int EXAMS_COUNT = 10;
    String faculty;

    public Student(String name, String surname, String patronymic, Sex sex, int age, String faculty) {
        super(name, surname, patronymic, sex, age);
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.faculty = faculty;
        this.sex = sex;
        this.age = age;
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

    public String getFaculty()
    {
        return faculty;
    }
}
