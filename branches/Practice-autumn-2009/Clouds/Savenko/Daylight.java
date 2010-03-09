/*
 * Daylight class randomly creates time of day
 * from 4 possible variants
 * Savenko Maria ©2009
 */

package msavenko;

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
