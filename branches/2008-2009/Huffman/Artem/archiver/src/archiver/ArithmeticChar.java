package archiver;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class ArithmeticChar implements Comparable {
	public int key;
	public int weight;
	public int bound_low;
	public int bound_high;

	public static void printic(){
		System.out.println("key char - weight - segment0 to segment1");
	}
	public void printiw(){
		System.out.println(key+" "+(char)key+" - "+weight+" - "+bound_low+" to "+bound_high);
	}

	public int compareTo(Object o) {
		if(o == null) return 1;
		if(o.getClass() != getClass()) return 1;
		if(((ArithmeticChar)o).key >= key) return -1;
		if(((ArithmeticChar)o).key < key) return 1;
		return 0;
	}
}
