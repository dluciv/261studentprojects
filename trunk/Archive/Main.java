import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



import tools.FileSt;
import tools.Tools;


//  Archivier.
//	The first argument is a type of action, second - type of encoding,
//  first and fourth - names, may be with paths, of input and outputs files.



public class Main {	
	
	
	public static void main(String[] arg) throws IOException {
		
		if (arg.length == 4) {
			
			FileSt fin = new FileSt(arg[2]);
			FileOutputStream fot = new FileOutputStream(arg[3]);
			//System.out.println(fin.available());
			boolean crazyIvan = false;
			if (arg[0].equals("encode"))	{	
				if (arg[1].equals("arithmet")) {

					fin.close();
					Tools.reverse(arg[2], arg[2]+"$tmp");
					fin = new FileSt(arg[2]+"$tmp");					
					arithmet.Coder c = new arithmet.Coder();
					c.code(fin, fot);					
					crazyIvan = true;
				} else  {
					haffman.Coder c = new haffman.Coder();
					c.code(fin, fot);
				} 
			} else {
				if (arg[1].equals("arithmet")) {
					arithmet.Decoder c = new arithmet.Decoder();
					c.decode(fin,fot);					
				} else {
					haffman.Decoder c = new haffman.Decoder();
					c.decode(fin,fot);					
				}
			}
			
			fin.close();
			(new File(arg[2]+"$tmp")).delete();
			fot.close();
			if (crazyIvan) {
				Tools.reverse(arg[3], arg[3]+"$tmp");
				(new File(arg[3])).delete();
				(new File(arg[3] + "$tmp")).renameTo(new File(arg[3]));
			}
			return;
		} else if (arg.length > 0) {
			System.err.println("Invalid arguments");
			return;
		}
		
				
	}
}