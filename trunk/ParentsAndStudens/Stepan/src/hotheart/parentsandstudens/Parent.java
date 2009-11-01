/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

/**
 *
 * @author HotHeart
 */
public class Parent extends BasicHuman {

    Student[] students;

    public Parent(String name, String surname, String patronymic, Sex sex, int age, Student[] students) {
        super(name, surname, patronymic, sex, age);
        this.students = students;
    }

    public int getStudentCount()
    {
        return students.length;
    }

    public Student getStrudent(int index)
    {
        return students[index];
    }
}
