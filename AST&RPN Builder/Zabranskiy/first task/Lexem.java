/*
 * Analizator
 * Lexem
 * Dmitriy Zabranskiy g261 (c)2009
 */
package analizator;

public class Lexem {

    private LexType type;
    private Position position = null;

    public Lexem(LexType inType, Position position) {
        this.type = inType;
        this.position = position;
    }

    public LexType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }
}
