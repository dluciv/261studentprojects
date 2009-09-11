package HuffmanCoding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pack
{
	Pack(String input_filename, String output_filename) throws IOException
	{
		FileInputStream input = new FileInputStream(input_filename);
		FileOutputStream output = new FileOutputStream(output_filename);
		
		// считаем, сколько раз каждый символ встречается в файле
		Tree[] weights = new Tree[256];
		// длина файла
		int length = 0;
		// количество разных символов в файле
		int alphabet_size = 0;
		// текущий символ
		int symbol;
		while ((symbol = input.read()) != -1)
		{
			if (weights[symbol] == null)
			{
				weights[symbol] = new Tree(symbol);
				alphabet_size++;
			}
			else
				weights[symbol].inc();
			length++;
		}
		input.close();
		
		if (length == 0)
		{
			output.flush();
			output.close();
			return;
		}
		
		// [hack] иначе дерево не строится
		if (alphabet_size == 1)
		{
			int free_index = 0;
			if (weights[free_index] != null)
				free_index = 1;
			weights[free_index] = new Tree(free_index);
			alphabet_size++;
		}
		//printWeights(weights);
		
		// строим дерево
		Tree head = growTree(weights);
		//System.out.println("Tree:");
		//System.out.println(head);
		
		// запоминаем кодирование каждого символа
		Bits[] code = getCode(head);
		//printCode(code);
		
		// записываем длину алфавита (alphabet_size-1) [1 байт]
		output.write(alphabet_size - 1);
		
		// записываем длину файла [4 байта]
		output.write(length >> 24);
		length -= (length >> 24) * (1 << 24);
		output.write(length >> 16);
		length -= (length >> 16) * (1 << 16);
		output.write(length >> 8);
		length -= (length >> 8) * (1 << 8);
		output.write(length);
		
		// записываем дерево [(alphabet_size-1)/2 байт]
		Bits tree_bits = head.toBits();
		//System.out.println("Tree bits:");
		//System.out.println(tree_bits);
		while (tree_bits.length() > 0)
			output.write(tree_bits.dequeue());
		
		// записываем алфавит [alphabet_size байт]
		int[] alphabet_to_write = head.alphabet();
		for (int i=0; i<alphabet_size; i++)
			output.write(alphabet_to_write[i]);
		
		// записываем закодированный файл
		input = new FileInputStream(input_filename);
		Bits queue = new Bits();
		int kk = 0;
		while ((symbol = input.read()) != -1)
		{
			queue.enqueue(code[symbol]);
			while (queue.length() > 8)
				output.write(queue.dequeue());
			kk++;
		}
		if (queue.length() > 0)
			output.write(queue.dequeue());
		
		output.flush();
		output.close();
		input.close();
	}
	
	private void printWeights(Tree[] weights)
	{
		System.out.println("Weights:");
		for (int i=0; i<256; i++)
			if (weights[i] != null)
				System.out.print(weights[i]+"; ");
		System.out.println();
	}
	
	private Tree growTree(Tree[] weights)
	{
		while (true)
		{
			int[] min = getMinimalWeightIndexes(weights);
			
			if (min[1] == -1)
				return weights[min[0]];
			weights[min[0]] = new Tree(weights[min[0]], weights[min[1]]);
			weights[min[1]] = null;
		}
	}
	
	private int[] getMinimalWeightIndexes(Tree[] weights)
	{
		int[] min = new int[2];
		min[0] = -1;
		min[1] = -1;
		
		for (int i=0; i<256; i++)
			if (weights[i] != null)
			{
				if (min[0] == -1 || (weights[i].weight() < weights[min[0]].weight() && min[1] != -1))
					min[0] = i;
				else if (min[1] == -1 || weights[i].weight() < weights[min[1]].weight())
					min[1] = i;
			}
		
		return min;
	}
	
	private Bits[] getCode(Tree head)
	{
		Bits[] code = new Bits[256];
		
		Bits prefix = new Bits();
		
		getCode(head, prefix, code);
		
		return code;
	}
	
	private void getCode(Tree node, Bits prefix, Bits[] code)
	{
		if (node.isLeaf())
			code[node.symbol()] = prefix;
		else
		{
			getCode(node.left(), prefix.clone().add(false), code);
			getCode(node.right(), prefix.clone().add(true), code);
		}
	}
	
	private void printCode(Bits[] code)
	{
		System.out.println("Code:");
		for (int i=0; i<256; i++)
			if (code[i] != null)
				System.out.print((char)i+": "+code[i]+"; ");
		System.out.println();
	}
}
