package cloud;

import org.junit.*;
import org.junit.runner.RunWith;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import cloud.IDaylight.DaylightType;
import cloud.ILuminary.LuminaryType;
import junit.framework.Assert;

import org.junit.Test;

@RunWith(JMock.class)
public class ICloudTest {
    Mockery context = new JUnit4Mockery();

WeatherFactoryMock weatherFactoryMock = new WeatherFactoryMock();

class WeatherFactoryMock implements IWeatherFactory {
        private LuminaryType luminary=LuminaryType.shining;
        private DaylightType daylight=DaylightType.Day ;
        private int wind =1;

        class LuminaryMock implements ILuminary {

            public LuminaryType isShining() {
                return luminary;
            }
        }
            class WindMock implements IWind {

                public int generateWindForm() {
                    return wind;
                }
            }

            class DaylightMock implements IDaylight {

                public DaylightType generateDaylightType() {
                    return daylight;
                }
            }

            public ILuminary getLuminary() {
                return new LuminaryMock();
            }

            public IWind getWind() {
                return new WindMock();
            }

            public IDaylight getDaylightType() {
                return new DaylightMock();
            }
        }

        @Test
        public void CloudBatTest() {
            IMagic magic = new Magic();
            Cloud cloud = new Cloud(magic,weatherFactoryMock);
            Assert.assertEquals(cloud.CallDeliverer().getCreatureType(), CreatureType.Bat);
        }
       @Test
       public void ttt(){
           final IMagic magic = context.mock(IMagic.class);
           final Cloud cloud = new Cloud(magic,weatherFactoryMock);
           context.checking(new Expectations() {
               {
                   oneOf(magic).callStork();
                   oneOf(magic).giveBaby();
               }
           });
           
           cloud.CallDeliverer();

       }
}
