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
public class ArithmeticCoder {
	public ArrayList<ArithmeticChar> chars = new ArrayList<ArithmeticChar>();
	public int factor = 10;
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

		float delta =  (float)sourceinterval.b - (float)sourceinterval.a;
		float step = 1;
		if(!sourceinterval.isEmpty())step = delta/factor;

		ArithmeticCharInterval resultinterval = new ArithmeticCharInterval();
		resultinterval.a = (float)getChar(ch).bound_low * step / factor + sourceinterval.a;
		resultinterval.b = (float)getChar(ch).bound_high * step / factor + sourceinterval.a;

		return resultinterval;
	}
	
	public ArithmeticCharInterval getScaledInterval( ArithmeticCharInterval sourceinterval ){
		encodedDataBuffer = "";
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
}
