/*
 * "Fathers and Children"
 * some generics example
 * (c) Yaskov Sergey, 261; 2009
 *
 * this class extends class "SimplyHuman", because any our Parent knows
 * his children;
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
