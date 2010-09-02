/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

import java.util.Scanner;

/**
 *
 * @author kate
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Print a for quit");

        String exp = input.nextLine();

        while (!"a".equals(exp)) {
            Parser parser = new Parser(exp);
            try {
                System.out.println(parser.parse());
            } catch (ParseException ex) {
                System.out.println("Error! " + ex);
            }
            exp = input.nextLine();
        }
    }
}
