/*Anton Karymov,gr261,2009
 *Class including Insert sorting
 */
package sort;

public class Insert implements MySort {

    public int[] sortArray(int array[]) {
        for (int i = 0; i < array.length; i++) {
            int j = i;
            int helpcell = array[i];
            while (j > 0 && helpcell < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = helpcell;
        }
        return array;
    }
}
