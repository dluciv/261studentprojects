package btreedatabase;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public interface BasicItem {
	public int getRecordSize();

    public BasicItem Instance();
	
	byte[] serialize();	
	
	void unserialize(byte[] data);

    @Override
    String toString ();

    ArrayList<Comparator> AvaliableOrders();
}