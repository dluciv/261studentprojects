/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

/**
 *
 * @author Admin
 */
public class ArithmeticCharInterval {
	public float a = 0;
	public float b = 0;

	public void print(){
		System.out.println("["+a+", "+b+"]");
	}
	public void print(String str){
		System.out.println(str+": ["+a+", "+b+"]");
	}
	public void scale(int c, int factor){
		a = a * factor - c;
		b = b * factor - c;
		if(a>=b)System.out.println("error: ArithmeticCharInterval.scale: a<=b");
	}

	public boolean isEmpty(){
		if(a==0 && b==0)
			return true;
		return false;
	}
}
