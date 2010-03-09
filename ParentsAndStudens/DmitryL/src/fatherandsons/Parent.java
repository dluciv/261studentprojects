//Lebedev Dmitry g261 2009 (c)
package fatherandsons;

import java.util.LinkedList;

public class Parent implements IHuman {

    private LinkedList<Student> kids;
    private String name;
    private String surname;
    private String patronymic;
    private Sex sex;
    private int age;

    Parent() {
        this.setName(Generator.maleNames[((int) (5 * Math.random()))]);
        this.setPatronymic(Generator.maleNames[((int) (5 * Math.random()))] + "ович");
        this.setSurname(Generator.surnames[((int) (5 * Math.random()))]);
        this.setAge(Generator.ageCategory2[((int) (5 * Math.random()))]);
        this.setSex(Sex.male);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setKids(LinkedList<Student> kids) {
        this.kids = kids;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public LinkedList<Student> getKids() {
        return kids;
    }
//Printing parameters of parent, including parmetrs of all kids
    public void printAll() {
        System.out.printf("%s %s %s %s %s %s", "Отец", this.name, this.patronymic,
                this.surname, this.sex, this.age);
        System.out.println();
        if (kids == null) {
        } else {
            for (Student kid : kids) {
                kid.printAll();
            }
        }
    }
}

