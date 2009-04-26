/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

/**
 *
 * @author Admin
 */
public class ItemWeight {
	public int key;
	public long oldWeight;
	public int newWeight;

	public void printiw()
	{
		System.out.println(key+" - "+oldWeight+" - "+newWeight+" - "+(char)key+" ; ");
	}
}
