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
		BitFileInput input;
		BitFileOutput output;
		
		switch (action.getAction())
		{
			case CommandLineParser.PACK:
				input = new BitFileInput(action.getInput());
				output = new BitFileOutput(action.getOutput());
			
				HuffmanPack hp = new HuffmanPack();
				
				byte[] bytes = new byte[1];
				int length = 0;
				
				try
				{
					while (true)
						hp.add(input.readInt(8));
				}
				catch (BitFileException bfe) {}
				
				//huffman.out();
				hp.buildTree();
				//huffman.outTree();
				hp.constructCode();
				//huffman.outCode();
				
				input.close();
				input = new BitFileInput(action.getInput()); 
				
				hp.writeInfo(output);
				
				try
				{
					while (true)
						output.writeBits(hp.getCode(input.readInt(8)));
				}
				catch (BitFileException bfe) {}
				
				input.flush();
				input.close();
				output.flush();
				output.close();
			break;
			
			case CommandLineParser.UNPACK:
				input = new BitFileInput(action.getInput());
				output = new BitFileOutput(action.getOutput());
			
				int letters = input.readInt(8);
				
				String treeString = input.readBits((letters-1) * 4);
				String alphabet = input.readString(letters);
				input.flush();
				int size = input.readInt(32);
				
				HuffmanUnpack hu = new HuffmanUnpack(treeString, alphabet, size);
				
				try
				{
					while (true) {
						output.writeString(hu.getSymbol(input.readBits(1).charAt(0)));
					}
				}
				catch (BitFileException bfi) {}
				
				input.flush();
				input.close();
				output.flush();
				output.close();
			break;
		}
	}
}