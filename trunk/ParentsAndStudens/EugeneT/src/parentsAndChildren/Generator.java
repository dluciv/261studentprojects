/* @author Eugene Todoruk
 * group 261
 */
package parentsAndChildren;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final int MAX_PARENTS = 20;
    private static final int MAX_CHILDREN = 10;
    private static final int MIN_PARENT_AGE = 40;
    private static final int MAX_PARENT_AGE = 100;
    private static final int MIN_STUDENT_AGE = 16;
    private static final int MAX_STUDENT_AGE = 25;
    
    private static final String MALE_SUFFIX = "ович";
    private static final String FEMALE_SUFFIX = "овна";
    private static final String[] MALE_FIRSTNAMES = new String[]{"Артём", "Владимир",
        "Петр", "Ярослав", "Всеволод", "Макар", "Александр", "Максим"};
    private static final String[] FEMALE_FIRSTNAMES = new String[]{"Наташа", "Мария",
        "Екатерина", "Дарья", "Ксения", "Анастасия", "София", "Анна", "Юля"};
    private static final String[] LASTNAMES = new String[]{"Тодорук", "Кривых",
        "Кравчук", "Веллер", "Квасоля", "Гердт", "Быстрых",};
    private static final String[] FACULTIES = new String[]{"Биолого-почвенный",
        "Восточный", "Географии и геоэкологии", "Геологический", "Журналистики",
        "Исторический", "Математико-механический", "Медицинский",
        "Международных отношений", "Психологии", "Социологии", "Физический"};

    public static List<IHuman> generatePeople() {
        List<IHuman> people = new LinkedList<IHuman>();
        Random random = new Random();
        Parent[] parents = generateParents(random);

        for (Parent parent : parents) {
            people.add(parent);

            for (Student child : parent.getChildren()) {
                people.add(child);
            }
        }

        return people;
    }

    private static Parent[] generateParents(Random random) {
        int parentCount = random.nextInt(MAX_PARENTS);
        Parent[] parents = new Parent[parentCount];

        for (int i = 0; i < parentCount; i++) {
            parents[i] = generateParent(random);
        }

        return parents;
    }

    private static Parent generateParent(Random random) {
        Sex sex = Sex.MALE;
        String firstName = generateFirstName(random, sex);
        String partonymic = makePartonymic(sex, generateFirstName(random, sex));
        String lastName = generateLastName(random);
        int age = generateParentAge(random);
        Student[] children = generateChildren(random, firstName, lastName);

        if (random.nextBoolean()) {
            return new Parent(firstName, partonymic, lastName, sex, age, children);
        } else {
            return new CoolParent(firstName, partonymic, lastName, sex, age, children);
        }
    }

    private static Student generateStudent(Random random, String parentFisrtName, String parentLastName) {
        Sex sex = generateSex(random);
        String firstName = generateFirstName(random, sex);
        String partonymic = makePartonymic(sex, parentFisrtName);
        String lastName = parentLastName;
        String faculty = generateFaculty(random);
        int age = generateStudentAge(random);

        if (random.nextBoolean()) {
            return new Student(firstName, partonymic, lastName, sex, age, faculty);
        } else {
            return new Botan(firstName, partonymic, lastName, sex, age, faculty);
        }
    }

    private static Student[] generateChildren(Random random, String parentFirstName,
                                              String parentLastName) {
        int childrenCount = random.nextInt(MAX_CHILDREN);
        Student[] children = new Student[childrenCount];

        for (int i = 0; i < childrenCount; i++) {
            children[i] = generateStudent(random, parentFirstName, parentLastName);
        }

        return children;
    }

    private static String generateFirstName(Random random, Sex sex) {
        if (sex == Sex.MALE) {
            return MALE_FIRSTNAMES[random.nextInt(MALE_FIRSTNAMES.length)];
        } else {
            return FEMALE_FIRSTNAMES[random.nextInt(FEMALE_FIRSTNAMES.length)];
        }
    }

    private static String generateLastName(Random random) {
        return LASTNAMES[random.nextInt(LASTNAMES.length)];
    }

    private static String makePartonymic(Sex sex, String parentFirstName) {
        if (sex == Sex.MALE) {
            return parentFirstName + MALE_SUFFIX;
        } else {
            return parentFirstName + FEMALE_SUFFIX;
        }
    }

    private static int generateParentAge(Random random) {
        return MIN_PARENT_AGE + random.nextInt(MAX_PARENT_AGE - MIN_PARENT_AGE);
    }

    private static int generateStudentAge(Random random) {
        return MIN_STUDENT_AGE + random.nextInt(MAX_STUDENT_AGE - MIN_STUDENT_AGE);
    }

    private static Sex generateSex(Random random) {
        if (random.nextBoolean()) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }
    }

    private static String generateFaculty(Random random) {
        return FACULTIES[random.nextInt(FACULTIES.length)];
    }
}
