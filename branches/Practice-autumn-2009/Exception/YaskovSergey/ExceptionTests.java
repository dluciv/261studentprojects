/*
 * набор необходимых тестов для проекта;
 * (c) Yaskov Sergey, 2009
 */

package tests;

import org.junit.*;
import org.junit.Assert.*;
import exception.*;

public class ExceptionTests {
    static ISortWay bubble = new BubbleSort();
    static ISortWay quick = new QuickSort();
    static int[] arrayExample = {1, 2, 0};

    @Test(expected = IllegalArgumentException.class)
    public void NullArrayBubbleSorting() {
        Sorter.sort(bubble, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullArrayQuickSorting() {
        Sorter.sort(quick, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullInsteadOfCalss() {
        Sorter.sort(null, arrayExample);
    }
}