/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

public class Converter {

    private static final int DOSE = 12;

    public static float covertFromDoseToDec(float n) {
        return n * DOSE;
    }

    public static float covertFromDecToDose(float n) {
        return n / DOSE;
    }
}
