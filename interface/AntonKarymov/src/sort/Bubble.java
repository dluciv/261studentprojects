/*Anton Karymov,gr261,2009
*Class including Bubble sorting
*/
package sort;

import java.util.Arrays;

public class Bubble implements MySort {
    public void sortArray(int array[]) {
        for(int i=0;i < array.length-1;i++){
            for(int j=i+1;j < array.length;j++){
                if ( array[i] >= array[j] ){
                    int helpcell = array[i];
                    array[i] = array[j];
                    array[j] = helpcell;
                }
            }
        }
    System.out.println(Arrays.toString(array));
    }
}

