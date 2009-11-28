/*
 * Class of parents, contains information about there childs.
 * Korshakov Stepan - 261 Group - (c) 2009
 */
package hotheart.parentsandstudens;

/**
  * @author Korshakov Stepan
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
