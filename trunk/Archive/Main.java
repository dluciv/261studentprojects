import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//  Archivier.
//	The first argument is a type of action, second - type of encoding,
//  first and fourth - names, may be with paths, of input and outputs files.

public class Main {
	public static void main(String[] arg) throws IOException {		
		if (arg.length == 4) {
			FileInputStream fin = new FileInputStream(arg[2]);
			byte[] inp = new byte[fin.available()];// read whole file
			fin.read(inp);
			fin.close();
			byte[] out;
			
			if (arg[0].equals("encode"))	{	
				if (arg[1].equals("arithmet")) {
					arithmet.Coder c = new arithmet.Coder();
					out = c.code(inp);
				} else  {
					haffman.Coder c = new haffman.Coder();
					out = c.code(inp);
				} 
			} else {
				if (arg[1].equals("arithmet")) {
					arithmet.Decoder c = new arithmet.Decoder();
					out = c.decode(inp);					
				} else {
					haffman.Decoder c = new haffman.Decoder();
					out = c.decode(inp);					
				}
			}			
			FileOutputStream fout = new FileOutputStream(arg[3]);
			fout.write(out);
			fout.close();			
		} else if (arg.length > 0) {
			System.err.println("Invalid arguments");
		}
		
	}
}
