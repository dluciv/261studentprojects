/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fathersandchildren;

public class Parent extends SimplyHuman {
    public Student[] students;

    Parent(String surname, String name, String patronymic, Sex sex,
            int age, Student[] students) {
        super (surname, name, patronymic, sex, age);
        this.students = students;
    }
}
