/*
 * Savenko Maria (c)2009
 */

package msavenko;

import org.junit.Test;

public class ExchangeRateConverterTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void testExchange() {
        ExchangeRateConverter.Exchange("fg");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testExchange2() {
        ExchangeRateConverter.Exchange(null);
    }
    
}
