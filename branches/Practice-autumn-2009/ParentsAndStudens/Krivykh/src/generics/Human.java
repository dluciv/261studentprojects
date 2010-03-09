//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

public class Human implements IHuman {

    private String firstName;
    private String secondName;
    private String patronymic;
    private EnumSex sex;
    private int age;

    public Human(String firstName, String secondName, String patronymic,
            EnumSex sex, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.sex = sex;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public EnumSex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nФИО: ").append(firstName).append(" ").append(secondName).append(" ").append(patronymic);
        str.append("\nSex: ").append(sex);
        str.append("\nAge: ").append(age);
        return str.toString();
    }
}
