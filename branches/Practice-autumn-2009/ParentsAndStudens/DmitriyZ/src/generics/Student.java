/*
 * Parents and Students
 * Student
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

import java.util.Arrays;

public class Student extends Human {

    static final int EXAMS = 5;
    private int[] marks = new int[EXAMS];
    String faculty;

    public Student(String name, String lastName, String patronymic, Sex sex, int age, String faculty) {
        super(name, lastName, patronymic, sex, age);
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.faculty = faculty;
        this.sex = sex;
        this.age = age;
        Arrays.fill(marks, 3);
    }

    public int[] getMarks() {
        return marks;
    }

    public String getFaculty() {
        return faculty;
    }

    public double averageGrade() {
        double grade = 0;
        for (int mark : marks) {
            grade += mark;
        }
        return grade / EXAMS;
    }

    public String studentToString() {
        return name + " " + patronymic + " " + lastName + "\nФакультет: " + faculty + "\nОценки: " + Arrays.toString(marks);
    }
}
