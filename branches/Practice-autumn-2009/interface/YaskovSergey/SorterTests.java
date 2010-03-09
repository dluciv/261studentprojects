/*
 * набор необходимых тестов для проекта;
 * (c) Yaskov Sergey, 2009
 */

package tests;

import org.junit.*;
import org.junit.Assert.*;
import interfaceexample.*;

public class SorterTests {
    static ISortWay bubble = new BubbleSort();
    static ISortWay quick = new QuickSort();
    static int[] unsortedArray = {94, 99, 79, 56, 39, 56, 45, 61, 67, 78, 87, 65, 77, 68, 2};

    public static boolean arrayIsSorted(int[] arrayToSort) {
        for (int i = 1; i < arrayToSort.length; i++) {
            if (arrayToSort[i - 1] > arrayToSort[i]) {
                return false;
            }
        }

        return true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullArrayBubbleSorting() {
        Sorter.sort(bubble, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullArrayQuickSorting() {
        Sorter.sort(quick, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SortWayIsNull() {
        Sorter.sort(null, unsortedArray);
    }

    @Test
    public void BubbleSortRightnessTest() {
        Assert.assertTrue(arrayIsSorted(Sorter.sort(bubble, unsortedArray)));
    }

    @Test
    public void QuickSortRightnessTest() {
        Assert.assertTrue(arrayIsSorted(Sorter.sort(quick, unsortedArray)));
    }
}
