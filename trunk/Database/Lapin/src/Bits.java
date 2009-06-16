

import java.util.ArrayList;

/**
 * Haffman coding.
 * User: Lapa
 * Date: 06.04.2009
 * Time: 2:42:10
 */
public class Bits
{
    public ArrayList<Boolean> _bits;
    public ArrayList<Byte> str;
    public Byte cursor;
    private Boolean isForsed;
    
    public Bits() {
        cursor = 0;
        isForsed = false;
        str = new ArrayList<Byte>();
        _bits = new ArrayList<Boolean>();
    }

    public Bits(ArrayList<Byte> s, ArrayList<Boolean> bits, Byte cur, Boolean isFrsd)
    {
        str = new ArrayList<Byte>();
        str = s;
        _bits = new ArrayList<Boolean>();
        _bits = bits;
        cursor = 0;
        cursor = cur;
        isForsed = false;
        isForsed = isFrsd;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() != Bits.class)
            return false;

        Bits res = (Bits)o;
        if(res._bits.equals(_bits) && res.cursor.equals(cursor) && res.str.equals(str) && res.isForsed == isForsed){
            return true;
        }
        return false;
    }   

    @Override
    public Object clone()
    {
        Bits res = new Bits();
        res.str.addAll(str);
        res._bits.addAll(_bits);
        res.cursor = cursor;
        res.isForsed = isForsed;
        return res;
    }

    public void MakeNormalBitString()
    {
        ArrayList<Boolean> res = new ArrayList<Boolean>();

        for(Byte tmp : str){
            res.addAll(Bits.DecodeExternalByte(tmp));
        }
        str.clear();
        res.addAll(_bits);
        _bits.clear();
        _bits = res;
    }

    public void Enqueue(Bits a)
    {
        if(a == null){
            return;
        }
        for(Byte tmp : a.str) {
            AddByte(tmp);
        }
        for(int i = 0; i < a.cursor; i++) {
            AddBit(a._bits.get(i));
        }
        NaturalFormByte();
    }

    public int length()
    {
        if(isForsed){
            return str.size() * 7 + cursor;
        }
        return str.size() * 8 + cursor;
    }

    private boolean isBitsFree()
    {
        return cursor == 0;
    }

    private boolean isBitsFull()
    {
        return cursor == 8;
    }

    private void ClearBits()
    {
        cursor = 0;
        _bits.clear();
    }

    public void ForseFormByte()
    {
        FormByte();
        ClearBits();
        isForsed = true;
    }

    private void FormByte()
    {
        int tmp = 1 << 7;
        byte res = 0;

        for (Boolean _bit : _bits) {
            if (_bit) {
                res += tmp;
            }
            tmp >>= 1;
        }
        Byte last = res;
        str.add(last);
        
    }

    public void NaturalFormByte()
    {
        if(!isBitsFull()){
            return;
        }
        FormByte();
        ClearBits();
    }

    public static boolean GetBit(byte a, int index)
    {
        while(index-- != 0)
        {
            a >>= 1;
        }

        return (a & 1) != 0;
    }

    public static ArrayList<Boolean> DecodeExternalByte(Byte last)
    {
        int tmpr = 1 << 7;
        byte tmp = last;
        ArrayList<Boolean> res = new ArrayList<Boolean>();

        for(int i = 0; i < 8; i++){
            res.add((tmp & tmpr) == tmpr);
            tmpr >>= 1;
        }
        return res;
    }

    public static byte[] Transfom(ArrayList<Byte> tmp)
    {
        byte[] res = new byte[tmp.size()];
        int i = 0;
        for(Byte tmpr : tmp){
            res[i++] = tmpr.byteValue();
        }
        return res;
    }

    public boolean DecodeInnerByte()
    {
        if(!isBitsFree() || str.size() == 0)
            return false;

        Byte last = str.get(str.size() - 1);
        _bits = DecodeExternalByte(last);
        return true;
    }

    public void AddBit(boolean tmp)
    {
        NaturalFormByte();
       
        Boolean tmpr = new Boolean(tmp);
        _bits.add(tmpr);
        Byte ncursor = new Byte((byte)(cursor + (byte)1));
        cursor = ncursor;
    }

    public void AddByte(Byte exByte)
    {
        if(isBitsFree()){
            str.add(exByte);
            return;
        }
        ArrayList<Boolean> res;
        res = DecodeExternalByte(exByte);
        for(int i = 0; i < 8; i++){
            AddBit(res.get(i));
        }
    }

    public ArrayList<Byte> GetByteStr()
    {
        ArrayList<Byte> res = (ArrayList<Byte>) str.clone();     
        ForseFormByte();
        res.addAll(str);
        return res;
    }

    public ArrayList<Boolean> GetBitStr()
    {
        ArrayList<Boolean> res = new ArrayList<Boolean>();
        for(Byte tmp : str){
            res.addAll(DecodeExternalByte(tmp));
        }

        res.addAll(_bits);

        return res;
    }

    public ArrayList<Byte> toByteString()
    {
        if(!isBitsFree()){
            ForseFormByte();
        }
        ArrayList<Byte> res = new ArrayList<Byte>();
        for(Byte tmp : str) {
            res.add(tmp);
        }
        return res;
    }

    public static int bitsToInt(boolean[] bits, int cursor) {
		int out = 0;
		for (int i = 0; i < cursor; i++) {
			if (bits[i]) {
				out += 1 << (bits.length - i - 1);
			}
		}
		return out;
	}

    public static byte[] intToByteArray(int value) {
        return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value
        };
    }

    public static int byteArrayToInt(byte[] b) {
        return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
    }

}