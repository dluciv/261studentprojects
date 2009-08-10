package Cards;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import BTree.Value;


public class CardManager implements BTree.Manager {

	public int compare(Value a, Value b) {
		
		Card ca = (Card)a;
		Card cb = (Card)b;
		if (ca.Adress.equalsIgnoreCase(cb.Adress)) {
			return ca.phone_number - cb.phone_number;
		}
		return ca.Adress.compareToIgnoreCase(cb.Adress);
	}

	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub		
	}

	public void writeExternal(ObjectOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public Value getValue() {
		return new Card(0,"","");
	}
	
}
