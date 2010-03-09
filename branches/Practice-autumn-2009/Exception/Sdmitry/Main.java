/*
 * the broken wheel of fortune
 * soldatov dmity©, 2009
 */
package exception;
/*
 * The Wheel of Fortune is "suddenly" broken every time you win =)
 */

import java.io.*;

public class Main {

    public static int chooseNumber() { 
        int offset = 48; //cause System.in.read() reads code of symbol
        int number = 0;

        System.out.println("Welcome to the Wheel of Fortune!" +
                "Choose number between 0 and 4: ");
        try {
            number = System.in.read() - offset; //user set number he wish
        } catch (IOException e) {
        }

        return number;
    }

    public static void main(String[] args) throws BrokenWheelException {
        WheelOfFortune wheelOfFortune = new WheelOfFortune();
        wheelOfFortune.twistWheel(); //generates WheelOfFortune.luckyNumber
        try {
            if (wheelOfFortune.goodLuckCheck(chooseNumber())) {
                throw new BrokenWheelException();
            } else {
                System.out.println("You loose! Fortune choosed " +
                        wheelOfFortune.getLuckyNumber() + "!");
            }
        } catch (BrokenWheelException e) {
            System.out.println("Sorry, but Wheel of Fortune is broken." +
                    "*Wheel shows " + wheelOfFortune.getLuckyNumber() + "*");
        }
    }
}
