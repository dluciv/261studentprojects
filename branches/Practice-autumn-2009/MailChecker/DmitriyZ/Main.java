/*
 * Mail Checker
 * Dmitriy Zabranskiy g261 (c)2009
 */
package mailchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String input;
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        input = bReader.readLine();
        System.out.println(MailChecker.isMail(input));
    }
}