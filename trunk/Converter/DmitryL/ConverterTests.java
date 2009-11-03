//Lebedev Dmitry g261 2009 (c)
package tests;

//import temperatureconverter.FrameConverter;
//import temperatureconverter.Main;
import temperatureconverter.TemperatureConverter;
import org.junit.*;
import org.junit.Assert;

public class ConverterTests {

    Double temperature = 36.6;
    @Test
    public void rightConversionTest() {
        
        Assert.assertEquals(temperature,
                            TemperatureConverter.
                                convertCelsiusToFahrenheit(TemperatureConverter.
                                    convertFahrenheitToCelsius(temperature)),
                            0.01);

    }
}
