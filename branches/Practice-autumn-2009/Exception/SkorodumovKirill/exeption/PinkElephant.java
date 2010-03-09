//by Skorodumov Kirill gr: 261


package exeption;

public class PinkElephant implements IColouredCreature{
	private static String name = "elephant";
	private static String COLOUR = "pink";
	
	public String getColour() {
		return COLOUR;
	}
	
	public String getName() {
		return name;
	}
	
	public PinkElephant()
	{
	}
}
