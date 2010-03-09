/*Anton Karymov,gr261,2009
 *Class Main including method which calling sortings
 *and throw IllegalArgumentException if argument of method equal null
 */
package sort;

public class Main {

    public static int[] sort(ISortAlgorithm changeSort, int unsortedArray[]) {
        if ((changeSort == null) || (unsortedArray == null)) {
            throw new java.lang.IllegalArgumentException();
        }
        return changeSort.sort(unsortedArray);
    }
}






