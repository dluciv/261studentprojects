package generic;

import java.util.Random;

public class Student implements IHuman {

    private static Random rnd = new Random();
    private String name;
    private String fathername;
    private String surname;
    private String faculty;
    private Sex sex;
    private int age;
    private static int minimalExamsNumber = 1;
    private static  int examsNumber = minimalExamsNumber + rnd.nextInt(5);
    private static int[]   marks = new int[examsNumber];

    public Student(String name, String fathername, String surname, Sex sex, int age, String faculty) {

        this.name = name;
        this.fathername = fathername;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.faculty = faculty;
        
    }

    public  String getName() {
        return name;
    }

    public String getFathername() {
        return fathername;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getFaculty() {
        return faculty;
    }
    
    public static int[] getMarks() {
        
        for (int i = 0; i < marks.length; i++) {
            marks[i] = 3;
        }
        return marks;
    }
   
    public static double getAveregeMark(int[] marks) {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
      return ((double)sum / (double)marks.length) ;

    }
    public static void printStudent(Student student) {
        System.out.printf("%s %s %s", student.getName(), student.getFathername(), student.getSurname());
        System.out.println();
        System.out.printf("%s %s%s %s%s %s", "пол:", student.getSex(), ", возраст:", student.getAge(), ", факультет:", student.getFaculty());
        System.out.println();
    }
}
