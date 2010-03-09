/*Anton Karymov,2009,261gr.
  Parent implements interface for all people
  Parent has a kids(all kids are student ore botan)
  Parent can  print own information on the screen
 */
package generic;

public class Parent implements IHuman {
    private String name;
    private String surname;
    private String fathername;
    private int age;
    private Student[] kids ;

//Parent's class constructor(parent's name,parent's patronymic,
//                     parent's surname,parent's sex,parent's age,parent's kids)
    Parent(String name, String fathername, String surname, Sex sex, int age, Student[] kids) {

        if (name == null || fathername== null || surname == null || sex == null || age < 0 || kids == null) {
            throw new java.lang.IllegalArgumentException();
        }
        this.name = name;
        this.fathername = fathername;
        this.surname = surname;
        this.age = age;
        this.kids = kids;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
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
        return kids.length;
    }

    public Student[] getKids() {
        return kids;
    }

//print parent's information on the screen
    public static void printParent(Parent parent) {
        System.out.printf("%s %s %s %s", "Отец:", parent.getName(),
                parent.getPatronymic(), parent.getSurname());
        System.out.println();
        System.out.printf("%s %s%s %s %s", "Возраст отца", parent.age,
                ", имеет", parent.getKidsNumber(), "детей:");
        System.out.println();
    }
}
