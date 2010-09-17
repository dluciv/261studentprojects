package ide.exceptions;

import ide.ide.*;

public class InterException extends Exception {

    private Position position = null;

    public InterException(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
