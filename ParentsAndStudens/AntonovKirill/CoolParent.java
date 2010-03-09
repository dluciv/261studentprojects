/* (c)Antomov Kirill 2009
 *
*/

package parentsstudents;

public class CoolParent extends Parents {
    public double money;
    private static final int MONEY_COEFFICIENT = 10;

    public CoolParent(String surname, String name, String FatherName, Sex sex,
            int age, Student[] students) {
        super(surname, name, FatherName, sex, age, students);
        money = calcMoney(students);
    }

    private double calcMoney(Student[] students) {
        double result = 0;
        double sum;

        if (students.length == 0) {
            return 0;
        }

        for (int i = 0; i < students.length; i++) {
            sum = 0;
            for (int j = 0; j < students[i].marks.length; j++)
              sum += students[i].marks[j];
            result += (sum / students[i].marks.length);
        }

        return result / students.length * MONEY_COEFFICIENT;
    }
}


