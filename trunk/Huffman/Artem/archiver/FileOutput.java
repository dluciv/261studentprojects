package archiver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutput {

	private FileOutputStream fileStream;
	
	public FileOutput(String fileName) throws IOException
	{
		File file = new File(fileName);
		if (file.exists())
			file.delete();
			
		if(file.createNewFile())
			fileStream = new FileOutputStream(file);
	}
	
	public void write(byte[] data) throws IOException
	{
		fileStream.write(data);
	}
	public void write(byte data) throws IOException
	{
		fileStream.write(data);
	}
	
	
	public void clean() throws IOException
	{
		this.fileStream.flush();
		this.fileStream.close();
	}
	
}
