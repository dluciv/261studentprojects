/*(c) Antonova Kirilla 2009
 *  Generate 
 */

package parentsstudents;

import java.util.Random;
import java.util.LinkedList;
import java.util.List;

public class Generator {
    private static final int THE_YOUNGEST_FATHER_AGE = 25;
    private static final int FATHER_AGE_RANGE = 5;
    private static final int THE_YOUNGEST_STUDENT_AGE = 17;
    private static final int STUDENT_AGE_RANGE = 5;
    private static final int MAX_FATHERS = 9;
    private static final int MAX_CHILDREN_PER_FATHER = 5;
    private static final int exams_counter = 4;
    private static final String[] maleNames = new String[] {
        "Артем", "Владимир", "Кирилл", "Иван", "Тарас", "Александр", "Петр"
    };
    private static final String[] femaleNames = new String[] {
        "Галя", "Клава", "Ангелина", "Анна", "Дуня", "София", "Юля"
    };
    private static final String[] surnames = new String[] {
        "Антонов", "Игнатьев", "Алексеев", "Скворцовв", "Ефремов", "Кириллов", "Максимов"
    };
    private static final String[] faculties = new String[] {
       "Биолого-почвенный",
        "Восточный", "Географии и геоэкологии", "Геологический", "Журналистики",
        "Исторический", "Математико-механический", "Медицинский",
        "Международных отношений", "Психологии", "Социологии", "Физический"
    };

    private static Random random = new Random();
	
    //randomly generate a name
    private static String generateName(Sex sex) {
        if (sex == Sex.male) {
            return maleNames[random.nextInt(maleNames.length)];
        } else {
            return femaleNames[random.nextInt(femaleNames.length)];
        }
    }

	//randomly generate a malesurname
    private static String generateMaleSurname() {
        return surnames[random.nextInt(surnames.length)];
    }

	//construction of women's surname
    private static String turnToFemaleSurname(String surname) {
	    // if surname ends with "ов" "ев" "ин" => add the ending 'а'
        if ((surname.endsWith("ов") || surname.endsWith("ев")
                                    || surname.endsWith("ин"))) {
            return (surname + 'а');
        }
		//if surname ends with "ий"  "ой" => Look at the end of the string subtracting namely -2 and add the ending "ая"
        else if ( (surname.endsWith("ий") || surname.endsWith("ой"))) {
            return surname.substring(0, surname.length() - 2) + "ая";
        }

        return surname;
    }
    //construction of FatherName
    private static String generateFatherName(String fatherName, Sex sex) {
        if (sex == Sex.male) {
            return fatherName + "ович";
        } else {
            return fatherName + "овна";
        }
    }

	//generate Faculty randomly
    private static String generateFaculty() {
        return faculties[random.nextInt(faculties.length)];
    }
 
    //generate Father
    private static Parents generateFather() {
        Sex sex = Sex.male;
        String surname = generateMaleSurname();
        String name = generateName(sex);
        String FatherName = generateFatherName(generateName(sex), sex);
        int age = THE_YOUNGEST_FATHER_AGE + random.nextInt(FATHER_AGE_RANGE);
        int children_Counter = random.nextInt(MAX_CHILDREN_PER_FATHER);
        Student[] children = new Student [children_Counter];
 
        
        for (int i = 0; i < children_Counter; i++) {
            children[i] = generateStudent(name, surname);
        }

        if (random.nextBoolean()) {
            return new Parents(surname, name, FatherName, sex, age, children);
        } else {
            return new CoolParent(surname, name, FatherName, sex,
                    age, children);
        }
    }

    private static Student generateStudent(String fatherName,
                                           String fatherSurname) {
        String studentSurname = fatherSurname;
        Sex sex = Sex.male;
        int age = THE_YOUNGEST_STUDENT_AGE + random.nextInt(STUDENT_AGE_RANGE);
        String faculty = generateFaculty();

        if (random.nextBoolean()) {
            sex = Sex.female;
            studentSurname = turnToFemaleSurname(studentSurname);
        }

        String name = generateName(sex);
        String FatherName = generateFatherName(fatherName, sex);

        if (random.nextBoolean()) {
            return new Student(studentSurname, name, FatherName, sex,
                    age, faculty, exams_counter);
        } else {
            return new Botan(studentSurname, name, FatherName, sex,
                    age, faculty, exams_counter);
        }
    }

    public static List<IHuman> generateCollection() {
        LinkedList<IHuman> result = new LinkedList<IHuman>();
        int fathers_Counter = 1 + random.nextInt(MAX_FATHERS);

        for (int i = 0; i < fathers_Counter; i++) {
            Parents father = generateFather();
            result.add((IHuman) father);

            for (int j = 0; j < father.students.length; j++) {
                result.add(father.students[j]);
            }
        }

        return result;
    }

}
