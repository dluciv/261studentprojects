/*
 * пример интерфейса, реализованного двумя классами;
 * (c) Yaskov Sergey, 2009
 */

package interfaceexample;

public class Sorter {
    static public int[] sort(ISortWay sortWay, int[] arrayToSort) {
        if (sortWay == null || arrayToSort == null) {
            throw new java.lang.NullPointerException();
        }

        return sortWay.sort(arrayToSort);
    }
}
