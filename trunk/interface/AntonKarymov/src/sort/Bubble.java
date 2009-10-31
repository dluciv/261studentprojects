/*Anton Karymov,gr261,2009
 *Class including Bubble sorting
 */
package sort;

public class Bubble implements MySort {

    public int[] sortArray(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] >= array[j]) {
                    int helpcell = array[i];
                    array[i] = array[j];
                    array[j] = helpcell;
                }
            }
        }
        return array;
    }
}

