/**
* BTree
* Ksenyia Cypan (c) 2009
*/

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
    public int compare2(Value a, Value b){
        Card ca = (Card)a;
		Card cb = (Card)b;
        StringBuilder phoneNum1 = new StringBuilder ();
        StringBuilder phoneNum2 = new StringBuilder ();
        phoneNum1.append(ca.phone_number);
        phoneNum2.append(cb.phone_number);
  
        int sum1 = 0;
        for (int i =0; i<phoneNum1.length(); i++)
        {
            sum1 += (int) phoneNum1.charAt(i);
        }

        int sum2 = 0;
        for (int i =0; i<phoneNum2.length(); i++)
        {
            sum2 += (int) phoneNum2.charAt(i);
        }

        return sum2 - sum1;

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
