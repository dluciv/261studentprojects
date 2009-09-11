package Regular;

/**
 *
 * @author Кирилл
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String getLine() {
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
        }
        return line;
    }

    public static char getChar() {
        int charCode = -1;

        try {
            charCode = System.in.read();
        } catch (IOException e) {
            System.out.println("Error : wrong expression\n");
            System.exit(0);
        }
        return (char) charCode;

    }
}
