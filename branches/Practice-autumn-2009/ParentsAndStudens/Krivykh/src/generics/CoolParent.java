//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

import java.util.List;

public class CoolParent extends Parent {

    private float money = 0;
    private static final int MONEY_COEFFICIENT = 10;

    public CoolParent(String firstName, String secondName, String patronymic,
            EnumSex sex, int age, List<Student> children) {
        super(firstName, secondName, patronymic, sex, age, children);
        for (Student child : children) {
            money += MONEY_COEFFICIENT * child.meanMark();
        }
    }

    public float getMoney(){
        return money;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(super.toString());
        str.append("\nMoney: ").append(money);
        return str.toString();
    }
}
