/*
 * Parents and Students
 * Parent
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

public class Parent extends Human {

    Student[] kids;

    public Parent(String name, String lastName, String patronymic, Sex sex, int age, Student[] kids) {
        super(name, lastName, patronymic, sex, age);
        this.kids = kids;
    }

    public Student[] getKids() {
        return kids;
    }

    public int getKidsCount() {
        return kids.length;
    }
}
