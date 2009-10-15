package exception;

import java.util.Random;
import java.io.*;

public class WheelOfFortune{
    public static void twistWheel() throws Exception{
        Random fortune = new Random();
        int max = 5;
        int offset = 48;
        int luckynumber = fortune.nextInt(max);
        System.out.println("Welcome to the Wheel of Fortune!" +
                           "Choose number between 0 and 4");
        try {
            int number = System.in.read() - offset;
            if (number == luckynumber){
                throw new Exception("Sorry, the Wheel of Forune should be broken! " +
                                    "*Wheel showed " + luckynumber + "*");
            }
            else{
                System.out.println("You choose: " + number +
                        "\nBut Fortune choise is " + luckynumber);
            }
        }
        catch (IOException e) {}
        


    }
}
