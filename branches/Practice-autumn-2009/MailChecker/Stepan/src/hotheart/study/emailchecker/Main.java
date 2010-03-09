/*
 * Main for Email checker by Korshakov Stepan
 */
package hotheart.study.emailchecker;

import java.util.Scanner;

/**
 * @author Korshakov Stepan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Write e-mail to ckeck(leave it blank to exit):");
            String email = in.nextLine();
            if (email.equals("")) {
                System.out.println("Exiting...");
                return;
            } else {
                if (EMailChecker.checkEmail(email)) {
                    System.out.println(email + " is Valid e-mail");
                } else {
                    System.out.println(email + " is Invalid e-mail");
                }
            }
        }
    }
}
