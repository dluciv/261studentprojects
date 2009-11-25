package generic;

public class Parent implements IHuman {

    private String name;
    private String surname;
    private String fathername;
    private Sex sex;
    private int age;
    private int kidsNumber;
    private Student[] kids = new Student[kidsNumber];

    Parent(String name, String fathername, String surname, Sex sex, int age, int kidsNumber, Student[] kids) {
        this.name = name;
        this.fathername = fathername;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.kidsNumber = kidsNumber;
        this.kids = kids;


    }

    public String getName() {
        return name;
    }

    public String getFathername() {
        return fathername;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return Sex.male;
    }

    public int getAge() {
        return age;
    }

    public int getKidsNumber() {
        return kidsNumber;
    }

    public Student[] getKids() {
        return kids;
    }

    public static void printParent(Parent parent) {
        System.out.printf("%s %s %s %s", "Отец:", parent.getName(), parent.getFathername(), parent.getSurname());
        System.out.println();
        System.out.printf("%s %s%s %s %s", "Возраст отца", parent.age, ", имеет", parent.getKidsNumber(), "детей:");
        System.out.println();
    }
}
