/*
 * Luminary class randomly selects
 * if it is shining or not
 * Antonov Kirill 2009
 */

package clouds;

/**
 *
 * @author Tiesto
 */
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