/*
 * Parents and Students
 * Generator
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final Random random = new Random();
    private static final String[] MALE_FIRSTNAMES = new String[]{
        "Александр", "Владимир", "Петр", "Степан"
    };
    private static final String[] FEMALE_FIRSTNAMES = new String[]{
        "Александра", "София", "Анна", "Юля", "Алина", "Марина", "Анастасия", "Катерина", "Аня"
    };
    private static final String[] LASTNAMES = new String[]{
        "Иванов", "Семенов", "Ильин", "Петров", "Турбин", "Орехов", "Булкин"
    };
    private static final String[] FACULTIES = new String[]{
        "Математико-Механический", "Юридический", "Психологии", "Социологии", "Физический"
    };

    private static Sex generateSex() {
        if (random.nextBoolean()) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }
    }

    public static String generateName(Sex sex) {
        if (sex == Sex.MALE) {
            return MALE_FIRSTNAMES[random.nextInt(MALE_FIRSTNAMES.length)];
        } else {
            return FEMALE_FIRSTNAMES[random.nextInt(FEMALE_FIRSTNAMES.length)];
        }
    }

    public static String generateLastName(Sex sex) {
        if (sex == Sex.FEMALE) {
            return LASTNAMES[random.nextInt(LASTNAMES.length)] + "а";
        } else {
            return LASTNAMES[random.nextInt(LASTNAMES.length)];
        }
    }

    public static String generatePatronymic(String lastName, Sex sex) {
        if (sex == Sex.FEMALE) {
            return lastName + "овна";
        } else {
            return lastName + "ович";
        }
    }

    public static String generateFaculty() {
        return FACULTIES[random.nextInt(FACULTIES.length)];
    }

    public static Student generateStudent(String fatherName, String lastName) {

        Sex sex = generateSex();
        String name = generateName(sex);
        String patronymic = generatePatronymic(fatherName, sex);
        String faculty = generateFaculty();

        int age = 15 + random.nextInt(9);

        if (random.nextBoolean()) {
            return new Student(name, lastName, patronymic, sex, age, faculty);
        } else {
            return new Botan(name, lastName, patronymic, sex, age, faculty);
        }
    }

    public static Parent generateParent() {

        Sex sex = generateSex();
        String name = generateName(sex);
        String lastName = generateLastName(sex);
        String patronymic = generatePatronymic(generateName(Sex.MALE), sex);
        int age = 30 + random.nextInt(20);

        int kidsCount = 2 + random.nextInt(10);
        Student[] students = new Student[kidsCount];
        for (int i = 0; i < kidsCount; i++) {
            students[i] = generateStudent(name, lastName);
        }

        if (random.nextBoolean()) {
            return new Parent(name, lastName, patronymic, sex, age, students);
        } else {
            return new CoolParent(name, lastName, patronymic, sex, age, students);
        }
    }

    public static List<IHuman> generatePeople() {
        LinkedList<IHuman> people = new LinkedList<IHuman>();
        int parentCount = random.nextInt(20);

        for (int i = 1; i <= parentCount; i++) {
            Parent parent = generateParent();
            people.add(parent);

            Student[] kids = parent.getKids();
            for (int j = 0; j < parent.getKidsCount(); j++) {
                people.add(kids[j]);
            }
        }
        return people;
    }
}