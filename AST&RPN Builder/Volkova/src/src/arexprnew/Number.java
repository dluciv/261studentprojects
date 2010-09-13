/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class Number implements Expression {

    int number;

    public Number(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
