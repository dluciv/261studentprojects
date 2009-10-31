/*Anton Karymov,gr261,2009
 *Test for Bubble and Insert sort
 */
package SortTest;

import org.junit.*;
import org.junit.Assert.*;
import sort.Insert;
import sort.Main;
import sort.MySort;

public class SortTest {

    int array[] = {2, 4, 4, 6, 1, 8, 7, 3};
    static MySort insert = new Insert();
    static MySort bubble = new Insert();

    public static boolean isSorted(int array[]) {
        for (int i = 0; i <= array.length - 2; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    @Test(expected = NullPointerException.class)
    public void NullSortTest() {
        Main.sort(null, array);
    }

    @Test(expected = NullPointerException.class)
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
    


