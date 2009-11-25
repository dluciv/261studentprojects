package generic;

public class CoolParent extends Parent {

    private String name;
    private String surname;
    private String fathername;
    private Sex sex;
    private int age;
    private static int kidsNumber;
    private Student[] kids = new Student[kidsNumber];

    CoolParent(String name, String fathername, String surname, Sex sex, int age, int kidsNumber, Student[] kids) {
        super(name, fathername, surname, sex, age, kidsNumber, kids);
        this.name = name;
        this.fathername = fathername;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.kidsNumber = kidsNumber;
        this.kids = kids;
    }

    public static double countMoney(Student[] kids) {
        double sumMoney = 0.0;
        for (int i = 0; i < kids.length; i++) {
            sumMoney += 10 * kids[i].getAveregeMark(kids[i].getMarks());
        }
        return sumMoney;
    }
}
