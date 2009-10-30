package exception;

import java.util.Random;

public class WheelOfFortune {
    private static Random fortune = new Random(System.currentTimeMillis());
    private static final int maxLuckyNumber = 5;
    private int luckyNumber = 0; 
    
    public void twistWheel() {
        luckyNumber = fortune.nextInt(maxLuckyNumber);
    }

    public boolean goodLuckCheck(int yourChoise) {
        return luckyNumber == yourChoise;
    }
    
    public int getLuckyNumber(){
        return luckyNumber;
    }
}