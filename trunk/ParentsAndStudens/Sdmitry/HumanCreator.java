/*
 * Generic
 * soldatov dmitry ©, 2009
 */
package generic;

import java.util.Random;

public class HumanCreator {

    private static Random rnd = new Random();
    private static PropertiesGenerator gen = new PropertiesGenerator();

    public Parent createParent() {

        boolean isParent = rnd.nextBoolean();
        String father = "Отец ";
        String coolFather = "КрутойОтец ";

        Gender gender = Gender.Male;
        String name = gen.generateName(gender);
        String lastName = gen.generateLastName();
        String fatherName = gen.generateFatherName(gender,
                gen.generateName(gender));
        int age = gen.generateParentAge();
        int kidsNumber = gen.generateKidsNumber();
        Student[] kids = new Student[kidsNumber];

        for (int i = 0; i < kidsNumber; i++) {
            kids[i] = createStudent(name, lastName);
        }

        if (isParent) {
            return new Parent(father + name, lastName, fatherName, gender, age,//!!!
                    kids);
        } else {
            return new CoolParent(coolFather + name, lastName, fatherName, gender, age,//!!!
                    kids);
        }
    }

    public Student createStudent(String preFatherName, String preFatherLastName) {

        boolean isStudent = rnd.nextBoolean();
        String student = "  Студент ";
        String botan = "  Ботан ";

        Gender gender = gen.generateGender();
        String name = gen.generateName(gender);
        String lastName = preFatherLastName;
        String fatherName = gen.generateFatherName(gender, preFatherName);
        String faculty = gen.generateFaculty();
        int age = gen.generateStudentAge();
        int examsNumber = gen.generateExamsNumber();

        if (isStudent) {
            int[] marks = gen.setStudentMarks(examsNumber);
            return new Student(student + name, lastName, fatherName, gender, age,
                    faculty, examsNumber, marks);
        } else {
            int[] marks = gen.setBotanMarks(examsNumber);
            return new Botan(botan + name, lastName, fatherName, gender, age,
                    faculty, examsNumber, marks);
        }
    }
}
