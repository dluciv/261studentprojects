//by Skorodumov Kirill gr: 261

package myinterface;

public class PinkElephant implements IColouredCreature{
	private static String COLOUR = "pink";
	private String name;
	
	public String getColour() {
		return COLOUR;
	}
	
	public String getName() {
		return name;
	}
	
	public PinkElephant(String name)
	{
		this.name = name;
	}
}
