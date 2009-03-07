/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

import java.io.IOException;
import java.io.FileInputStream;

public class BitFileInput
{
	private FileInputStream file;
	private String buf = "";
	
	public BitFileInput(String filename) throws IOException
	{
		this.file = new FileInputStream(filename);
	}
	
	public String read(int bits) throws IOException
	{
		if (this.buf.length() < bits)
		{
			int toread = (int) Math.ceil((double)(bits - this.buf.length()) / 8);
			byte[] bytes = new byte[toread];
			this.file.read(bytes);
			int b, i, j;
			String s;
			for (i=0; i<bytes.length; i++)
			{
				b = bytes[i] < 0 ? bytes[i] + 256 : bytes[i];
				s = "";
				for (j=0; j<8; j++)
				{
					s = (b % 2 == 1 ? '1' : '0') + s;
					b = (int) (b / 2);
				}
				this.buf = this.buf + s;
			}
		}
		
		String out = this.buf.substring(0, bits);
		this.buf = this.buf.substring(bits);
		
		return out;
	}
	
	public int readInt(int bits) throws IOException
	{
		String b = this.read(bits);
		int out = 0;
		for (int i=0; i<bits; i++)
		{
			out += b.charAt(i) == '1' ? Math.pow(2, bits - 1 - i) : 0;
		}
		return out;
	}
	
	public String readString(int len) throws IOException
	{
		byte[] bytes = new byte[len];
		this.file.read(bytes, 0, len);
		int b;
		String out = "";
		for (int i=0; i<bytes.length; i++)
		{
			b = bytes[i] < 0 ? bytes[i] + 256 : bytes[i];
			out += (char) b;
		}
		return out;
	}
	
	public void flush() throws IOException
	{
		this.read(this.buf.length() % 8);
	}
	
	public void close() throws IOException
	{
		this.file.close();
	}
}