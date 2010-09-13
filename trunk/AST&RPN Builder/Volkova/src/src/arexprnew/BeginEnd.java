/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class BeginEnd implements Expression {

    private Sq sq;

    public BeginEnd(Sq sq) {
        this.sq = sq;
    }

    public Sq getSq() {
        return sq;
    }
}
