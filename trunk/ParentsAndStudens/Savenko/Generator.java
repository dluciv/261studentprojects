/*
 * generates Fathers and their students-children
 * counts marks and CoolFather's money
 * Savenko Maria (c)2009
 */

package msavenko.parentsandstudens;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import msavenko.parentsandstudens.IHuman.Sex;

public class Generator {
    
    private static final Random   random     = new Random();
    public static final String[] namesMale   = new String[] { 
            "Олег", "Иван", "Егор", "Петр", "Владимир", "Валентин", "Вячеслав" };
    public static final String[] namesFemale = new String[] { 
            "Мария", "Екатерина", "Анна", "Елена", "Алена", "Дарья" };
    private static final String[] surnames    = new String[] { 
            "Лебедев", "Воробьев", "Кузнецов", "Смирнов", "Соловьев" };
    private static final String[] faculties   = new String[] {
            "Математико-Механический", "Международных Отношений", "Геологический", 
            "Юридический", "Физический", "Юридический", "Журналистики" };
    
    public static String generateName(Sex sex) {
        if (sex == Sex.female) {
            return namesFemale[random.nextInt(namesFemale.length)];
        }
        else {
            return namesMale[random.nextInt(namesMale.length)];
        }
    }
    
    public static String generateFathersSurname() {
        return surnames[random.nextInt(surnames.length)];
    }
    
    public static String generateChildsSurname(Sex sex, String surname){
        if (sex == Sex.female){
            return surname + "а";
        }
        else{
            return surname;
        }
    }
    
    public static String generatePatronymic(String fatherName, Sex sex) {
        if (sex == Sex.female) {
            return fatherName + "овна";
        }
        else {
            return fatherName + "ович";
        }
    }
    
    public static String generateFaculty() {
        return faculties[random.nextInt(faculties.length)];
    }
    
    public static Student generateChild(String fatherName, String Surname) {
        Sex sex = Sex.values()[random.nextInt(Sex.values().length)];
        
        String name = generateName(sex);
        String surname = generateChildsSurname(sex, Surname);
        String patronymic = generatePatronymic(fatherName, sex);
        String faculty = generateFaculty();
        
        int age = 17 + random.nextInt(8);
        
        if (random.nextBoolean()) {
            return new Student(name, surname, patronymic, sex, age, faculty);
        }
        else {
            return new Botan(name, surname, patronymic, sex, age, faculty);
        }
    }
    
    public static Father generateFather() {
        Sex sex = Sex.male;
        
        String Name = generateName(sex);
        String Surname = generateFathersSurname();
        String Patronymic = generatePatronymic(generateName(sex), sex);
        int age = 30 + random.nextInt(10);
        
        int numOfChildren = 1 + random.nextInt(6);
        Student[] children = new Student[numOfChildren];
        for (int i = 0; i < numOfChildren; i++) {
            children[i] = generateChild(Name, Surname);
        }
        
        if (random.nextBoolean()) {
            return new Father(Name, Surname, Patronymic, sex, age, children);
        }
        else {
            return new CoolFather(Name, Surname, Patronymic, sex, age, children);
        }
    }
    
    public static List<IHuman> generateCollection() {
        LinkedList<IHuman> FatherChildrenList = new LinkedList<IHuman>();
        
        int parentCount = random.nextInt(10)+1;
        for (int i = 0; i < parentCount; i++) {
            Father father = generateFather();
            FatherChildrenList.add(father);
            
            for (int j = 0; j < father.getStudentCount(); j++) {
                FatherChildrenList.add(father.getStudent(j));
            }
        }
        return FatherChildrenList;
    }
    
    public static int calcMoney(List<IHuman> humans) {
        int amount = 0;
        for (IHuman i : humans) {
            if (i instanceof ICoolFather) {
                amount += ((ICoolFather) i).getAmountOfMoney();
            }
        }

        return amount;
    }

    public static int calcMark(List<IHuman> humans) {
        int marksSum = 0;
        int count = 0;
        for (IHuman i : humans) {
            if (i instanceof IBotan) {
                int excount = ((IBotan) i).getNumberOfExams();

                count += excount;
                for (int j = 0; j < excount; j++) {
                    marksSum += ((IBotan) i).getMarkForExam(j);
                }
            }
        }

        return (marksSum / count);
    }
}
