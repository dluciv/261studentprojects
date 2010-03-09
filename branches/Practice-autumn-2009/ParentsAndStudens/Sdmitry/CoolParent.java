/*
 * Generic
 * soldatov dmitry ©, 2009
 */
package generic;

public class CoolParent extends Parent {

    private float averageMarksSum = 0;
    private float moneyArgument = 10;


    public CoolParent(String name, String lastName, String fatherName, Gender gender,
            int age, Student[] kids) {
        super(name, lastName, fatherName, gender, age, kids);
    }

    public double countMoney(Student[] kids) {

        for (int i = 0; i < kids.length; i++) {
            averageMarksSum += kids[i].getAverageMark();
        }
        return moneyArgument * averageMarksSum;
    }
}
