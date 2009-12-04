//(c) Antonov Kirill 2009
//Here we are describe some humanSpecifications

package parentsstudents;

public class humanSpecifications implements IHuman {
    private String surname;
    private String name;
    private String FatherName;
    private Sex sex;
    private int age;

    public humanSpecifications(String surname, String name, String FatherName,
                               Sex sex, int age){
        this.surname = surname;
        this.name = name;
        this.FatherName = FatherName;
        this.sex = sex;
        this.age = age;
    }

     public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFatherName() {
        return FatherName;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
