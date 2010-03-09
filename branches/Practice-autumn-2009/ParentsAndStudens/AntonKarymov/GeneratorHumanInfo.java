/*Anton Karymov,2009,261gr.
  In this class random generate information about human
 */
package generic;

public class GeneratorHumanInfo {
    private static int minimalKidsNumber = 1;
    private static int maximumKidsNumber = 4;
    private static int minimalStudentAge = 17;
    private static int minimalParentAge = 40;
    private static String[] maleNames = {"Антон", "Петр", "Денис"};
    private static String[] femaleNames = {"Мария", "Анастасия", "Виолетта"};
    private static String[] surnames = {"Пальчиков", "Краснощёков", "Большеглазов"};
    private static String[] faculty = {"математико-механический", "медицинский", "филологичский"};
    private static String endFemaleSurnames = "a";
    private static String endMalePatronymic = "ович";
    private static String endFemalePatronymic = "овна";
    private static int maximumAdditionToStudentAge = 10;
    private static int maximumAdditionToParentAge = 40;

    public static String generateName(Sex sex) {
        if (sex == Sex.male) {
            return maleNames[MyRandom.getRandom().nextInt(maleNames.length)];
        } else {
            return femaleNames[MyRandom.getRandom().nextInt(femaleNames.length)];
        }
    }

    public static String generateSurname(Sex sex, String fatherSurname) {
        if (sex == Sex.male) {
            return fatherSurname;
        } else {
            return fatherSurname + endFemaleSurnames;
        }
    }

    public static String generateFatherSurname() {
        return surnames[MyRandom.getRandom().nextInt(surnames.length)];
    }

    public static String generateFaculty() {
        return faculty[MyRandom.getRandom().nextInt(faculty.length)];
    }

    public static String generatePatronymic(Sex sex, String maleName) {
        if (sex == Sex.male) {
            return maleName + endMalePatronymic;
        } else {
            return maleName + endFemalePatronymic;
        }
    }

    public static Sex generateSex() {
        return Sex.values()[MyRandom.getRandom().nextInt(Sex.values().length)];
    }

    public static int generateKidsNumber() {
        return minimalKidsNumber + MyRandom.getRandom().nextInt(maximumKidsNumber);
    }

    public static int generateStudentAge() {
        return minimalStudentAge + MyRandom.getRandom().nextInt(maximumAdditionToStudentAge);
    }

    public static int generateParentAge() {
        return minimalParentAge + MyRandom.getRandom().nextInt(maximumAdditionToParentAge);
    }
}
