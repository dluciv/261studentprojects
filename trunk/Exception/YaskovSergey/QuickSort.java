/*
 * способ сортировки массива "быстрая сортировка";
 * (c) Yaskov Sergey, 2009
 */

package exception;

public class QuickSort implements ISortWay {
    public int[] sort(int[] arrayToSort) {
        int endIndex = arrayToSort.length - 1;
        int startIndex = 0;

        if (arrayToSort == null) {
            throw new java.lang.NullPointerException();
        }
        if (arrayToSort.length <= 0) {
            throw new java.lang.NegativeArraySizeException();
        }

        return doSort(startIndex, endIndex, arrayToSort);
    }

    private int[] doSort(int start, int end, int[] arrayToSort) {
        if (start >= end)
            return arrayToSort;

        int i = start;
        int j = end;
        int cur = (i + j) / 2;

        while (i < j) {
            while (i < cur && (arrayToSort[i] <= arrayToSort[cur])) {
                i++;
            }
            while (j > cur && (arrayToSort[cur] <= arrayToSort[j])) {
                j--;
            }
            if (i < j) {
                int temp = arrayToSort[i];
                arrayToSort[i] = arrayToSort[j];
                arrayToSort[j] = temp;
                if (i == cur) {
                    cur = j;
                }
                else if (j == cur) {
                    cur = i;
                }
            }
        }
        doSort(start, cur, arrayToSort);
        doSort(cur + 1, end, arrayToSort);

        return arrayToSort;
    }
}
