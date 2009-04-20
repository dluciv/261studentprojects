/**
 * @author Renat Akhmedyanov
 *
 * Arithmetic Coding for Data Compression
 * Paul G Howard, Jeffrey Scott Vitter
 * www.cs.duke.edu/~jsv/Papers/HoV94.arithmetic_coding.pdf
 */

package ArithmeticCoding;

public class Main {
	public static final int EOF = 256;
	
	public static void main(String[] args) throws Exception {
		new Pack("text", "packed");
	}
}