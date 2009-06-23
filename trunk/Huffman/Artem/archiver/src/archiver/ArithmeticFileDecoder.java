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

	public String cutedFloatStringBuffer = "";
	public ArithmeticCharInterval getScaledInterval( ArithmeticCharInterval sourceinterval, String num){
		cutedFloatStringBuffer = num;
		//int localfactor = factor;
		int localfactor = 10;
		int a = (int) (sourceinterval.a * localfactor);
		int b = (int) (sourceinterval.b * localfactor);
		while( 0 == ( a - b ) ){
			cutedFloatStringBuffer = cutedFloatStringBuffer.substring(1);
			sourceinterval.scale(a, factor);
			a = (int) (sourceinterval.a * localfactor);
			b = (int) (sourceinterval.b * localfactor);
		}
		cutedFloatStringBuffer = "0." + cutedFloatStringBuffer;
		return sourceinterval;
	}

	public float getNewF(FileInput fis,int readlen) throws IOException{
		// дополняем число до readlen знаков после запятой
		byte[] buf;
		if( cutedFloatStringBuffer.length() < readlen+2 ){
			buf = fis.read( (readlen+2) - cutedFloatStringBuffer.length() );
			return Float.valueOf( cutedFloatStringBuffer+getStringFromBytes(buf) );
		}
		else return Float.valueOf( cutedFloatStringBuffer );
	}
	
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
		//printChars();
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
		// скорость потокового чтения из файла
		int readlen = 8;

		// получаем первый кусок кодированного числа
		byte[] buf = fis.read(readlen);
		float f = Float.valueOf("0."+getStringFromBytes(buf));
		// открываем файл на запись в потоке
		FileOutput fos = new FileOutput(fosName);
		// получаем первый промежуток для текущего числа
		String res = "";
		ArithmeticCharInterval curinterval = new ArithmeticCharInterval();
		for (ArithmeticChar ch : chars) {
			if(f > (float)ch.bound_low / factor && f < (float)ch.bound_high / factor){
				curinterval.a = (float)ch.bound_low / factor;
				curinterval.b = (float)ch.bound_high / factor;
				curinterval.print((char)ch.key+"");
				//curinterval = getScaledInterval(curinterval);
				res += (char)ch.key;
				fos.write(res);
				res = "";
				break;
			}
		}
		
		// продолжаем считывать данные, пересчитывая промежуток и число пока не еоф
		while(fis.lenLeft() > 0){
			// подбираем промежуток из таблицы, в который входит число
			for (ArithmeticChar ch : chars) {
				ArithmeticCharInterval tinterval = getNextInterval((byte)ch.key, curinterval);
				if(f > tinterval.a && f < tinterval.b){
					System.out.println(f);
					curinterval = tinterval;
					curinterval = getScaledInterval(curinterval,String.valueOf(f).substring(2));
					curinterval.print((char)ch.key+"");
					res += (char)ch.key;
					fos.write(res);
					res = "";
					break;
				}
			}
			// дополняем число до readlen знаков после запятой
			f = getNewF(fis, readlen);
			
		}
		System.out.println(res);
		fis.flush();
		fos.flush();
	}

	public static void main(String[] args) throws IOException {
		
		String fn1 = "D:/.huf/g.a";
		String fn2 = "D:/.huf/g.a.coded";
		
		ArithmeticFileDecoder d = new ArithmeticFileDecoder();
		d.decodeData(fn2, fn1);
		
	}
}
