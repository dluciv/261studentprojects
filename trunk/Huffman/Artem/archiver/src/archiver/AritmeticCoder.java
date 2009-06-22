/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Admin
 */
public class AritmeticCoder {
	public ArrayList<ArithmeticChar> chars = new ArrayList<ArithmeticChar>();
	public int factor = 100;
	public String encodedDataBuffer = "";
	
	public void printChars( ){
		System.out.println("chars:");
		ArithmeticChar.printic();

		for (int i = 0; i < chars.size(); i++) {
			ArithmeticChar arithmeticChar = chars.get(i);
			arithmeticChar.printiw();
		}
	}

	public void addChars( byte[] data ){
		for (int i=0; i<data.length; i++){
			Boolean found = false;
			for (ArithmeticChar ch : chars) {
				if(ch.key == data[i]){
					ch.weight++;
					found = true;
				}
			}
			if(!found){
				ArithmeticChar ch = new ArithmeticChar();
				ch.key = data[i];
				ch.weight = 1;
				chars.add(ch);
			}
		}
	}

	public void signChars(){
		int curSegmentLen = 0;
		Collections.sort(chars);
		for (ArithmeticChar ch : chars) {
			ch.bound_low = curSegmentLen;
			curSegmentLen += ch.weight;
			ch.bound_high = curSegmentLen;
		}
	}

	public ArithmeticChar getChar(int key){
		for (ArithmeticChar ch : chars) {
			if(ch.key == key)
				return ch;
		}
		return null;
	}

	public ArithmeticCharInterval getNextInterval( byte ch, ArithmeticCharInterval sourceinterval ){
		
		ArithmeticCharInterval resultinterval = new ArithmeticCharInterval();
		float delta =  (float)sourceinterval.b - (float)sourceinterval.a;
		float step = 1;
		if(!sourceinterval.isEmpty())step = delta/factor;

		resultinterval.a = (float)getChar(ch).bound_low * step / factor + sourceinterval.a;
		resultinterval.b = (float)getChar(ch).bound_high * step / factor + sourceinterval.a;
		
		return resultinterval;
	}
	
	public ArithmeticCharInterval getScaledInterval( ArithmeticCharInterval sourceinterval ){
		encodedDataBuffer = "";;
		int a = (int) (sourceinterval.a * factor);
		int b = (int) (sourceinterval.b * factor);
		while( 0 == ( a - b ) ){
			encodedDataBuffer += a;
			sourceinterval.scale(a, factor);
			a = (int) (sourceinterval.a * factor);
			b = (int) (sourceinterval.b * factor);
		}

		return sourceinterval;
	}

	public String encodeData(String fisName) throws IOException{
		
		FileInput fis = new FileInput(fisName);
		int readlen = 128;
		//создаем кодирующую таблицу символов
		byte[] buf = new byte[readlen];
		while(buf.length > 0){
			buf = fis.read(readlen);
			addChars(buf);
		}
		fis.flush();
		signChars();
		printChars();

		fis = new FileInput(fisName);
		buf = new byte[readlen];

		// производим начальное заполнение интервала
		buf = fis.read(1);
		ArithmeticCharInterval curinterval = new ArithmeticCharInterval();
		curinterval.a = (float)getChar(buf[0]).bound_low / factor;
		curinterval.b = (float)getChar(buf[0]).bound_high / factor;
		curinterval.print((char)buf[0]+"");

		// в потоке пересчитываем интервал для каждого символа
		buf = fis.read(readlen);
		String encodedData = "";
		while(buf.length>0){
			for (byte b : buf) {
				curinterval = getNextInterval(b, curinterval);
				curinterval = getScaledInterval(curinterval);
				curinterval.print((char)b+"");
				encodedData += encodedDataBuffer;
			}
			buf = fis.read(readlen);
		}
		fis.flush();
		// получаем конечное число
		float num = (float) (curinterval.a + (curinterval.b - curinterval.a) / 2);
		encodedData += (""+num).substring(2);
		return encodedData;
	}

	// ============= DECODER METHODS! =========================
	
	public float getFloatByChar(float f, char ch){

		String tf = f+"";
		int factor = tf.length()+2;
		int n;
			 if('1' == ch)n=1;
		else if('2' == ch)n=2;
		else if('3' == ch)n=3;
		else if('4' == ch)n=4;
		else if('5' == ch)n=5;
		else if('6' == ch)n=6;
		else if('7' == ch)n=7;
		else if('8' == ch)n=8;
		else if('9' == ch)n=9;
		else n=0;
			
		f += (float)n / factor;
		//f = (float) ((int) (f * factor)) / factor;
		return f;
	}


	public static void main(String[] args) throws IOException {
		AritmeticCoder a = new AritmeticCoder();
		String fisName = "D:/.huf/g.a";
		String data = a.encodeData(fisName);
		System.out.println("0."+data);




		System.out.println("============ DECODE SYMBOLS ============");
		int readdigits = 6;
		int i = 0;
		float f = 0;
		f = Float.valueOf("0."+data.substring(0,readdigits));
		System.out.println(f);

/*
		String tf = f+"";
		while(tf.length() < readdigits+2 && i < data.length()){
			f = a.getFloatByChar(f,data.charAt(i));
			i++;
			System.out.println(f);
		}

		/*
		String decodedData = "";
		// определим первый символ слова + задаем начально значение интервала
		ArithmeticCharInterval decodinginterval = new ArithmeticCharInterval();
		for (ArithmeticChar ch : a.chars) {
			if((float)ch.bound_low/a.factor < f && (float)ch.bound_high/a.factor> f){
				decodedData += (char)ch.key;
				decodinginterval.a = (float)ch.bound_low/a.factor;
				decodinginterval.b = (float)ch.bound_high/a.factor;
				decodinginterval.print((char)ch.key+": ");
				break;
			}
		}
		
		//for (i = 0; i <= data.length()/readdigits; i++) {
			t = "";
			if(data.length()<=(i+1)*readdigits)
				 t = data.substring(i*readdigits);
			else t = data.substring(i*readdigits, (i+1)*readdigits);
			f = a.getFloatByString(t);
			System.out.println(t+": "+f);
		//}

/*
		while(decodedData.charAt(decodedData.length()-1)!='!'){
			for (ArithmeticChar ch : a.chars) {
				ArithmeticCharInterval tinterval = a.getNextInterval((byte)ch.key, decodinginterval);
				if(tinterval.a < encodedData && tinterval.b > encodedData){
					decodinginterval = tinterval;
					decodedData += (char)ch.key;
					break;
				}
			}	
		}
		System.out.println(decodedData);
		//*/
        System.out.println("ok!");
	}
}
