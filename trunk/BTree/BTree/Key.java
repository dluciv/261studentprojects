package BTree;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Arrays;

public class Key implements Comparable, Externalizable {
	String Name;
	String Adress;	
	int phone_number;
	Key next;	

	public Key() {
		
	}
	public Key(String name, String adr, int num) {
		Name = name;
		Adress = adr;
		phone_number = num;
	}
	
	public int compareTo(Object arg0) {
		return phone_number - ((Key)arg0).phone_number;
	}
	public boolean equals(Object o) {		
		return compareTo(o) == 0;		
	}
	public String toString() {
		return "[ " + Name + ", " + Adress + ", " + phone_number + "]";
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
