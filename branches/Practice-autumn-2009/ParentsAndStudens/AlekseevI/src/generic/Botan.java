/*
 * Alekseev Ilya(c)
 */
package generic;

import java.util.Random;
import java.util.Arrays;


public class Botan extends Student {

    private static final int DOWNBORDER = 3;
    private static final int UPBORDER = 5;
    private static final int MAXMARKCOUNT = 10;
    private int[] marks = new int[MAXMARKCOUNT];
    Random random = new Random();

    public Botan(String name, String surName, String middleName, int age,
            Gender gender, String faculty) {
        super(name, surName, middleName, age, gender, faculty);

        for (int i = 0; i < MAXMARKCOUNT; i++) {
            marks[i] = random.nextInt(UPBORDER - DOWNBORDER + 1) + DOWNBORDER;
        }
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
        string.append("\nЯвляется : Ботаном");
        string.append("\nОценки ").append(Arrays.toString(marks));

        return string.toString();
    }
}
