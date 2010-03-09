/*
 * Alekseev Ilya(c)
 */
package generic;

public class CoolParent extends Parent {

    private static final int MONEYKOFF = 10;
    private int money = 0;

    public CoolParent(String name, String surName, String middleName, int age,
            Gender gender, Student[] children) {
        super(name, surName, middleName, age, gender, children);

        for (Student child : children) {
            money += MONEYKOFF * child.getMeanMark();
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(super.toString());
        string.append("\n Является : Крутым родителем");
        string.append("\nКолличество денег ").append(money);
        return string.toString();
    }
}
