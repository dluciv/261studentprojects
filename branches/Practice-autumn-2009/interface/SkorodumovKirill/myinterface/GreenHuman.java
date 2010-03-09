//by Skorodumov Kirill gr: 261

package myinterface;

public class GreenHuman implements IColouredCreature{
	private static String COLOUR = "green";
	private String name;
	
	public String getColour() {
		return COLOUR;
	}
	
	public String getName() {
		return name;
	}
	
	public GreenHuman(String name)
	{
		this.name = name;
	}
}
