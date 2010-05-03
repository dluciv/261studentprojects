package spusk1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName ="src/spusk1/programm.txt";
        String TextProgramm=null;

        BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        //System.out.println("Enter a line:");
        while(stdin.ready()){
           TextProgramm+=stdin.readLine();
        }
        Parser parser = new Parser(new Lexer(TextProgramm));
        System.out.println("the result is:");
        Sequence sequence = parser.getSeq();
        sequence.Interpret();
        System.out.println();

    }
}
