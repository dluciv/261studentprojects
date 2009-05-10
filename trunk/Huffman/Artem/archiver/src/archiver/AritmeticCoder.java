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
	public long bound_low = 0;
	public long bound_high = 1;
	public long pow;

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
		bound_low  = 0;
		pow = bound_high = curSegmentLen;
	}

	public ArithmeticChar getChar(int key){
		for (ArithmeticChar ch : chars) {
			if(ch.key == key)
				return ch;
		}
		return null;
	}

	public void pack( byte[] data ){
		for (int i=0; i < data.length; i++){
			
			long range = bound_high - bound_low;
			if(0>range){
				System.out.println("err!");
				return;
			}
			long bound_low_t = bound_low;
			bound_low = bound_low_t*pow+range*getChar(data[i]).bound_low;
			bound_high = bound_low_t*pow+range*getChar(data[i]).bound_high;
			System.out.println("range"+range+" - - bound_high"+bound_high+" - - bound_low"+bound_low+" - - pow"+pow);
		}
	}

	public static void main(String[] args) throws IOException {

		AritmeticCoder a = new AritmeticCoder();
		String fisName = "D:/.huf/g.a";
		
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			a.addChars(buf);
		}
		fis.flush();

		a.signChars();
		a.printChars();



		fis = new FileInput(fisName);
		buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			a.pack(buf);
		}
		fis.flush();

		System.out.println("b_low  = " + a.bound_low);
		System.out.println("b_high = " + a.bound_high);

        System.out.println("ok!");



	}
}
