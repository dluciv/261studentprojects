/*
 * (c) Stefan Bojarovski 2009
 * */
package cloud;

import java.util.Random;

public class Wind implements IWind{
	
	private static final int MAXWINDSPEED = 11;
	private static final Random rand = new Random();
	
	public int getWindSpeed(){
		return rand.nextInt(MAXWINDSPEED);
	}

}
