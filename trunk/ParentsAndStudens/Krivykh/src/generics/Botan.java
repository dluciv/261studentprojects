//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

import java.util.Random;

public class Botan extends Student {

    private static final int QUANTITY = 4;
    private static final int MIN_MARK = 3;
    private static final int MAX_MARK = 5;

    public Botan(String firstName, String secondName,
            String patronymic, EnumSex sex, int age, String faculty) {
        super(firstName, secondName, patronymic, sex, age, faculty);
        Random randomMark = new Random();
        for (int i = 0; i < QUANTITY; i++) {
            marks[i] = MIN_MARK + randomMark.nextInt(MAX_MARK - MIN_MARK + 1);
        }
    }
}
