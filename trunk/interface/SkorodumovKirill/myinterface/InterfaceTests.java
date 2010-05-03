package myinterface;

import org.junit.Assert;
import org.junit.Test;

public class InterfaceTests {
	
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
