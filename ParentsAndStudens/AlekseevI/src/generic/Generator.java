/*
 * Alekseev Ilya(c)
 */
package generic;

import java.util.LinkedList;
import java.util.Random;
import java.util.List;

public class Generator {

    private static final int MINIMAL_AGE_STUDENT = 17;
    private static final int MAXIMAL_AGE_STUDENT = 25;
    private static final int MINIMAL_AGE_PARENT = 26;
    private static final int MAXIMAL_AGE_PARENT = 90;
    private static final int QANTITY_PARENT = 15;
    private static final String SUFFIKS_MIDDLENAME_MALE = "ович";
    private static final String SUFFIKS_MIDDLENAME_FEMALE = "овна";
    private static final String[] LIST_NAME_MALE = {"Максим", "Александр",
        "Фарит", "Азат", "Петр", "Иван"};
    private static final String[] LIST_NAME_FEMALE = {"Ольга", "Рита", "Надежда",
        "Анна", "Наталья", "Татьяна", "Мария"};
    private static final String[] LIST_SURNAME = {"Алексеев", "Путин", "Медведев",
        "Ленин", "Сталин"};
    private static final String[] FACULTY = {"Физический", "Математико-механический",
        "Биологический", "Медицинский"};

    public static List<IHuman> generatePeople() {
        List<IHuman> people = new LinkedList<IHuman>();
        Random random = new Random();
        Parent[] parents = generateParents(random);

        for (Parent parent : parents) {
            people.add(parent);

            for (Student student : parent.getChildren()) {
                people.add(student);
            }
        }
        return people;
    }

    private static Parent[] generateParents(Random random) {
        Parent[] parents = new Parent[QANTITY_PARENT];

        for (int i = 0; i < QANTITY_PARENT; i++) {

            Gender gender = Gender.MALE;
            int age = generatorParentAge(random);
            String name = generatorName(random, gender);
            String surName = generatorSurName(random, gender);
            String middleName = generatorMiddleName(random, gender);

            Student[] children = generateChildren(random, name, surName);
            if (random.nextBoolean()) {
                parents[i] = new Parent(name, surName, middleName, age, gender, children);
            } else {
                parents[i] = new CoolParent(name, surName, middleName, age, gender, children);
            }
        }
        return parents;
    }

    private static Student[] generateChildren(Random random, String name, String surName) {
        int countChildren = random.nextInt(Parent.CHILDRENCOUNT);
        Student[] children = new Student[countChildren];

        for (int i = 0; i < countChildren; i++) {
            Gender gender = generatorGender(random);
            int age = generatorStudentAge(random);
            String nameChildren = generatorName(random, gender);
            String surNameChildren = surName;
            if (gender == Gender.FEMALE) {
                surNameChildren += "a";

            }
            String middleName = name;
            if (gender == Gender.MALE) {
                middleName = name + SUFFIKS_MIDDLENAME_MALE;

            } else {
                middleName = name + SUFFIKS_MIDDLENAME_FEMALE;
            }
            String faculty = generatorFaculty(random);
            if (random.nextBoolean()) {
                children[i] = new Student(nameChildren, surNameChildren, middleName, age, gender, faculty);
            } else {
                children[i] = new Botan(nameChildren, surNameChildren, middleName, age, gender, faculty);
            }
        }

        return children;
    }

    private static Gender generatorGender(Random random) {
        if (random.nextBoolean()) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }

    }

    private static int generatorStudentAge(Random random) {
        return random.nextInt(MAXIMAL_AGE_STUDENT - MINIMAL_AGE_STUDENT) + MINIMAL_AGE_STUDENT;
    }

    private static int generatorParentAge(Random random) {
        return random.nextInt(MAXIMAL_AGE_PARENT - MINIMAL_AGE_PARENT) + MINIMAL_AGE_PARENT;
    }

    private static String generatorName(Random random, Gender gender) {
        if (gender == Gender.MALE) {
            return LIST_NAME_MALE[random.nextInt(LIST_NAME_MALE.length)];
        } else {
            return LIST_NAME_FEMALE[random.nextInt(LIST_NAME_FEMALE.length)];
        }
    }

    private static String generatorSurName(Random random, Gender gender) {
        if (gender == Gender.MALE) {
            return LIST_SURNAME[random.nextInt(LIST_SURNAME.length)];
        } else {
            return LIST_SURNAME[random.nextInt(LIST_SURNAME.length)] + "a";
        }
    }

    private static String generatorMiddleName(Random random, Gender gender) {
        if (gender == Gender.MALE) {
            return LIST_NAME_MALE[random.nextInt(LIST_NAME_MALE.length)] + SUFFIKS_MIDDLENAME_MALE;
        } else {
            return LIST_NAME_MALE[random.nextInt(LIST_NAME_MALE.length)] + SUFFIKS_MIDDLENAME_FEMALE;
        }
    }

    private static String generatorFaculty(Random random) {
        return FACULTY[random.nextInt(FACULTY.length)];
    }
}
