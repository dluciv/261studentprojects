/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import hotheart.converter.Main;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stepan
 */
public class ConverterTest {

    @Test
    public void convertTest() {
        assertEquals(Main.convertIp(new int[]{192, 168, 0, 1}), 3232235521L);
        assertEquals(Main.convertIp(new int[]{127, 0, 0, 1}), 2130706433L);

        // Google.ru
        assertEquals(Main.convertIp(new int[]{209, 85, 229, 104}), 3512067432L);

        // yandex.ru
        assertEquals(Main.convertIp(new int[]{213, 180, 204, 8}), 3585395720L);

        // stepa.name
        assertEquals(Main.convertIp(new int[]{85, 21, 143, 138}), 1427476362L);
    }

    @Test
    public void parseTest() {
        assertArrayEquals(new int[]{192, 168, 0, 1}, Main.tryParse("192.168.0.1"));
        assertArrayEquals(new int[]{127, 0, 0, 1}, Main.tryParse("127.0.0.1"));

        assertArrayEquals(new int[]{209, 85, 229, 104}, Main.tryParse("209.85.229.104"));
        assertArrayEquals(new int[]{213, 180, 204, 8}, Main.tryParse("213.180.204.8"));
        assertArrayEquals(new int[]{85, 21, 143, 138}, Main.tryParse("85.21.143.138"));
    }

    @Test
    public void wrongParseTest() {
        String[] tests = new String[]{"192.168.0.0.0", "192.168.0.0.1", "192.168.0", "192.168.0.0.", ".168.0.0.0",
            "256.0.0.1", "asdas.13.sdf.2", "-1.0.0.1", "0.0.."};

        for (int i = 0; i < tests.length; i++) {
            try {
                Main.tryParse("192.168.0.1.1");
                assertTrue("Must throw exception!", false);
            } catch (IllegalArgumentException e) {
            }
        }
    }
}
