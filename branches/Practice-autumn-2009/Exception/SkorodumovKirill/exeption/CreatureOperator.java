//by Skorodumov Kirill gr: 261

package exeption;

public class CreatureOperator 
{
	public static String getCreatureName(IColouredCreature cr) throws IllegalArgumentException
	{
		if (cr == null)
		{
			throw new IllegalArgumentException("Invalid argument!");
		}
		
		return cr.getName();
	}
	
	public static String getCreatureColour(IColouredCreature cr) throws IllegalArgumentException
	{
		if (cr == null)
		{
			throw new IllegalArgumentException("Invalid argument!");
		}
		
		return cr.getColour();
	}
	
	public static void showCreatureInfo(IColouredCreature cr)
	{
		try 
		{
			System.out.println(getCreatureColour(cr));
			System.out.println(getCreatureName(cr));
		} catch (IllegalArgumentException e) {System.out.println("Invalid argument");}
	}
}
