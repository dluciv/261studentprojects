/*
 * Class for Father, who makes money on his children
 * Savenko Maria (c)2009
 */

package msavenko.parentsandstudens;

public class CoolFather extends Father implements ICoolFather{
    
    public CoolFather(String Name, String Surname, String Patronymic, Sex Sex, int Age, Student[] Children) {
        super(Name,Surname,Patronymic,Sex,Age,Children);
    }
    
    @Override
    public int getAmountOfMoney() {
        int marksSum = 0;
        int count = 0;
        for (int i = 0; i < getStudentCount(); i++){
            int numberOfExams = getStudent(i).getNumberOfExams();
            for (int j = 0; j < numberOfExams; j++){
                marksSum += students[i].getMarkForExam(j);
                count++;
            }
        }
        
        marksSum /= count;
        
        return marksSum * 10;
    }
}
