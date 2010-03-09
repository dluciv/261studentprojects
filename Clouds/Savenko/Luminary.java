/*
 * Luminary class randomly selects
 * if it is shining or not
 * Savenko Maria ©2009
 */

package msavenko;

import java.util.Random;

public class Luminary implements ILuminary {

	private boolean IsShiny;

	@Override
	public boolean IsShining() {
		return IsShiny;
	}

	public Luminary() {
		Random random = new Random();
		IsShiny = random.nextBoolean();
	}

}
