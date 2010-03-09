//by Skorodumov K. gr: 261

package converterwithgui;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTests {
	
	@Test
	public void firstSimpleTest()
	{
		Assert.assertEquals(Converter.toHex(10),"A");
	}
	
	@Test
	public void secondSimpleTest()
	{
		Assert.assertEquals(Converter.toHex(123),"7B");
	}
	
	@Test
	public void complexTest()
	{
		Assert.assertEquals(Converter.toHex(4132950), "3F1056");
	}

}
