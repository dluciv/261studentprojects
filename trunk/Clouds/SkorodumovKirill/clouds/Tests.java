//by Skorodumov Kirill gr: 261

package clouds;

import org.junit.*;
import org.jmock.Expectations;
import org.jmock.Mockery;

public class Tests 
{
	private static Mockery context = new Mockery();
	private static IDaylight daylight = context.mock(IDaylight.class);
	private static ILuminary luminary = context.mock(ILuminary.class);
	private static IWind wind = context.mock(IWind.class);
	
	@Test
	public void hedgehogTest()
	{
		context.checking(new Expectations() {
			oneOf (lu)
		});
	}
}
