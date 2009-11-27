/*
 * ordinary Student, gets only '3' for his exams
 * Savenko Maria (c)2009
 */

package msavenko.parentsandstudens;

import java.util.Random;

public class Student extends Human {
    
    private Random random = new Random();
    private int    exams_num = random.nextInt(10);
    private String faculty;
    
    public Student(String Name, String Surname, String Patronymic, Sex Sex,int Age, String Faculty) {
        name = Name;
        surname = Surname;
        patronymic = Patronymic;
        sex = Sex;
        age = Age;
        faculty = Faculty;
    }    
    
    public int getNumberOfExams() { return exams_num; }    

    public int getMarkForExam(int index) {
        if ((index < 0) || (index >= exams_num)){
            throw new IllegalArgumentException();
        }
        
        return 3;
    }
    
    public String getFaculty() { return faculty; }
}
