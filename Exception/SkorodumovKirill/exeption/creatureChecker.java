package exeption;

public class creatureChecker {
	public static String getCreatureName(IColouredCreature cr) throws IllegalArgumentException
	{
		if (cr == null)
		{
			throw new IllegalArgumentException("Invalid argument!");
		}
		
		return cr.getName();
	}
}
