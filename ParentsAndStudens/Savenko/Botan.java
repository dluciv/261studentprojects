/*
 * Botan is the type of Student, who can get better marks for the same exams
 * Savenko Maria (c)2009
 */

package msavenko.parentsandstudens;

import java.util.Random;

public class Botan extends Student implements IBotan {
    
    private int[] marks;
    
    public Botan(String Name, String Surname, String Patronymic, Sex Sex, int Age, String Faculty) {
        super(Name,Surname,Patronymic,Sex,Age,Faculty);
        marks = new int[getNumberOfExams()];
        Random random = new Random();
        for (int i = 0; i < marks.length; i++)
            marks[i] = 3 + random.nextInt(3);
    }
    
    @Override
    public int getMarkForExam(int index) { return marks[index]; }
}
