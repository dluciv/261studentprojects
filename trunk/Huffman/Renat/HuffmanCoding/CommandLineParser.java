/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

public class CommandLineParser
{
	private String[] args;
	
	public static final int NONE   = 1;
	public static final int PACK   = 2;
	public static final int UNPACK = 3;
	
	public CommandLineParser()
	{
	}
	
	public CommandLineAction parse(String[] args)
	{
		if (args.length == 3)
		{
			if (args[0].equals("-c"))
			{
				return new CommandLineAction(this.PACK, args[1], args[2]);
			}
			else if (args[0].equals("-x"))
			{
				return new CommandLineAction(this.UNPACK, args[1], args[2]);
			}
		}
		
		System.out.println("Huffman Coding");
		System.out.println("Options");
		System.out.println("  -c    pack file");
		System.out.println("  -x    unpack file");
		
		return new CommandLineAction(this.NONE);
	}
}

class CommandLineAction
{
	private int action;
	private String input;
	private String output;
	
	public CommandLineAction(int action)
	{
		this.action = action;
	}
	
	public CommandLineAction(int action, String input, String output)
	{
		this.action = action;
		this.input  = input;
		this.output = output;
	}
	
	public int getAction()
	{
		return this.action;
	}
	
	public String getInput()
	{
		return this.input;
	}
	
	public String getOutput()
	{
		return this.output;
	}
}