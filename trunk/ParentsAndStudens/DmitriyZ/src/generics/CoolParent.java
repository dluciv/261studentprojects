/*
 * Parents and Students
 * Cool Parent
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

public class CoolParent extends Parent {

    private double money = 0.0;

    public CoolParent(String name, String lastName, String patronymic, Sex sex, int age, Student[] kids) {
        super(name, lastName, patronymic, sex, age, kids);

        for (Student kid : kids) {

            money += 10 * kid.averageGrade();
        }
    }

    public int getMoney() {
        return (int) money;
    }
}
