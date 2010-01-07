package exeption;

import org.junit.Assert;
import org.junit.Test;


public class exeptionTests {
	@Test(expected = IllegalArgumentException.class)
	public void nullTest()
	{
		creatureChecker.getCreatureName(null);
	}
	
	@Test
	public void humanTest()
	{
		IColouredCreature cr = new GreenHuman();
		Assert.assertEquals(cr.getName(),"human");
		Assert.assertEquals(cr.getColour(),"green");
	}
	
	@Test
	public void elephantTest()
	{
		IColouredCreature cr = new PinkElephant();
		Assert.assertEquals(cr.getName(),"elephant");
		Assert.assertEquals(cr.getColour(),"pink");
	}
}
