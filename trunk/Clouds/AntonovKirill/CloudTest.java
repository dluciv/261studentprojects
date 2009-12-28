/*
 * (c) Antonov Kirill 2009
 */

package clouds;

import clouds.ICreature.CreatureType;
import clouds.IDaylight.DaylightType;
import org.junit.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;

/**
 *
 * @author Tiesto
 */
 class CloudTests {
	static Mockery context = new JUnit4Mockery();
	static IDaylight daylight = context.mock(IDaylight.class);
	static IWind wind = context.mock(IWind.class);
	static ILuminary luminary = context.mock(ILuminary.class);

	class MockFactory implements IWeatherFactory {

		@Override
		public IDaylight CreateDaylight() {
			return daylight;
		}

		@Override
		public ILuminary CreateLuminary() {
			return luminary;
		}

		@Override
		public IWind CreateWind() {
			return wind;
		}

	}
    //i?iaa?yai ?eaioiuo
	@Test
	public void CreateKitten() {
		IWeatherFactory factory = new MockFactory();
		ICloud cloud = new Cloud(factory);

		context.checking(new Expectations() {
			{
				oneOf(luminary).IsShining();
				will(returnValue(true));
				oneOf(wind).getWindPower();
				will(returnValue(0));
				oneOf(daylight).getDaylight();
				will(returnValue(DaylightType.Morning));
			}
		});

		Assert.assertSame(CreatureType.Kitten, cloud.Create()
				.getCreatureType());
	}

	@Test
    public void CreatePuppy() {
        IWeatherFactory factory = new MockFactory();
        ICloud cloud = new Cloud(factory);

        context.checking(new Expectations() {
            {
                oneOf(luminary).IsShining();
                will(returnValue(true));
                oneOf(wind).getWindPower();
                will(returnValue(0));
                oneOf(daylight).getDaylight();
                will(returnValue(DaylightType.Noon));
            }
        });

        Assert.assertSame(CreatureType.Puppy, cloud.Create()
                .getCreatureType());
    }

	@Test
    public void CreatePiglet() {
        IWeatherFactory factory = new MockFactory();
        ICloud cloud = new Cloud(factory);

        context.checking(new Expectations() {
            {
                oneOf(luminary).IsShining();
                will(returnValue(true));
                oneOf(wind).getWindPower();
                will(returnValue(6));
                oneOf(daylight).getDaylight();
                will(returnValue(DaylightType.Night));
            }
        });

        Assert.assertSame(CreatureType.Piglet, cloud.Create()
                .getCreatureType());
    }

	@Test
    public void CreateBearcub() {
        IWeatherFactory factory = new MockFactory();
        ICloud cloud = new Cloud(factory);

        context.checking(new Expectations() {
            {
                oneOf(luminary).IsShining();
                will(returnValue(true));
                oneOf(wind).getWindPower();
                will(returnValue(6));
                oneOf(daylight).getDaylight();
                will(returnValue(DaylightType.Evening));
            }
        });

        Assert.assertSame(CreatureType.Bearcub, cloud.Create()
                .getCreatureType());
    }

	@Test
    public void CreateBat() {
        IWeatherFactory factory = new MockFactory();
        ICloud cloud = new Cloud(factory);

        context.checking(new Expectations() {
            {
                oneOf(luminary).IsShining();
                will(returnValue(true));
                oneOf(wind).getWindPower();
                will(returnValue(10));
                oneOf(daylight).getDaylight();
                will(returnValue(DaylightType.Night));
            }
        });

        Assert.assertSame(CreatureType.Bat, cloud.Create()
                .getCreatureType());
    }

	@Test
    public void CreateBalloon() {
        IWeatherFactory factory = new MockFactory();
        ICloud cloud = new Cloud(factory);

        context.checking(new Expectations() {
            {
                oneOf(luminary).IsShining();
                will(returnValue(false));
                oneOf(wind).getWindPower();
                will(returnValue(5));
                oneOf(daylight).getDaylight();
                will(returnValue(DaylightType.Night));
            }
        });

        Assert.assertSame(CreatureType.Balloon, cloud.Create()
                .getCreatureType());
    }

	@Test
    public void CreateHedgehog() {
        IWeatherFactory factory = new MockFactory();
        ICloud cloud = new Cloud(factory);

        context.checking(new Expectations() {
            {
                oneOf(luminary).IsShining();
                will(returnValue(false));
                oneOf(wind).getWindPower();
                will(returnValue(8));
                oneOf(daylight).getDaylight();
                will(returnValue(DaylightType.Night));
            }
        });

        Assert.assertSame(CreatureType.Hedgehog, cloud.Create()
                .getCreatureType());
    }

}