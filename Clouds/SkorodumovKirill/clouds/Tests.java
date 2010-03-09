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
		IWeatherFactory factory = context.mock(IWeatherFactory.class);
		Cloud cloud = new Cloud(factory);
		
		context.checking(new Expectations() {
			{
				oneOf (luminary).isShining();
				/*will(returnValue(true));
				oneOf(wind).getSpeed();
				will(returnValue(0));
				oneOf(daylight).getDaylight();
				will(returnValue(DaylightType.Morning));*/
			}
			});
		
		//context.assertIsSatisfied();
			
		Assert.assertSame(CreatureType.Baloon, cloud.Create().getType());
	}
}
