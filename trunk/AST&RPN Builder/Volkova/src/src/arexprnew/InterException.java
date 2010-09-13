/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class InterException extends Exception {

    private Position position = null;

    public InterException(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
