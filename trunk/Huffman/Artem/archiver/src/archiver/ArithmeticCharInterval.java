package archiver;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
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
	public void scaleup(){
		a = Float.valueOf("0."+String.valueOf(a).substring(3));
		b = Float.valueOf("0."+String.valueOf(b).substring(3));
	}

	public boolean isEmpty(){
		if(a==0 && b==0)
			return true;
		return false;
	}
}
