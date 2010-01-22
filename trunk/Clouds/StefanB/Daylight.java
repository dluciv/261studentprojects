package cloud;

import java.util.Random;

public class Daylight implements IDaylight {
	
	private static final Random rand = new Random();
	
	public DaylightEnumType current(){
		return DaylightEnumType.values()[rand.nextInt(DaylightEnumType.values().length)];
	}
}
