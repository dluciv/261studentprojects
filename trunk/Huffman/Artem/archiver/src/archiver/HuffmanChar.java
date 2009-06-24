package archiver;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class HuffmanChar implements Comparable {
	public int key;
	public long oldWeight;
	public int newWeight;
	
	public int compareTo(Object o){
		if(o == null) return 1;
		if(o.getClass() != getClass()) return 1;
		if(((HuffmanChar)o).oldWeight >= oldWeight) return -1;
		if(((HuffmanChar)o).oldWeight < oldWeight) return 1;
		return 0;
	}
	public void printiw()
	{
		System.out.println(key+" - "+oldWeight+" - "+newWeight+" - "+(char)key+" ; ");
	}
}
