/*
 * General realization of IHuman - able to return NSP, age and sex
 * Also function ToString make easier to return first three parameters
 * Savenko Maria (c)2009
 */

package msavenko.parentsandstudens;

public class Human implements IHuman {

    protected String name;
    protected String surname;
    protected String patronymic;
    protected Sex sex;
    protected int age;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
    
    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic + " [" + age + "]";
    }
}
