//Lebedev Dmitry g261 2009 (c)
package tests;

import temperatureconverter.TemperatureConverter;
import org.junit.*;
import org.junit.Assert;

public class ConverterTests {

    final Double TEMPERATURE_ONE = -273.2;
    final Double TEMPERATURE_TWO = -459.7;
    final String stream = "271,33";
    Double temperature = 36.6;

    @Test
    public void rightConversionTest() {

        Assert.assertEquals(temperature,
                TemperatureConverter.convertCelsiusToFahrenheit(TemperatureConverter.convertFahrenheitToCelsius(temperature)),
                0.01);

    }

    @Test(expected = IllegalArgumentException.class)
    public void firstMinimumTemperatureConversion() {
        TemperatureConverter.convertCelsiusToFahrenheit(TEMPERATURE_ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void secondMinimumTemperatureConversion() {
        TemperatureConverter.convertFahrenheitToCelsius(TEMPERATURE_TWO);
    }

    @Test(expected = NumberFormatException.class)
    public void firstNumberFormatTest() {
        TemperatureConverter.convertFahrenheitToCelsius(Double.valueOf(stream));
    }

    @Test(expected = NumberFormatException.class)
    public void sexondNumberFormatTest() {
        TemperatureConverter.convertFahrenheitToCelsius(Double.valueOf(stream));
    }
}
