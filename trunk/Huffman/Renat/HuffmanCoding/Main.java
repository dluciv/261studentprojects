/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		CommandLineParser parser = new CommandLineParser();
		CommandLineAction action = parser.parse(args);
		
		switch (action.getAction())
		{
			case CommandLineParser.PACK:
				HuffmanPack huffman = new HuffmanPack();
				
				FileInputStream input = new FileInputStream(action.getInput()); 
				
				byte[] bytes = new byte[1];
				int length = 0;
				
				while (length != -1)
				{
					if (length > 0)
					{
						huffman.add(bytes[0]);
					}
					
					length = input.read(bytes, 0, 1);
				}
				
				//huffman.out();
				huffman.buildTree();
				//huffman.outTree();
				huffman.constructCode();
				//huffman.outCode();
				
				input.close();
				input = new FileInputStream(action.getInput()); 
				
				BitFileOutput output = new BitFileOutput(action.getOutput());
				
				huffman.writeInfo(output);
				
				length = 0;
				String out;
				while (length != -1)
				{
					if (length > 0)
						huffman.process(bytes[0], output);
					
					length = input.read(bytes, 0, 1);
				}
				
				output.flush();
				
				input.close();
				output.flush();
				output.close();
			break;
			
			case CommandLineParser.UNPACK:
				BitFileInput bfi = new BitFileInput(action.getInput());
				FileOutputStream fos = new FileOutputStream(action.getOutput());
				
				int letters = bfi.readInt(8);
				
				String treeString = bfi.read((letters-1) * 4);
				String alphabet = bfi.readString(letters);
				bfi.flush();
				int size = bfi.readInt(32);
				
				HuffmanUnpack h = new HuffmanUnpack(treeString, alphabet, size);
				
				while (h.add(bfi.read(1).charAt(0), fos)) {}
				
				bfi.close();
				fos.close();
			break;
		}
	}
}