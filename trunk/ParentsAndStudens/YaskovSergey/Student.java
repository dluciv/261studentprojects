/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fathersandchildren;

public class Student extends SimplyHuman {
    public String faculty;
    private int examsQnt;
    public int[] marksList;

    public Student(String surname, String name, String patronymic, Sex sex,
            int age, String faculty, int examsQnt) {
        super(surname, name, patronymic, sex, age);
        this.faculty = faculty;
        this.examsQnt = examsQnt;
        marksList = new int[examsQnt];

        for (int i = 0; i < examsQnt; i++) {
            marksList[i] = 3;
        }
    }
}
