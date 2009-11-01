/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

/**
 *
 * @author HotHeart
 */
public class CoolParent extends Parent {

    public CoolParent(String name, String surname, String patronymic, Sex sex, int age, Student[] students) {
        super(name, surname, patronymic, sex, age, students);
    }

    public int getMoney() {

        int sum = 0;
        int count = 0;
        for (int i = 0; i < getStudentCount(); i++) {
            int marksCount = getStrudent(i).getExamsCount();
            for (int j = 0; j < marksCount; j++) {
                count++;
                sum += students[i].getExamMark(j);
            }
        }

        sum /= count;

        return sum * 10;
    }
}
