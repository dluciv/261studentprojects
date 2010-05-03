/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fathersandchildren;

import java.util.Random;

public class Botan extends Student {
    private final Random rnd = new Random();
    public double averageGrades;

    public Botan(String surname, String name, String patronymic, Sex sex,
            int age, String faculty, int examsQnt) {
        super(surname, name, patronymic, sex, age, faculty, examsQnt);

        for (int i = 0; i < examsQnt; i++) {
            marksList[i] = 3 + rnd.nextInt(3);
        }

        averageGrades = calcAverageGrades(marksList);
    }

    private double calcAverageGrades(int[] marksList) {
        double sum = 0;

        for (int i = 0; i < marksList.length; i++) {
            sum += marksList[i];
        }

        return sum / marksList.length;
    }
}
