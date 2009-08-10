package Cards;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import BTree.Value;

public class Card implements Value {
	
	
	String Name;
	String Adress;
	int phone_number;	
	
	public Card(int number, String name, String adress) {
		this.phone_number = number;
		this.Name = name;
		this.Adress = adress;
	}
	
	public String toString() {		
		return "[ Name = \"" + Name + "\", Adress = \"" + Adress + "\", Number = \"" + phone_number + "\"]";
	}

	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		Name = (String)arg0.readObject();
		Adress = (String)arg0.readObject();
		phone_number = arg0.readInt();		
	}

	public void writeExternal(ObjectOutput arg0) throws IOException {
		arg0.writeObject(Name);		
		arg0.writeObject(Adress);
		arg0.writeInt(phone_number);		
	}
}
