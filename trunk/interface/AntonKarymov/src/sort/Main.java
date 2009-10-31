/*Anton Karymov,gr261,2009
 *Class Main including method which calling sortings
 *and throw NullPointerExeption if argument of method equal null
 */
package sort;

public class Main {

    public static int[] sort(MySort changeSort, int array[]) {
        if ((changeSort == null) || (array == null)) {
            throw new java.lang.NullPointerException();
        }
        return changeSort.sortArray(array);
    }
}






