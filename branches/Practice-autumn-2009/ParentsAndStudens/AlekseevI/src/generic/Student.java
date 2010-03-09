/*
 * Alekseev Ilya(c)
 */
package generic;

public class Student extends Human {

    private static final int MARK = 3;
    private static final int MAXMARKCOUNT = 10;
    private String faculty;
    private int[] marks = new int[MAXMARKCOUNT];

    public Student(String name, String surName, String middleName, int age,
            Gender gender, String faculty) {
        super(name, surName, middleName, age, gender);
        this.faculty = faculty;
        for (int i = 0; i < MAXMARKCOUNT; ++i) {

            marks[i] = MARK;
        }

    }

    public String getFaclty() {
        return faculty;
    }

    public double getMeanMark() {

        double sumMark = 0;
        for (int mark : marks) {
            sumMark += mark;

        }
        return sumMark / MAXMARKCOUNT;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(super.toString());
        string.append("\nЯвляется : студентом ");
        string.append("\nФакультет ").append(faculty);
        string.append("\nСредняя оценка ").append(getMeanMark());

        return string.toString();
    }
}
