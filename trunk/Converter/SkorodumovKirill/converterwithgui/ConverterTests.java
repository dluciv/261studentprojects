package converterwithgui;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTests {
	
	@Test
	public void firstSimpleTest()
	{
		long x = 10001001100l;
		Assert.assertEquals(Converter.toBinary(1100),x);
	}
	
	@Test
	public void secondSimpleTest()
	{
		long x = 1000010110;
		Assert.assertEquals(Converter.toBinary(534),x);
	}

}
