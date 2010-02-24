/*
 * (c) Stefan Bojarovski 2009
 * */
package cloud;

import java.util.Random;

public class Luminary implements ILuminary{
	
	private static final Random rand = new Random();
	
	public boolean isShiny(){
		return rand.nextBoolean();
	}
}
