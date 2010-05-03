/* @author Eugene Todoruk
 * group 261
 */
package parentsAndChildren;

import java.util.Random;

public class Botan extends Student {

    private static final int LOWER_BOUNDS = 3;
    private static final int UPPER_BOUNDS = 5;

    public Botan(String firstName, String partonymic, String lastName,
            Sex sex, int age, String faculty) {
        super(firstName, partonymic, lastName, sex, age, faculty);

        Random random = new Random();
        for (int i = 0; i < MARKS_COUNT; i++) {
            marks[i] = LOWER_BOUNDS + random.nextInt(UPPER_BOUNDS - LOWER_BOUNDS + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        stringBuilder.append("\nДоп. информация: Ботан");

        return stringBuilder.toString();
    }
}
