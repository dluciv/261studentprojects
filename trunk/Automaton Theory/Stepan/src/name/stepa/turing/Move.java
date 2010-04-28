package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public enum Move {
    LEFT, RIGHT, NONE, HALT;


    @Override
    public String toString() {
        if (this == LEFT)
            return "L";
        if (this == RIGHT)
            return "R";
        if (this == NONE)
            return "N";
        if (this == HALT)
            return "H";

        return "?";
    }
}
