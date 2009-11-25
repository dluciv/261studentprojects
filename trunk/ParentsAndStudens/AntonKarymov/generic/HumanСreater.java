package generic;

import java.util.Random;

public class HumanСreater {

    static Random rnd = new Random();

    public static IHuman createStudent(String fatherName) {
        Sex sex = GeneratorHumanInfo.generateSex();
        String name = GeneratorHumanInfo.generateName(sex);
        String fathername = GeneratorHumanInfo.generateFathername(sex, fatherName);
        String surname = GeneratorHumanInfo.generateSurname(sex);
        int age = GeneratorHumanInfo.generateStudentAge();
        String faculty = GeneratorHumanInfo.generateFaculty();
        

        if (rnd.nextBoolean()) {
            return new Student(name, fathername, surname, sex, age, faculty);
        } else {
            return new Botan(name, fathername, surname, sex, age, faculty);
        }
    }

    public static IHuman createParent() {
        String name = GeneratorHumanInfo.generateName(Sex.male);
        String fathername = GeneratorHumanInfo.generateFathername(Sex.male, GeneratorHumanInfo.generateName(Sex.male));
        String surname = GeneratorHumanInfo.generateSurname(Sex.male);
        Sex sex = Sex.male;
        int age = GeneratorHumanInfo.generateParentAge();
        int kidsNumber = GeneratorHumanInfo.generateKidsNumber();
        Student[] kids = new Student[kidsNumber];

        for (int i = 0; i < kidsNumber; i++) {
            kids[i] =  (Student) createStudent(name);//нафига сдесь нужно(Student)
        }

        if (rnd.nextBoolean()) {
            return new Parent(name, fathername, surname, sex, age, kidsNumber, kids);
        } else {
            return new CoolParent(name, fathername, surname, sex, age, kidsNumber, kids);
        }
    }
}




