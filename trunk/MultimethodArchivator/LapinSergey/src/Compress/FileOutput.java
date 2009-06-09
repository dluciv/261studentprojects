package Compress;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutput {
	private DataOutputStream fileStream;

    public FileOutput(String fileName) throws IOException
	{
		File file = new File(fileName);
		if (file.exists())
			file.delete();

		if(file.createNewFile()) {
			FileOutputStream fileout = new FileOutputStream(file);
            fileStream = new DataOutputStream(fileout);
        }
	}

    public void close() throws IOException {
        fileStream.close();
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
