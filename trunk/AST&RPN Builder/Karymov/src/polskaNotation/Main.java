/**
 *
 * @author Карымов Антон 261 группа
 */
package polskaNotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a line:");
        Parser parser = new Parser(new Lexer(stdin.readLine()));
        System.out.println("the result is:");
        parser.calculate().print();
        System.out.println();
    }
}
