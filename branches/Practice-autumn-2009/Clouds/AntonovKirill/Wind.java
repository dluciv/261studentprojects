/*
 * Wind class creates wind power randomly
 * Antonov Kirill 2009
 */
package clouds;


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
