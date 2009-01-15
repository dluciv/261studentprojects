package graph;

public class WalkerException extends Exception {

    public WalkerException() {
        super("You can use graph only after epsilon-closure");
    }
}
