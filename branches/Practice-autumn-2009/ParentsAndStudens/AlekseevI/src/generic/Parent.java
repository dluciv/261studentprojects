/*
 * Alekseev Ilya(c)
 */
package generic;

public class Parent extends Human {

    public static final int CHILDRENCOUNT = 3;
    private Student[] children = new Student[CHILDRENCOUNT];

    public Parent(String name, String surName, String middleName, int age,
            Gender gender, Student[] children) {
        super(name, surName, middleName, age, gender);
        this.children = children;
    }

    public Student[] getChildren() {
        return children;
    }

    public String toString() {
        StringBuilder string = new StringBuilder(super.toString());
        string.append("\nЯвляется : Отцом");
        string.append("\nКолличество детей  ").append(getChildren().length);


        return string.toString();
    }
}
