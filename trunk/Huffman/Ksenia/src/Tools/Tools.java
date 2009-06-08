package Tools;
import java.util.HashMap;


public class Tools {
	
	static public HashMap<Byte, Integer> TableCode(byte[] mas){
		HashMap<Byte, Integer> Table = new HashMap<Byte, Integer>();
		for(byte i:mas){
			if(Table.containsKey(i)){
				int n=Table.get(i)+1;
				Table.put(i, n);
			}
			else{
				Table.put(i, 1);
			}
		}
		return Table;
	}

}
