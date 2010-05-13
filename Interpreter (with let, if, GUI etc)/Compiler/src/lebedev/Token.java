/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 * представитель списка токенов, создаваемого интерпретатором. у каждого токена есть тип, у некоторых из них -
 * атрибут;
 *
 */

package lebedev;

public class Token {
    private TokenType tokenType;
    private int attribute;

    public Token(TokenType token, int attribute) {
        this.tokenType = token;
        this.attribute = attribute;
    }

    public Token(TokenType token) {
        this.tokenType = token;
    }

    public TokenType getType() {
        return tokenType;
    }

    public int getAttribute() {
        return attribute;
    }
}
