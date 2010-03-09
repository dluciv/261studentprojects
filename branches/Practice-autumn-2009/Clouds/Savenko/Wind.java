/*
 * Wind class creates wind power randomly
 * Savenko Maria ©2009
 */

package msavenko;

import java.util.Random;

public class Wind implements IWind {

	private int windPower;

	public Wind() {
		Random random = new Random();
		windPower = random.nextInt(11);
	}

	@Override
	public int getWindPower() {
		return windPower;
	}
}
