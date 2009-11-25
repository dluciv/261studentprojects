package generic;

import java.util.Random;

public class Botan extends Student {
    private String name;
    private String fathername;
    private String surname;
    private String faculty;
    private Sex sex;
    private int age;
    private static Random rnd = new Random();
    private static int minimalExamsNumber = 1;
    private static int examsNumber = minimalExamsNumber + rnd.nextInt(5);
    private static int[] marks = new int[examsNumber];

    public Botan(String name, String fathername, String surname, Sex sex, int age, String faculty) {
        super(name, fathername, surname, sex, age, faculty);
        this.name = name;
        this.fathername = fathername;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.faculty = faculty;

    }

    //@Override
    public static int[] getMarks() {

        for (int i = 0; i < marks.length; i++) {
            marks[i] = 3 + rnd.nextInt(3);
        }
        return marks;

    }
}


