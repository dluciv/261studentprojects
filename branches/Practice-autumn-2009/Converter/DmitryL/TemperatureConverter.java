//Lebedev Dmitry g261 2009 (c)
package temperatureconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureConverter {

    public static double convertCelsiusToFahrenheit(double temperature) {
        return BigDecimal.valueOf(9 * temperature / 5 + 32).
                setScale(2, RoundingMode.UP).doubleValue();
    }

    public static double convertFahrenheitToCelsius(double temperature) {
        return BigDecimal.valueOf(5 * (temperature - 32) / 9).
                setScale(2, RoundingMode.DOWN).doubleValue();
    }
}

