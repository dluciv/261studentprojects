/* @author Eugene Todoruk
 * group 261
 */

package parentsAndChildren;

public interface IHuman {

    void setFirstName(String firstName);
    void setPartonymic(String partonymic);
    void setLastName(String lastName);
    void setSex(Sex sex);
    void setAge(int age);

    String getFirstName();
    String getPatronymic();
    String getLastName();
    Sex getSex();
    int getAge();
}
