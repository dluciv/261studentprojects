//(c)Antonov Kirill 2009
//Here we are create random marks from 3 to 5 and collected AverageGrades

package parentsstudents;

import java.util.Random;

public class Botan extends Student {
    private static final int MINIMAL_MARKS = 3;
    private static final int NUMBER_FROM_0_To_2 = 3;

    private final Random random = new Random();
    public double averageGrades;

    public Botan(String surname, String name, String FatherName, Sex sex,
                 int age, String faculty, int exams_counter) {
        super(surname, name, FatherName, sex, age, faculty, exams_counter);
        this.faculty = faculty;
        this.exams_counter = exams_counter;

        marks = new int[exams_counter];


        for (int i = 0; i < exams_counter; i++) {
            marks[i] = MINIMAL_MARKS + random.nextInt(NUMBER_FROM_0_To_2);
        }

        averageGrades = AverageGrades(marks);
    }

    private double AverageGrades(int[] marks) {
        double sum = 0;

        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }

        return sum / marks.length;
    }
}
