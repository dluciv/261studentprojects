/*
 * Generic
 * soldatov dmitry ©, 2009
 */
package generic;

import java.util.Random;

public class PropertiesGenerator {

    private static Random rnd = new Random();
    private static int minState = 1;
    private static int studentMark = 3;
    private static int fatherAge = 40;
    private static int kidAge = 20;
    private static int agePlus = 10;
    private static int botanMarkPlus = 3;
    private static int kidsNumberPlus = 5;
    private static int examsNumberPlus = 5;

    public Gender generateGender() {
        return Gender.values()[rnd.nextInt(Gender.values().length)];
    }

    public String generateFaculty() {
        return Names.getFaculties()[rnd.nextInt(Names.getFaculties().length)];
    }

    public String generateName(Gender gender) {
        if (gender == Gender.Male) {
            return Names.getMaleNames()[rnd.nextInt(Names.getMaleNames().length)];
        } else {
            return Names.getFemaleNames()[rnd.nextInt(Names.getFemaleNames().length)];
        }
    }

    public String generateLastName() {
        return Names.getLastNames()[rnd.nextInt(Names.getLastNames().length)];
    }

    //отчество человека зависит от его пола и имени отца, конечно же
    public String generateFatherName(Gender gender, String preFatherName) {
        if (gender == Gender.Male) {
            return preFatherName + "ович";
        } else {
            return preFatherName + "овна";
        }
    }

    public int generateParentAge() {
        return fatherAge + rnd.nextInt(agePlus);
    }

    public int generateStudentAge() {
        return kidAge + rnd.nextInt(agePlus);
    }

    public int generateKidsNumber() {
        return minState + rnd.nextInt(kidsNumberPlus);
    }

    public int generateExamsNumber() {
        return minState + rnd.nextInt(examsNumberPlus);
    }

    public int[] setBotanMarks(int examsNumber) {
        int[] marks = new int[examsNumber];
        for (int i = 0; i < examsNumber; i++) {
            marks[i] = studentMark + rnd.nextInt(botanMarkPlus);
        }
        return marks;
    }

    public int[] setStudentMarks(int examsNumber) {
        int[] marks = new int[examsNumber];
        for (int i = 0; i < examsNumber; i++) {
            marks[i] = studentMark;
        }
        return marks;
    }
}
