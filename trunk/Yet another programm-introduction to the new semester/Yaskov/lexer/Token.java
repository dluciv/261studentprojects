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

package lexer;

public class Token {
    private TokenType tokenType;
    private int attribute;

    Token(TokenType token, int attribute) {
        this.tokenType = token;
        this.attribute = attribute;
    }

    public TokenType getType() {
        return tokenType;
    }

    public int getAttribute() {
        return attribute;
    }
}
