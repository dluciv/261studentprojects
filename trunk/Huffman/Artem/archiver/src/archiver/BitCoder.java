/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

/**
 *
 * @author Admin
 */
public class BitCoder {
	public static byte convertBitsToByte(String bits){
		int value = 0;
		if(bits!=null)
		{
			for (int i = 0; i < bits.length(); i++)
			{
				if(bits.charAt(i) == '1')
				{
					value = value | (1 << i);
				}
			}
		}
		return (byte)value;
	}
    public static String converByteToBits(byte b) {
        boolean[] bits = new boolean[8];
        String str = "" ;
        for (int i = 0; i < bits.length; i++) {
            bits[i] = ((b & (1 << i)) != 0);
            if(bits[i])
				str += "1";
			else str += "0";
        }
        return str;
    }
    public static String converByteToBits(byte[] bytes) {
        String str = "" ;
		for (byte b : bytes) {
			str += converByteToBits(b);
		}
        return str;
    }
}
