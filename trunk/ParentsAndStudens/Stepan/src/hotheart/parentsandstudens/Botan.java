/*
 * Class of clever students.
 * Korshakov Stepan - 261 Group - (c) 2009
 */
package hotheart.parentsandstudens;

import java.util.Random;

/**
 * Class of clever students.
 * @author Korshakov Stepan
 */
public class Botan extends Student {

    int[] marks;
    public Botan(String name, String surname, String patronymic, Sex sex, int age, String faculty)
    {
        super(name, surname, patronymic, sex, age, faculty);
        marks = new int[this.getExamsCount()];
        Random rnd = new Random();
        for(int i = 0; i<marks.length; i++)
            marks[i] = 3 + rnd.nextInt(3);
    }

    @Override
    public int getExamMark(int index) {
        return marks[index];
    }
}
