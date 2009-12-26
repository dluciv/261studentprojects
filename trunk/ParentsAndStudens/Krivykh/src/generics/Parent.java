//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

import java.util.*;

public class Parent extends Human {

    private List<Student> children;

    public Parent(String firstName, String secondName, String patronymic,
            EnumSex sex, int age, List<Student> children) {
        super(firstName, secondName, patronymic, sex, age);
        this.children = children;
    }

    public List<Student> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(super.toString());
        str.append("\nChildren: ").append(children);
        return str.toString();
    }
}
