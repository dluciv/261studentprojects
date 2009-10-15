/*Anton Karymov,gr261,2009
*Class Main including method which calling sortings 
*and throw NullPointerExeption if argument of method equal null
*/

package sort;

public class Main {
    static MySort bubble = new Bubble();
    static MySort insert = new Insert();

    public static void sorting (MySort changeSort,int array []){
        if (changeSort == null)
            throw new java.lang.NullPointerException();
        changeSort.sortArray(array);
    }
    public static void main(String[] args){
       int check[]={1,32,4,434};
       sorting (insert,check);
       int check1[]={2,45,23,32,32};
       sorting (bubble,check1);
    }
}






