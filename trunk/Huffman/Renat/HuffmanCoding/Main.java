package HuffmanCoding;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		if (args.length == 3)
		{
			if (args[0].equals("-c"))
				new Pack(args[1], args[2]);
			else if (args[0].equals("-x"))
				new Unpack(args[1], args[2]);
			else
				printHelp();
		}
		else
			printHelp();
	}
	
	public static void printHelp()
	{
		System.out.println("-c   pack file");
		System.out.println("-x   unpack file");
	}
}