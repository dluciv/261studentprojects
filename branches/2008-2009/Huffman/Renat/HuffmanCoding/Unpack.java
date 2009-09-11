package HuffmanCoding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Unpack
{
	Unpack(String input_filename, String output_filename) throws IOException, TreeConstructException, WrongFileFormatException
	{
		FileInputStream input = new FileInputStream(input_filename);
		FileOutputStream output = new FileOutputStream(output_filename);
		
		// размер алфавита
		int alphabet_size = input.read() + 1;
		if (alphabet_size == 0)
		{
			output.flush();
			output.close();
			return;
		}
		//System.out.println("Alphabet size: "+alphabet_size);
		
		// длина распакованного файла
		int length = readLength(input);
		//System.out.println("Output file length: "+length);
		
		// читаем дерево
		int tree_size_bytes = (int) Math.ceil((double) (alphabet_size - 1) / 2);
		int[] tree_bytes = new int[tree_size_bytes];
		for (int i=0; i<tree_size_bytes; i++)
			tree_bytes[i] = input.read();
		
		// читаем алфавит
		int[] alphabet = new int[alphabet_size];
		for (int i=0; i<alphabet_size; i++)
			alphabet[i] = input.read();
		
		// количество битов закодированного дерева
		int tree_size_bits = alphabet_size == 2 ? 4 : (alphabet_size - 1) * 4;
		
		// строим дерево
		Tree head = buildTree(tree_bytes, alphabet, tree_size_bits);
		//System.out.println(head);
		
		// текущий символ
		int symbol;
		
		// текущее положение в дереве
		Tree current = head;
		
		while ((symbol = input.read()) != -1)
		{
			for (int i=0; i<8; i++)
			{
				boolean bit = symbol >> (7-i) == 1;
				symbol -= bit ? 1 << (7-i) : 0;
				current = bit ? current.right() : current.left();
				if (current.isLeaf())
				{
					output.write(current.symbol());
					current = head;
					length--;
					if (length == 0)
						break;
				}
			}
		}
		
		if (length != 0)
			throw new WrongFileFormatException();
		
		output.flush();
		output.close();
		input.close();
	}
	
	private int readLength(FileInputStream input) throws IOException
	{
		int result = 0;
		for (int i=0; i<4; i++)
			result += input.read() << ((3-i)*8);
		return result;
	}
	
	private Tree buildTree(int[] bytes, int[] alphabet, int bits_count) throws TreeConstructException
	{
		Tree parent = new Tree();
		Tree current;
		int alphabet_index = 0;
		int cursor = 0;
		while (bits_count > 0)
		{
			int to = bits_count >= 8 ? 8 : bits_count;
			int b = bytes[cursor];
			for (int i=0; i<to; i++)
			{
				boolean bit = b >> (7-i) == 1;
				b -= bit ? 1 << (7-i) : 0;
				
				if (!bit)
				{
					current = new Tree();
					current.parent(parent);
					if (parent.left() == null)
						parent.left(current);
					else if (parent.right() == null)
						parent.right(current);
					else
						throw new TreeConstructException();
					parent = current;
				}
				else
				{
					if (parent.left() == null && parent.right() == null)
					{
						parent.symbol(alphabet[alphabet_index]);
						alphabet_index++;
					}
					
					parent = parent.parent();
					if (parent == null)
						throw new TreeConstructException();
				}
			}
			bits_count -= 8;
			cursor++;
		}
		return parent;
	}
}

class TreeConstructException extends Exception {}
class WrongFileFormatException extends Exception {}