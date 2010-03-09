/*
 * (c) Stefan Bojarovski 2009
 * */
 
package cloud;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;
import static org.junit.Assert.*;

public class CloudTest {

    private Mockery context;
    
    private void testCloud(final boolean isShin, final int wind, final DaylightEnumType daylightType,
    						final CreatureEnumType creatureType, final CarrierEnumType carrierType ){
    	
    	context = new Mockery();
    	
    	final IWeather mockWeather = context.mock(IWeather.class);
        final ILuminary mockLum = context.mock(ILuminary.class);
        final IWind mockWind = context.mock(IWind.class);
        final IDaylight mockDayLight = context.mock(IDaylight.class);
        
        final IMagic mockMagic = context.mock(IMagic.class);
        
        final Carrier mockCarier = new Carrier(carrierType);
        
     // Carrier

        if (carrierType == CarrierEnumType.Deamon) {
            context.checking(new Expectations() {

                {
                    oneOf(mockMagic).callDeamon();
                    will(returnValue(mockCarier));
                    never(mockMagic).callStork();
                    will(returnValue(null));
                }
            });
        } else {
            context.checking(new Expectations() {

                {
                    oneOf(mockMagic).callStork();
                    will(returnValue(mockCarier));
                    never(mockMagic).callDeamon();
                    will(returnValue(null));
                }
            });
        }

        // Weather

        context.checking(new Expectations() {

            {
                allowing(mockLum).isShiny();
                will(returnValue(isShin));
                allowing(mockWind).getWindSpeed();
                will(returnValue(wind));
                allowing(mockDayLight).current();
                will(returnValue(daylightType));
            }
        });


        context.checking(new Expectations() {

            {
                allowing(mockWeather).getDaylight();
                will(returnValue(mockDayLight));
                allowing(mockWeather).getLuminary();
                will(returnValue(mockLum));
                allowing(mockWeather).getWind();
                will(returnValue(mockWind));
            }
        });
        
        Cloud cloud = new Cloud(mockMagic, mockWeather);
        Carrier carrier = cloud.create();

        context.assertIsSatisfied();

        assertEquals(carrier.getCarrierType(), carrierType);

        assertEquals(carrier.getCreatureType(), creatureType);
    }
    
    @Test
    public void TestCloud() {
        for (CreatureTable.CreatureTableRow row : CreatureTable.Table) {
            for (int i = row.windStart; i <= row.windEnd; i++) {
                testCloud(row.isShinig, i, row.daylight, row.creature, row.carrier);
            }
        }
    }
}
