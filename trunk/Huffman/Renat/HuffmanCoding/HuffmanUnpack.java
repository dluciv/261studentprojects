/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

import java.io.IOException;
import java.io.FileOutputStream;

public class HuffmanUnpack
{
	private HuffmanTree tree;
	private HuffmanTree cur;
	private int size;
	
	public HuffmanUnpack(String treeString, String alphabet, int size) throws HuffmanTreeException
	{
		this.tree = new HuffmanTree(treeString, alphabet);
		this.size = size;
		this.cur = this.tree;
		
		//System.out.println(this.tree.outTree());
	}
	
	public boolean add(char c, FileOutputStream fos) throws HuffmanUnpackException, IOException
	{
		if (c == '0')
		{
			if (this.cur.getLeft() == null)
				throw new HuffmanUnpackException();
			else
				this.cur = this.cur.getLeft();
		}
		else if (c == '1')
		{
			if (this.cur.getRight() == null)
				throw new HuffmanUnpackException();
			else
				this.cur = this.cur.getRight();
		}
		else
			throw new HuffmanUnpackException();
		
		if (this.cur.getChar() != -1)
		{
			fos.write((char) this.cur.getChar());
			this.cur = this.tree;
			this.size--;
		}
		
		return this.size > 0;
	}
}

class HuffmanUnpackException extends Exception {}