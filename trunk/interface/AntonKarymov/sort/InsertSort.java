/*Anton Karymov,gr261,2009
 *Class including InsertSort sorting
 */
package sort;

public class InsertSort implements ISortAlgorithm {

    public int[] sort(int unsortedArray[]) {
        if (unsortedArray == null) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < unsortedArray.length; i++) {
            int j = i;
            int helpСell = unsortedArray[i];
            while (j > 0 && helpСell < unsortedArray[j - 1]) {
                unsortedArray[j] = unsortedArray[j - 1];
                j--;
            }
            unsortedArray[j] = helpСell;
        }
        return unsortedArray;
    }
}
