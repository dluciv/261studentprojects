/* @author Eugene Todoruk
 * group 261
 */

package parentsAndChildren;

public class Human implements IHuman {
    
    private String firstName;
    private String partonymic;
    private String lastName;
    private Sex sex;
    private int age;
    
    public Human(String firstName, String partonymic, String lastName,
                 Sex sex, int age) {
        setFirstName(firstName);
        setPartonymic(partonymic);
        setLastName(lastName);
        setSex(sex);
        setAge(age);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPartonymic(String partonymic) {
        this.partonymic = partonymic;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return partonymic;
    }

    public String getLastName() {
        return lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("ФИО: ").append(lastName).append(" ").append(firstName).
                      append(" ").append(partonymic);
        stringBuilder.append("\nПол: ").append(sex);
        stringBuilder.append("\nВозраст: ").append(age);

        return stringBuilder.toString();
    }
}
