/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class UnknownSymbolException extends ParserException {

    public UnknownSymbolException(Position position) {
        super(position);
    }
}
