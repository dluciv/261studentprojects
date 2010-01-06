//by Skorodumov Kirill gr: 261

package myinterface;

public class GreenHuman implements IColouredCreature{
	private static String name = "human";
	private static String COLOUR = "green";
	
	public String getColour() {
		return COLOUR;
	}
	
	public String getName() {
		return name;
	}
	
	public GreenHuman()
	{
	}
}
