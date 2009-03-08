/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

import java.io.IOException;

public class HuffmanTree
{
	private HuffmanTree left;
	private HuffmanTree right;
	private int b = -1;
	private int weight = -1;
	private String alphabet = "";
	private int mark = 0;
	
	public HuffmanTree(int b, int weight)
	{
		this.b = b;
		this.weight = weight;
	}
	
	public HuffmanTree(HuffmanTree left, HuffmanTree right)
	{
		this.weight = left.getWeight() + right.getWeight();
		this.left = left;
		this.right = right;
	}
	
	public HuffmanTree(String treeString, String alphabet) throws HuffmanTreeException
	{
		HuffmanTree[] parents = new HuffmanTree[256];
		int h = 0;
		parents[0] = this;
		HuffmanTree tree;
		for (int i=0; i<treeString.length(); i++)
		{
			char c = treeString.charAt(i);
			if (c == '0')
			{
				tree = new HuffmanTree(-1, 0);
				if (parents[h].getLeft() == null)
				{
					//System.out.println("left");
					parents[h].setLeft(tree);
				}
				else if (parents[h].getRight() == null)
				{
					//System.out.println("right");
					parents[h].setRight(tree);
				}
				else
					throw new HuffmanTreeException();
				
				h++;
				parents[h] = tree;
			}
			else
			{
				if (parents[h].getLeft() == null && parents[h].getRight() == null)
				{
					parents[h].setChar(alphabet.charAt(0));
					alphabet = alphabet.substring(1);
				}
				h--;
				if (h < 0)
					throw new HuffmanTreeException();
			}
		}
	}
	
	public void incWeight()
	{
		this.weight++;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
	
	public int getChar()
	{
		return this.b;
	}
	
	public void setChar(char b)
	{
		this.b = (int) b;
	}
	
	public void setLeft(HuffmanTree left)
	{
		this.left = left;
	}
	
	public void setRight(HuffmanTree right)
	{
		this.right = right;
	}
	
	public HuffmanTree getLeft()
	{
		return this.left;
	}
	
	public HuffmanTree getRight()
	{
		return this.right;
	}
	
	public void incMark()
	{
		this.mark++;
	}
	
	public int getMark()
	{
		return this.mark;
	}
	
	public String traverse()
	{
		this.alphabet = "";
		
		HuffmanTree[] parents = new HuffmanTree[256];
		int h = 0;
		int[] mark = new int[256];
		HuffmanTree cur;
		HuffmanTree left;
		HuffmanTree right;
		HuffmanTree parent;
		
		cur = this;
		
		String out = "";
		
		while (true)
		{
			left = cur.getLeft();
			right = cur.getRight();
			parent = h == 0 ? null : parents[h-1];
			
			if (left != null && left.getMark() < 2)
			{
				left.incMark();
				parents[h] = cur;
				h++;
				cur = left;
				out += "0";
				if (cur.getChar() != -1)
					this.alphabet += (char) (cur.getChar());
			}
			else if (right != null && right.getMark() < 2)
			{
				right.incMark();
				parents[h] = cur;
				h++;
				cur = right;
				out += "0";
				if (cur.getChar() != -1)
					this.alphabet += (char) (cur.getChar());
			}
			else if ((left == null && right == null) || (cur.getMark() < 2 && h > 0 && parents[h] != null))
			{
				cur.incMark();
				h--;
				cur = parents[h];
				out += "1";
			}
			else
			{
				return out;
			}
		}
	}
	
	public boolean eq(HuffmanTree tree)
	{
		return this.outTree() == tree.outTree();
	}
	
	public void write(BitFileOutput output) throws IOException, BitFileException
	{
		String traverse = this.traverse();
		output.writeInt((int) (traverse.length()/4) + 1, 8);
		output.writeBits(traverse);
		output.flush();
		output.writeString(this.alphabet);
	}
	
	public String out()
	{
		return "'"+(char)this.b+"': "+this.weight;
	}
	
	public String outTree()
	{
		if (this.b == -1)
			return "("+this.left.outTree()+", "+this.right.outTree()+"):"+this.mark;
		else
			return "'"+(char)this.b+"':"+this.mark;
	}
}

class HuffmanTreeException extends Exception {}