package huffman;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputFile {

	private FileOutputStream fileStream;

	public OutputFile(String fileName) throws IOException
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
	public void write(int data) throws IOException
	{
		fileStream.write(data);
	}
	public void write(String data) throws IOException
	{
        for (int i = 0; i < data.length(); i++)
            write((byte)data.charAt(i));
	}

	public void clean() throws IOException
	{
		this.fileStream.flush();
		this.fileStream.close();
	}

}
