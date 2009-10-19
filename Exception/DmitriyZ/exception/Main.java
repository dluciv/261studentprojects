//Dmitriy Zabranskiy g261 (c) 2009
//Exception
package exception;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String num1, num2;
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        num1 = bReader.readLine();
        num2 = bReader.readLine();

        System.out.println(FloatAdd.add(num1, num2));
    }
}
