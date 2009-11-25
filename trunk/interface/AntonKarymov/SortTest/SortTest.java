/*Anton Karymov,gr261,2009
 *Test for Bubble and InsertSort sort
 */
package SortTest;

import org.junit.*;
import org.junit.Assert.*;
import sort.InsertSort;
import sort.Main;
import sort.ISortAlgorithm;

public class SortTest {

    int array[] = {2, 4, 4, 6, 1, 8, 7, 3};
    static ISortAlgorithm insert = new InsertSort();
    static ISortAlgorithm bubble = new InsertSort();

    public static boolean isSorted(int array[]) {
        for (int i = 0; i <= array.length - 2; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullSortTest() {
        Main.sort(null, array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullArrayTest() {
        Main.sort(bubble, null);
    }

    @Test
    public void BubbleCorrectTest() {
        Assert.assertTrue(isSorted(Main.sort(bubble, array)));
    }

    @Test
    public void InsertCorrectTest() {
        Assert.assertTrue(isSorted(Main.sort(insert, array)));
    }
}
    


