/* @author Eugene Todoruk
 * group 261
 */

package parentsAndChildren;

public class Parent extends Human {
    
    private Student[] children;

    public Parent(String firstName, String partonymic, String lastName,
                  Sex sex, int age, Student[] children) {
        super(firstName, partonymic, lastName, sex, age);

        setChildren(children);
    }

    public void setChildren(Student[] children) {
        this.children = children;
    }

    public Student[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        stringBuilder.append("\nСтатус: Отец");
        stringBuilder.append("\nДетей: ").append(children.length);

        return stringBuilder.toString();
    }
}
