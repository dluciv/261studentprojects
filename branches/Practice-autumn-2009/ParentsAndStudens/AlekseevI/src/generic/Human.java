/*
 * Alekseev Ilya(c)
 */
package generic;

public class Human implements IHuman {

    private String name;
    private String surName;
    private String middleName;
    private int age;
    private Gender gender;

    public Human(String name, String surName, String middleName, int age, Gender gender) {

        this.name = name;
        this.surName = surName;
        this.middleName = middleName;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surName;
    }

    public String getMiddlename() {
        return middleName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("ФИО: ").append(surName).append(" ").append(name).
                append(" ").append(middleName);
        string.append("\nВозраст ").append(age);
        string.append("\nПол ").append(gender);

        return string.toString();
    }
}
