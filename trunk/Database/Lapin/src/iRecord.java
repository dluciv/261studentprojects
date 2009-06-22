
import java.util.ArrayList;
import java.util.Comparator;



public interface iRecord {	
	public int getRecordSize();

    public iRecord Instance();
	
	byte[] serialize();	
	
	void unserialize(byte[] data);

    @Override
    String toString ();

    ArrayList<Comparator> AvaliableOrders();
}