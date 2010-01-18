/*
 * "Fathers and Children"
 * some generics example
 * (c) Yaskov Sergey, 261; 2009
 *
 * this is a base people class;
 * another classes of people extend this base class;
 */

package fathersandchildren;

public class SimplyHuman implements IHuman {
    private String surname;
    private String name;
    private String patronymic;
    private Sex sex;
    private int age;

    public SimplyHuman(String surname, String name, String patronymic, Sex sex,
            int age) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
}
