/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

/**
 *
 * @author HotHeart
 */
public class BasicHuman implements IHuman {

    String name, surname, patronymic;
    Sex sex;
    int age;

    public BasicHuman(String name, String surname, String patronymic, Sex sex, int age) {
        this.name = name;
        this.surname = surname;
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

    @Override
    public String toString() {
        return name + " " + patronymic + " " + surname + " (" + age + ")";
    }
}
