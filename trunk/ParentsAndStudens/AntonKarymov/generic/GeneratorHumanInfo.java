package generic;

import java.util.Random;

public class GeneratorHumanInfo {

    static Random rnd = new Random();
    private static int minimalKidsNumber = 1;
    private static int minimalStudentAge = 17;
    private static int minimalParentAge = 40;
    private static String[] maleNames = {"Антон", "Петр", "Денис"};
    private static String[] femaleNames = {"Мария", "Анастасия", "Виолетта"};
    private static String[] surnames = {"Пальчиков", "Краснощёков", "Большеглазов"};
    private static String[] faculty = {"математико-механический", "медицинский", "филологичский"};

    public static String generateName(Sex sex) {
        if (sex == Sex.male) {
            return maleNames[rnd.nextInt(maleNames.length)];
        } else {
            return femaleNames[rnd.nextInt(femaleNames.length)];
        }
    }

    public static String generateSurname(Sex sex) {
        if (sex == Sex.male) {
            return surnames[rnd.nextInt(surnames.length)];
        } else {
            return surnames[rnd.nextInt(femaleNames.length)] + "а";
        }
    }

    public static String generateFaculty() {
        return faculty[rnd.nextInt(faculty.length)];
    }

    public static String generateFathername(Sex sex, String maleName) {
        if (sex == Sex.male) {
            return maleName + "ович";
        } else {
            return maleName + "овна";
        }
    }

    public static Sex generateSex() {
        return Sex.values()[rnd.nextInt(Sex.values().length)];
    }

    public static int generateKidsNumber() {
        return minimalKidsNumber + rnd.nextInt(4);
    }

    public static int generateStudentAge() {
        return minimalStudentAge + rnd.nextInt(10);
    }

    public static int generateParentAge() {
        return minimalParentAge + rnd.nextInt(40);
    }
}
