/*
 * "Fathers and Children"
 * some generics example
 * (c) Yaskov Sergey, 261; 2009
 *
 * this class generates several families and puts it to collection.
 * each family consists of father and his children-students;
 */

package fathersandchildren;

import java.util.Random;
import java.util.LinkedList;
import java.util.List;

public class Creator {
    private static final int THE_YOUNGEST_FATHER_AGE = 25;
    private static final int FATHER_AGE_RANGE = 5;
    private static final int THE_YOUNGEST_STUDENT_AGE = 15;
    private static final int STUDENT_AGE_RANGE = 5;
    private static final int MAX_FATHERS = 9;
    private static final int MAX_CHILDREN_PER_FATHER = 5;
    private static final int EXAMS_QNT = 4;
    private static final String[] maleNames = new String[] {
        "Емельян", "Кондрат", "Захар", "Иван", "Тарас", "Эдмунд", "Петр"
    };
    private static final String[] femaleNames = new String[] {
        "Галя", "Клава", "Зина", "Анна", "Дуня", "Аквамарина", "Даздраперма"
    };
    private static final String[] surnames = new String[] {
        "Пупкин", "Шальной", "Злых", "Самосвалов", "Крапива", "Бровко", "Зноев"
    };
    private static final String[] faculties = new String[] {
        "Садоводства", "Взрывного дела", "ФТК"
    };
    private static Random rnd = new Random();

    private static String createName(Sex sex) {
        if (sex == Sex.MALE) {
            return maleNames[rnd.nextInt(maleNames.length)];
        } else {
            return femaleNames[rnd.nextInt(femaleNames.length)];
        }
    }

    private static String createMaleSurname() {
        return surnames[rnd.nextInt(surnames.length)];
    }

    private static String turnToFemaleSurname(String surname) {
        if (surname.endsWith("ов") || surname.endsWith("ев")
                                    || surname.endsWith("ин")) {
            return (surname + 'а');
        }
        else if ( surname.endsWith("ий") || surname.endsWith("ой")
                                         || surname.endsWith("ый")) {
            return surname.substring(0, surname.length() - 2) + "ая";
        }

        return surname;
    }
    
    private static String createPatronymic(String fatherName, Sex sex) {
        if (sex == Sex.MALE) {
            return fatherName + "ович";
        } else {
            return fatherName + "овна";
        }
    }

    private static String createFaculty() {
        return faculties[rnd.nextInt(faculties.length)];
    }

    private static Parent createFather() {
        Sex sex = Sex.MALE;
        String surname = createMaleSurname();
        String name = createName(sex);
        String patronymic = createPatronymic(createName(sex), sex);
        int age = THE_YOUNGEST_FATHER_AGE + rnd.nextInt(FATHER_AGE_RANGE);
        int childrenQnt = rnd.nextInt(MAX_CHILDREN_PER_FATHER);
        Student[] children = new Student[childrenQnt];

        for (int i = 0; i < childrenQnt; i++) {
            children[i] = createStudent(name, surname);
        }

        if (rnd.nextBoolean()) {
            return new Parent(surname, name, patronymic, sex, age, children);
        } else {
            return new CoolParent(surname, name, patronymic, sex,
                    age, children);
        }
    }

    private static Student createStudent(String fatherName,
            String fatherSurname) {
        String studentSurname = fatherSurname;
        Sex sex = Sex.MALE;
        int age = THE_YOUNGEST_STUDENT_AGE + rnd.nextInt(STUDENT_AGE_RANGE);
        String faculty = createFaculty();

        if (rnd.nextBoolean()) {
            sex = Sex.FEMALE;
            studentSurname = turnToFemaleSurname(studentSurname);
        }

        String name = createName(sex);
        String patronymic = createPatronymic(fatherName, sex);

        if (rnd.nextBoolean()) {
            return new Student(studentSurname, name, patronymic, sex,
                    age, faculty, EXAMS_QNT);
        } else {
            return new Botan(studentSurname, name, patronymic, sex,
                    age, faculty, EXAMS_QNT);
        }
    }

    public static List<IHuman> createCollection() {
        LinkedList<IHuman> result = new LinkedList<IHuman>();
        int fathersQnt = 1 + rnd.nextInt(MAX_FATHERS);

        for (int i = 0; i < fathersQnt; i++) {
            Parent father = createFather();
            result.add(father);

            for (int j = 0; j < father.students.length; j++) {
                result.add(father.students[j]);
            }
        }

        return result;
    }
}
