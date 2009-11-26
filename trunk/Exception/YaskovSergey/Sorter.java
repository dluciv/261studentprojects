/*
 * пример интерфейса, реализованного двумя классами;
 * в случае если вместо класса или массива в функцию "Sorter()" передан "null",
 * возбуждается исключение "IllegalArgumentException", которое в дальнейшем
 * может быть обработано;
 * (c) Yaskov Sergey, 2009
 */

package exception;

public class Sorter {
    static public int[] sort(ISortWay sortWay, int[] arrayToSort)
            throws IllegalArgumentException {
        if (sortWay == null || arrayToSort == null) {
            throw new java.lang.IllegalArgumentException("both arguments " +
                    "shouldn't be null");
        }

        return sortWay.sort(arrayToSort);
    }
}
