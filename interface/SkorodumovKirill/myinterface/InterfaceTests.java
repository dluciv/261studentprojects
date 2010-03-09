package myinterface;

import org.junit.Assert;
import org.junit.Test;

public class InterfaceTests {
	
	@Test
	public void humanTest()
	{
		IColouredCreature cr = new GreenHuman("Коля");
		Assert.assertEquals(cr.getName(),"Коля");
		Assert.assertEquals(cr.getColour(),"green");
	}
	
	@Test
	public void elephantTest()
	{
		IColouredCreature cr = new PinkElephant("Слоник");
		Assert.assertEquals(cr.getName(),"Слоник");
		Assert.assertEquals(cr.getColour(),"pink");
	}

}
