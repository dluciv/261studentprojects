import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lapin Sergey
 */
public class Card implements iRecord{
    public static final int FIELD_SIZE    = 50;
    public static final int CARD_SIZE     = FIELD_SIZE * 5 + 1;
    private static final byte NULL_SYMBOL = (byte)'_';    

    private String name;
    private String soname;
    private String third_name;
    private String address;
    private String phone;
    private boolean sex;

    public byte[] serialize() {
		byte[] out = new byte[CARD_SIZE];
		for (int i = 0; i < CARD_SIZE; i++) {
			out[i] = NULL_SYMBOL;
		}

		int cursor = 0;

		System.arraycopy(name.getBytes(), 0, out, cursor, name.length());
        cursor += FIELD_SIZE;
		System.arraycopy(soname.getBytes(), 0, out, cursor, soname.length());
        cursor += FIELD_SIZE;
		System.arraycopy(third_name.getBytes(), 0, out, cursor, third_name.length());
        cursor += FIELD_SIZE;
		System.arraycopy(address.getBytes(), 0, out, cursor, address.length());
        cursor += FIELD_SIZE;
		System.arraycopy(phone.getBytes(), 0, out, cursor, phone.length());
        cursor += FIELD_SIZE;

        if(sex){
            out[cursor] = 1;
        } else {
            out[cursor] = 0;
        }
        
		return out;
	}

    @Override
    public String toString() {
        return name;
    }



    public int getRecordSize() {
		return CARD_SIZE;
	}

    private static String fromByteArrayToString(byte[] seri, int leftbound, int rightbound) {
		while (rightbound > leftbound && seri[rightbound-1] == NULL_SYMBOL) {
			rightbound--;
		}

		byte[] out = new byte[rightbound-leftbound];
		System.arraycopy(seri, leftbound, out, 0, rightbound-leftbound);

		return new String(out);
	}

        public void unserialize(byte[] data) {
            int cursor = 0;

            name = fromByteArrayToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            soname = fromByteArrayToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            third_name = fromByteArrayToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            address = fromByteArrayToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            phone = fromByteArrayToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;

            if(data[cursor] == 1){
                sex = true;
            } else {
                sex = false;
            }
    }

    public iRecord Instance() {        
        Card res;
        try {
            res = new Card("", "", "", "", "", true);
            return res;
        } catch (CreateCardException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Comparator> AvaliableOrders() {
        ArrayList<Comparator> res = new ArrayList<Comparator>();
        res.add(CardSet.name_comparator);
        res.add(CardSet.soname_comparator);
        res.add(CardSet.third_namecomparator);
        res.add(CardSet.phone_comparator);
        res.add(CardSet.address_comparator);
        res.add(CardSet.sex_comparator);
        return res;
    }

    class CreateCardException extends Exception {
    }

    public Card(String n, String s, String t, String a, String p, boolean sx) throws CreateCardException {
        if(n.length() > FIELD_SIZE || s.length() > FIELD_SIZE || t.length() > FIELD_SIZE 
         || a.length() > FIELD_SIZE || p.length() > FIELD_SIZE) {
            throw new CreateCardException(); 
        }
        name = n;
        soname = s;
        third_name = t;
        address = a;
        phone = p;
        sex = sx;
    } 

        /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the soname
     */
    public String getSoname() {
        return soname;
    }

    /**
     * @param soname the soname to set
     */
    public void setSoname(String soname) {
        this.soname = soname;
    }

    /**
     * @return the third_name
     */
    public String getThird_name() {
        return third_name;
    }

    /**
     * @param third_name the third_name to set
     */
    public void setThird_name(String third_name) {
        this.third_name = third_name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

     /**
     * @return the sex
     */
    public boolean isSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
