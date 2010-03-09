/*
 * Parents and Students
 * Botan
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

import java.util.Random;

public class Botan extends Student {

    private int[] marks;

    public Botan(String name, String lastName, String patronymic, Sex sex, int age, String faculty) {
        super(name, lastName, patronymic, sex, age, faculty);
        marks = new int[EXAMS];
        Random random = new Random();
        for (int i = 0; i < EXAMS; i++) {
            marks[i] = 3 + random.nextInt(3);
        }
    }

    @Override
    public double averageGrade() {
        double grade = 0;
        for (int mark : marks) {
            grade += mark;
        }
        return grade / EXAMS;
    }
}