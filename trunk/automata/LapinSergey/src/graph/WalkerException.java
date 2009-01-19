/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package graph;

public class WalkerException extends Exception {

    public WalkerException() {
        super("You can use graph only after epsilon-closure");
    }
}
