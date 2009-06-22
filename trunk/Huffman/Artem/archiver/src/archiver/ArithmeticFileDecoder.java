/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

import java.io.IOException;

/**
 *
 * @author Admin
 */
public class ArithmeticFileDecoder extends ArithmeticCoder {


	public void getCharsTable(FileInput fis) throws IOException{
		int readBytes = 2;
		byte[] codes = new byte[readBytes];
        codes = fis.read(readBytes);

        while(codes[0]!=0 && codes[1]!=0){
			ArithmeticChar ch = new ArithmeticChar();
			ch.key = codes[0];
			ch.weight = codes[1];
			chars.add(ch);
            codes = fis.read(readBytes);
        }
		signChars();
		printChars();
	}

	public String getStringFromBytes(byte[] buf){
		String str = "";
		for (byte b : buf)
			str += (char)b;
		return str;
	}

	public void decodeData(String fisName,String fosName) throws IOException{
		FileInput fis = new FileInput(fisName);
		// востанавливаем таблицу символов
		getCharsTable(fis);
		
		int readlen = 8;

		byte[] buf = fis.read(readlen);
		float f = Float.valueOf("0."+getStringFromBytes(buf));
		System.out.println(f);

		String res = "";
		ArithmeticCharInterval curinterval = new ArithmeticCharInterval();
		for (ArithmeticChar ch : chars) {
			if(f > (float)ch.bound_low / factor && f < (float)ch.bound_high / factor){
				curinterval.a = (float)ch.bound_low / factor;
				curinterval.b = (float)ch.bound_high / factor;
				curinterval.print((char)ch.key+"");
				//curinterval = getScaledInterval(curinterval);
				res += (char)ch.key;
				break;
			}

		}


		while(fis.lenLeft() > 0){
			// подбираем промежуток из таблицы, в который входит число
			for (ArithmeticChar ch : chars) {
				ArithmeticCharInterval tinterval = getNextInterval((byte)ch.key, curinterval);
				if(f > tinterval.a && f < tinterval.b){
					curinterval = tinterval;
					curinterval = getScaledInterval(curinterval);
					curinterval.print((char)ch.key+"");
					res += (char)ch.key;
					break;
				}
			}
			
			// дополняем число до readlen знаков после запятой
			if( String.valueOf(f).length() < readlen+2 ){
				buf = fis.read( (readlen+2) - String.valueOf(f).length() );
				f = Float.valueOf( String.valueOf(f)+getStringFromBytes(buf) );
				System.out.println(f);
			}
			System.out.println(res);
		}
		
		fis.flush();
	}

	public static void main(String[] args) throws IOException {
		ArithmeticFileDecoder d = new ArithmeticFileDecoder();
		String fn1 = "D:/.huf/g.a";
		String fn2 = "D:/.huf/g.a.coded";
		d.decodeData(fn2, fn1);
		
	}
}
