package mailchecker;

/* Program MailChecker
 * @author
 * Eugene Todoruk
 * group 261
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String input;
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        input = bReader.readLine();

        System.out.println("E-mail '" + input + "' is " + MailChecker.isEmail(input));
    }
}
