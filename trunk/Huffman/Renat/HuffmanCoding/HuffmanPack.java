/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

import java.io.IOException;

public class HuffmanPack
{
	private HuffmanTree[] list = new HuffmanTree[256];
	private String[] code = new String[256];
	private HuffmanTree tree;
	private int size = 0;
	
	public HuffmanPack()
	{
	}
	
	public void add(int b)
	{
		this.size++;
		if (this.list[b] == null)
			this.list[b] = new HuffmanTree(b, 1);
		else
			this.list[b].incWeight();
	}
	
	public void buildTree()
	{
		while (true)
		{
			int[] min = this.getMinimalWeights();
			if (min[0] == -1 || min[1] == -1)
				break;
			this.tree = this.list[min[0]] = new HuffmanTree(this.list[min[0]], this.list[min[1]]);
			this.list[min[1]] = null;
		}
	}
	
	public int[] getMinimalWeights()
	{
		int min1 = -1;
		int min2 = -1;
		for (int i=0; i<=255; i++)
		{
			if (this.list[i] != null)
			{
				if (min1 == -1 || (this.list[i].getWeight() < this.list[min1].getWeight() && min2 != -1))
					min1 = i;
				else if (min2 == -1 || this.list[i].getWeight() < this.list[min2].getWeight())
					min2 = i;
			}
		}
		int[] min = new int[2];
		min[0] = min1;
		min[1] = min2;
		return min;
	}
	
	public void constructCode()
	{
		this.constructCodeHelper(this.tree, "");
	}
	
	public void constructCodeHelper(HuffmanTree tree, String prefix)
	{
		if (tree.getChar() == -1)
		{
			if (tree.getWeight() != -1)
			{
				this.constructCodeHelper(tree.getLeft(), prefix+"0");
				this.constructCodeHelper(tree.getRight(), prefix+"1");
			}
		}
		else
			this.code[tree.getChar()] = prefix;
	}
	
	public void writeInfo(BitFileOutput output) throws IOException, BitFileException
	{
		tree.write(output);
		output.flush();
		output.writeInt(this.size, 32); // 32 bits for size, max 4GB
	}
	
	public String getCode(int b) throws IOException, BitFileException
	{
		
		return this.code[b];
	}
	
	public void out()
	{
		for (HuffmanTree tree: this.list)
		{
			if (tree != null)
				System.out.println(tree.out());
		}
	}
	
	public void outTree()
	{
		System.out.println(this.tree.outTree());
	}
	
	public void outCode()
	{
		for (int i=0; i<=255; i++)
		{
			if (this.code[i] != null)
				System.out.println("'"+(char)i+"': "+this.code[i]);
		}
	}
}