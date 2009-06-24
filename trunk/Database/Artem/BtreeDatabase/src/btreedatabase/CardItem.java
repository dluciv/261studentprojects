package btreedatabase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lapin Sergey
 */
public class CardItem implements BasicItem{
    public static final int FIELD_SIZE    = 64;
    public static final int CARD_SIZE     = FIELD_SIZE * 5 + 1;
    private static final byte NULL_SYMBOL = (byte)'_';

    private String name;
    private String name2;
    private String name3;
    private String addr;
    private String phone;
    private boolean isMale;

    public byte[] serialize() {
		byte[] out = new byte[CARD_SIZE];
		for (int i = 0; i < CARD_SIZE; i++) {
			out[i] = NULL_SYMBOL;
		}

		int cursor = 0;

		System.arraycopy(name.getBytes(), 0, out, cursor, name.length());
        cursor += FIELD_SIZE;
		System.arraycopy(name2.getBytes(), 0, out, cursor, name2.length());
        cursor += FIELD_SIZE;
		System.arraycopy(name3.getBytes(), 0, out, cursor, name3.length());
        cursor += FIELD_SIZE;
		System.arraycopy(addr.getBytes(), 0, out, cursor, addr.length());
        cursor += FIELD_SIZE;
		System.arraycopy(phone.getBytes(), 0, out, cursor, phone.length());
        cursor += FIELD_SIZE;

        if(isMale){
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

    private static String convertBytesToString(byte[] seri, int leftbound, int rightbound) {
		while (rightbound > leftbound && seri[rightbound-1] == NULL_SYMBOL) {
			rightbound--;
		}
		byte[] out = new byte[rightbound-leftbound];
		System.arraycopy(seri, leftbound, out, 0, rightbound-leftbound);
		return new String(out);
	}

        public void unserialize(byte[] data) {
            int cursor = 0;
            name = convertBytesToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            name2 = convertBytesToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            name3 = convertBytesToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            addr = convertBytesToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;
            phone = convertBytesToString(data, cursor, cursor + FIELD_SIZE);
            cursor += FIELD_SIZE;

            if(data[cursor] == 1){
                isMale = true;
            } else {
                isMale = false;
            }
    }

    public BasicItem Instance() {
        CardItem res;
        try {
            res = new CardItem("", "", "", "", "", true);
            return res;
        } catch (CreateCardException ex) {
            Logger.getLogger(CardItem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Comparator> AvaliableOrders() {
        ArrayList<Comparator> res = new ArrayList<Comparator>();
        res.add(Cards.nameComparator);
        res.add(Cards.name2Comparator);
        res.add(Cards.name3comparator);
        res.add(Cards.phoneComparator);
        res.add(Cards.addrComparator);
        res.add(Cards.ismaleComparator);
        return res;
    }

    class CreateCardException extends Exception {}

    public CardItem(String n, String s, String t, String a, String p, boolean sx) throws CreateCardException {
        if(n.length() > FIELD_SIZE || s.length() > FIELD_SIZE || t.length() > FIELD_SIZE 
         || a.length() > FIELD_SIZE || p.length() > FIELD_SIZE) {
            throw new CreateCardException(); 
        }
        name = n;
        name2 = s;
        name3 = t;
        addr = a;
        phone = p;
        isMale = sx;
    } 

    public String getName() {
        return name;
    }
    public void setName(String s) {
        this.name = s;
    }

    public String getName2() {
        return name2;
    }
    public void setName2(String s) {
        this.name2 = s;
    }

    public String getName3() {
        return name3;
	}
    public void setName3(String s) {
        this.name3 = s;
    }

    public String getAddr() {
        return addr;
    }
    public void setAddr(String s) {
        this.addr = s;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSex() {
        return isMale;
    }

    public void setSex(boolean sex) {
        this.isMale = sex;
    }
}
