/*
 * Daylight class randomly creates time of day
 * from 4 possible variants
 * Antonov Kirill 2009
 */

package clouds;


import java.util.Random;

public class Daylight implements IDaylight {

	public DaylightType current;

	public Daylight() {
		Random random = new Random();
		current = DaylightType.values()[random.nextInt(DaylightType.values().length)];
	}

	@Override
	public DaylightType getDaylight() {
		return current;
	}

}
