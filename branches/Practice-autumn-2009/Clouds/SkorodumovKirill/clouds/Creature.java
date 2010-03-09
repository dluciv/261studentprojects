//by Skorodumov Kirill gr: 261

package clouds;

public class Creature {
	private CreatureType type;
	
	public Creature(CreatureType type)
	{
		this.type = type;
	}
	
	public CreatureType getType()
	{
		return type;
	}
}
