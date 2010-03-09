/*
 * Generic
 * soldatov dmitry ©, 2009
 */
package generic;

public class Names {

    private static String[] maleNames = {"Петр", "Ипполит", "Алексндр", "Иван", "Караван"};
    private static String[] femaleNames = {"Ирина", "Гадя", "Гюльчатай", "Екатерина", "Елизавета"};
    private static String[] lastNames = {"Кац", "Эйнштейн", "Шульман", "Рубенштейн"};
    private static String[] faculties = {"Матмех", "Физмех", "Биомех", "Геомех"};

    public static String[] getMaleNames() {
        return maleNames;
    }

    public static String[] getFemaleNames() {
        return femaleNames;
    }

    public static String[] getLastNames() {
        return lastNames;
    }

    public static String[] getFaculties() {
        return faculties;
    }
}