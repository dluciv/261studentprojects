/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fathersandchildren;

public class CoolParent extends Parent {
    public double moneyQnt;
    private final int MONEY_COEFFICIENT = 10;

    public CoolParent(String surname, String name, String patronymic, Sex sex,
            int age, Student[] students) {
        super(surname, name, patronymic, sex, age, students);
        moneyQnt = calcMoney(students);
    }

    private double calcMoney(Student[] students) {
        double result = 0;
        double forOne;

        if (students.length == 0) {
            return 0;
        }

        for (int i = 0; i < students.length; i++) {
            forOne = 0;
            for (int j = 0; j < students[i].marksList.length; j++)
              forOne += students[i].marksList[j];
            result += (forOne / students[i].marksList.length);
        }

        return result / students.length * MONEY_COEFFICIENT;
    }
}
