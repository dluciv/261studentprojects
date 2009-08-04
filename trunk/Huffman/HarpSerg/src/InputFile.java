import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputFile {
	private InputStream fileStream;	
	
	public InputFile(String fileName) throws IOException
	{
		File file = new File(fileName);
		if(file.exists()){
			fileStream = new FileInputStream(file);
		}
		else throw new IOException();
	}	
	
	public byte[] read(int len) throws IOException
	{
		if(len>fileStream.available())
			len = fileStream.available();		
		byte[] buf = new byte[len];
		fileStream.read(buf,0, len);				
		return buf;
	}
	
	public void clean() throws IOException
	{
		this.fileStream.close();
	}
}
