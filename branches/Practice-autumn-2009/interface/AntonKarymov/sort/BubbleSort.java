/*Anton Karymov,gr261,2009
 *Class including BubbleSort sorting
 */
package sort;

public class BubbleSort implements ISortAlgorithm {

    public int[] sort(int unsortedArray[]) {
        if (unsortedArray == null) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < unsortedArray.length - 1; i++) {
            for (int j = i + 1; j < unsortedArray.length; j++) {
                if (unsortedArray[i] >= unsortedArray[j]) {
                    int helpСell = unsortedArray[i];
                    unsortedArray[i] = unsortedArray[j];
                    unsortedArray[j] = helpСell;
                }
            }
        }
        return unsortedArray;
    }
}

