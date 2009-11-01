/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import java.util.Random;

/**
 *
 * @author HotHeart
 */
public class Generator {

    private static final Random rnd = new Random();
    private static final String[] namesMale = new String[]{
        "Александр", "Степан", "Дситрий", "Илья", "Сергей", "Михаил"
    };
    private static final String[] namesFemale = new String[]{
        "Александра", "Алина", "Марина", "Анастасия", "Катерина", "Аня"
    };
    private static final String[] surnamesMale = new String[]{
        "Коршаков", "Табуреткин", "Семенихин", "Ланцелотов", "Турбофишкин", "Орехов", "Белочкин"
    };
    private static final String[] faculties = new String[]{
        "Математико-Механический", "Задолбайства и сельского хозяйства", "Юридический"
    };

    public static String generateName(Sex sex) {
        if (sex == Sex.FEMALE) {
            return namesFemale[rnd.nextInt(namesFemale.length)];
        } else {
            return namesMale[rnd.nextInt(namesMale.length)];
        }
    }

    public static String generateSurname(Sex sex) {
        if (sex == Sex.FEMALE) {
            return surnamesMale[rnd.nextInt(surnamesMale.length)] + "а";
        } else {
            return surnamesMale[rnd.nextInt(surnamesMale.length)];
        }
    }

    public static String generatePatronymic(String fatherName, Sex sex) {
        if (sex == Sex.FEMALE) {
            return fatherName + "овна";
        } else {
            return fatherName + "ович";
        }
    }

    public static String generateFaculty() {
        return faculties[rnd.nextInt(faculties.length)];
    }

    public static Student generateStudent(String fatherName, String surname) {
        Sex sex = Sex.FEMALE;
        if (rnd.nextBoolean()) {
            sex = Sex.MALE;
        }

        String name = generateName(sex);
        String patronymic = generatePatronymic(fatherName, sex);
        String faculty = generateFaculty();

        int age = 17 + rnd.nextInt(10);

        if (rnd.nextBoolean()) {
            return new Student(name, surname, patronymic, sex, age, faculty);
        } else {
            return new Botan(name, surname, patronymic, sex, age, faculty);
        }
    }

    public static Parent generateParent() {
        Sex sex = Sex.FEMALE;
        if (rnd.nextBoolean()) {
            sex = Sex.MALE;
        }

        String name = generateName(sex);
        String surname = generateSurname(sex);
        String patronymic = generatePatronymic(generateName(Sex.MALE), sex);
        int age = 30 + rnd.nextInt(10);

        int studentsCount = rnd.nextInt(10);
        Student[] students = new Student[studentsCount];
        for (int i = 0; i < studentsCount; i++) {
            students[i] = generateStudent(name, surname);
        }

        if (rnd.nextBoolean()) {
            return new Parent(name, surname, patronymic, sex, age, students);
        } else {
            return new CoolParent(name, surname, patronymic, sex, age, students);
        }
    }
}
