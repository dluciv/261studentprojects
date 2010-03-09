/*
 * Parents and Students
 * Human
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

public class Human implements IHuman {

    String name, lastName, patronymic;
    Sex sex;
    int age;

    public Human(String name, String lastName, String patronymic, Sex sex, int age) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String humanToString() {
        return name + " " + patronymic + " " + lastName + "\nПол: " + sex + "\nВозраст: " + age;
    }
}
