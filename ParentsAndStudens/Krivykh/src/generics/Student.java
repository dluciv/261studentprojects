//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

import java.util.Arrays;

public class Student extends Human {

    private static final int QUANTITY_OF_MARKS = 4;
    private static final int MARK = 3;
    private String faculty;
    protected  int[] marks = new int[QUANTITY_OF_MARKS];

    public Student(String firstName, String secondName,
            String patronymic, EnumSex sex, int age, String faculty) {
        super(firstName, secondName, patronymic, sex, age);
        this.faculty = faculty;
        Arrays.fill(marks, MARK);
    }

    public float meanMark() {
        float res = 0;
        for (int i = 0; i < QUANTITY_OF_MARKS; i++) {
            res += marks[i];
        }
        return res / QUANTITY_OF_MARKS;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(super.toString());
        str.append("\nFaculty: ").append(faculty);
        str.append("\nMarks: ");
        for (int i = 0; i < QUANTITY_OF_MARKS; i++){
            str.append(marks[i]).append(" ");
        }
        str.append("\nMeanMark: ").append(meanMark());
        return str.toString();
    }
}
