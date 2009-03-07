/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

import java.io.IOException;
import java.io.FileOutputStream;

public class BitFileOutput
{
	private FileOutputStream file;
	private String buf = "";
	
	public BitFileOutput(String filename) throws IOException
	{
		this.file = new FileOutputStream(filename);
	}
	
	public void write(String bits) throws BitFileException, IOException
	{
		char b;
		for (int i=0; i<bits.length(); i++)
		{
			b = bits.charAt(i);
			
			if (b != '0' && b != '1')
			{
				throw new BitFileException("Wrong symbol"+i+": "+bits);
			}
			
			this.buf += b;
			
			while (this.buf.length() >= 8)
			{
				this.writeByte();
			}
		}
	}
	
	private void writeByte() throws IOException
	{
		int length = this.buf.length() >= 8 ? 8 : this.buf.length();
		int c = 0;
		
		for (int i=0; i<length; i++)
		{
			c += this.buf.charAt(i) == '1' ? Math.pow(2, 7-i) : 0;
		}
		
		this.file.write((char) c);
		
		this.buf = this.buf.substring(length);
	}
	
	public void writeString(String text) throws IOException
	{
		this.flush();
		for (int i=0; i<text.length(); i++)
		{
			this.file.write(text.charAt(i));
		}
	}
	
	public void writeInt(int a, int bits) throws BitFileException, IOException
	{
		if (a >= Math.pow(2, bits))
			throw new BitFileException("Number too big: "+a+"; must be less than "+Math.pow(2, bits));
		
		for (int i=0; i<bits; i++)
		{
			this.buf = (a % 2 == 1 ? "1" : "0") + this.buf;
			a = (int) (a / 2);
		}
		
		while (this.buf.length() >= 8)
			this.writeByte();
	}
	
	public void flush() throws IOException
	{
		if (this.buf.length() > 0)
			this.writeByte();
		this.file.flush();
	}
	
	public void close() throws IOException
	{
		this.file.close();
	}
}