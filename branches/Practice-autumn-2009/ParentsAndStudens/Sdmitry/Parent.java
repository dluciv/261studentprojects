/*
 * Generic
 * soldatov dmitry ©, 2009
 */

package generic;

public class Parent implements IHuman {

    private String name;
    private String lastName;
    private String fatherName;
    private Gender gender;
    private int age;
    private Student[] kids;
    //Абсолютно все поля генерируются в PropertiesGenerator, кроме пола, т.к.
    //родители у нас только мужского рода

    @SuppressWarnings("empty-statement")
    public Parent(String name, String lastName, String fatherName, Gender gender,
            int age, Student[] kids) {
        this.name = name;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.age = age;
        this.kids = kids;

        try {
            if (kids == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("No kids");
        }
        ;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getKidsNumber() {
        return kids.length;
    }

    public Student[] getKids() {
        return kids;
    }
}
