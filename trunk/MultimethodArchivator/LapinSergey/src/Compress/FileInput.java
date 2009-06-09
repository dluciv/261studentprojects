package Compress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileInput {
	private DataInputStream fileStream;
	
	public FileInput(String fileName) throws IOException
	{
		File file = new File(fileName);
		if(file.exists()){
			FileInputStream filein = new FileInputStream(file);
            fileStream = new DataInputStream(filein);
		}
		else throw new IOException();
	}

    public void close() throws IOException {
        fileStream.close();
    }

	public byte readOneByte() throws IOException
	{
        byte res = read(1)[0];        
		return res;
		
	}

    public int read() throws IOException
	{
		return fileStream.read();
	}

    public int read(byte[] buf) throws IOException
	{
		return fileStream.read(buf);
	}

	public byte[] read(int len) throws IOException
	{
		if(len>fileStream.available())
			len = fileStream.available();

		byte[] buf = new byte[len];
		fileStream.read(buf,0, len);
		return buf;
	}

	public int lenLeft() throws IOException
	{
		return fileStream.available();
	}

	public void clean() throws IOException
	{
		this.fileStream.close();
	}
}
