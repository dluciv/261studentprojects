package btreedatabase;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Lapin Sergey
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