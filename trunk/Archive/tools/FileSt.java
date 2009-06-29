package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileSt  {
	String file;
	FileInputStream fis;
	public FileSt(String st) throws FileNotFoundException {
		file = st;
		fis = new FileInputStream(st);		
	}
	public void refresh() throws IOException {
		fis.close();
		fis = new FileInputStream(file);
	}
	public int available() throws IOException {
		return fis.available();
	}
	public int read(byte[] b) throws IOException {
		return fis.read(b);
	}
	public void close() throws IOException {
		fis.close();
	}
}
