//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HumanGenerator {

    private static final String[] PARENT_PATRONYMICS = {"Иванович", "Алексеевич", "Дмитриевич"};
    private static final String[] FIRST_NAMES = {"Кривых", "Тодорук", "Устименко", "Литвиненко"};
    private static final String[] SECOND_MALE_NAMES = {"Петр", "Иван", "Александр", "Максим"};
    private static final String[] SECOND_FEMALE_NAMES = {"Анна", "Мария", "Анастасия", "Светлана"};
    private static final String[] FACULTYS = {"Математико-Мехфнический", "Биолого-Почвенный", "Физический", "Геологический", "Филосовский"};
    private static final int MAX_PARENTS_AGE = 80;
    private static final int MIN_PARENTS_AGE = 40;
    private static final int MIN_CHILD_AGE = 17;
    private static final int MAX_CHILD_AGE = 24;
    private static final int MAX_QUANTITY_OF_PARENT = 10;
    private static final int MAX_QUANTITY_OF_CHILDREN = 5;
    private static Random random = new Random();

    private static String choseFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    private static String choseSecondName(EnumSex sex) {
        if (sex == EnumSex.MALE) {
            return SECOND_MALE_NAMES[random.nextInt(SECOND_MALE_NAMES.length)];
        } else {
            return SECOND_FEMALE_NAMES[random.nextInt(SECOND_FEMALE_NAMES.length)];
        }
    }

    private static String chosePantonymic() {
        return PARENT_PATRONYMICS[random.nextInt(PARENT_PATRONYMICS.length)];
    }

    private static int choseAge(int maxAge, int minAge) {
        return minAge + random.nextInt(maxAge - minAge + 1);
    }

    private static EnumSex choseSex() {
        if (random.nextBoolean()) {
            return EnumSex.FEMALE;
        } else {
            return EnumSex.MALE;
        }
    }

    private static String choseFaculty() {
        return FACULTYS[random.nextInt(FACULTYS.length)];
    }

    private static String childPatonymic(EnumSex childSex, String parentName) {
        if (childSex == EnumSex.MALE) {
            return parentName + "ович";
        } else {
            return parentName + "овна";
        }
    }

    private static List<Student> childrenGenerator(String parentSecondName,
            String parentFirstName) {
        List student = new LinkedList<Student>();
        for (int i = 0; i < random.nextInt(MAX_QUANTITY_OF_CHILDREN); i++) {
            EnumSex sex = choseSex();
            if (random.nextBoolean()) {
                student.add(new Student(parentFirstName, choseSecondName(sex),
                        childPatonymic(sex, parentSecondName), sex, choseAge(MAX_CHILD_AGE, MIN_CHILD_AGE),
                        choseFaculty()));
            } else {
                student.add(new Botan(parentFirstName, choseSecondName(sex),
                        childPatonymic(sex, parentSecondName), sex, choseAge(MAX_CHILD_AGE, MIN_CHILD_AGE),
                        choseFaculty()));
            }
        }
        return student;
    }

    public static IHuman parentGenerator() {
        String firstName = choseFirstName();
        String secondName = choseSecondName(EnumSex.MALE);
        String patonymic = chosePantonymic();
        int age = choseAge(MAX_PARENTS_AGE, MIN_PARENTS_AGE);
        if (random.nextBoolean()) {
            return new Parent(firstName, secondName, patonymic, EnumSex.MALE,
                    age, childrenGenerator(secondName, firstName));
        } else {
            return new CoolParent(firstName, secondName, patonymic, EnumSex.MALE,
                    age, childrenGenerator(secondName, firstName));
        }
    }

    public static List<IHuman> humanGenerator() {
        List human = new LinkedList<IHuman>();
        for (int i = 0; i < random.nextInt(MAX_QUANTITY_OF_PARENT); i++) {
            human.add(parentGenerator());
        }
        return human;
    }
}
