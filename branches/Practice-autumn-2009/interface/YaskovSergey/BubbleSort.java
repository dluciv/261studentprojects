/*
 * способ сортировки массива "пузырьком";
 * (c) Yaskov Sergey, 2009
 */

package interfaceexample;

public class BubbleSort implements ISortWay {
    public int[] sort(int[] arrayToSort) {
        if (arrayToSort == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (arrayToSort.length <= 0) {
            throw new java.lang.NegativeArraySizeException();
        }

        for (int i = 0; i < arrayToSort.length; i++) {
            for (int j = arrayToSort.length - 1; j > i; j--) {
                if (arrayToSort[j - 1] > arrayToSort[j]) {
                    int temp = arrayToSort[j - 1];
                    arrayToSort[j - 1] = arrayToSort[j];
                    arrayToSort[j] = temp;
                }
            }
        }

        return arrayToSort;
    }
}
