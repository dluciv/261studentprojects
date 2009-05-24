package Arithm;

import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * @author Renat Akhmedyanov
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Arithm ar = new Arithm();
		ar.pack("films.table", "packed");
		ar.unpack("packed", "unpacked");
	}
}