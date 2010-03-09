/* @author Eugene Todoruk
 * group 261
 */

package parentsAndChildren;

public class CoolParent extends Parent {

    private static final int MAGIC_MONEY_CONST = 10;
    private int money = 0;

    public CoolParent(String firstName, String partonymic, String lastName,
            Sex sex, int age, Student[] children) {
        super(firstName, partonymic, lastName, sex, age, children);

        for (Student child : children) {

            money += MAGIC_MONEY_CONST * child.getMeanRating();
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        stringBuilder.append("\nДоп. информация: Хороший отец");
        stringBuilder.append("\nДенег: ").append(money);

        return stringBuilder.toString();
    }
}
