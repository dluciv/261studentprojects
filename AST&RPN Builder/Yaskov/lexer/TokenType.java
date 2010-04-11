/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 * список возможных типов токенов;
 *
 */

package lexer;

public enum TokenType {
    NUMBER,

    PLUS,
    MINUS,
    DIVISION,
    MULTIPLICATION,

    LEFT_BRACKET,
    RIGHT_BRACKET,

    ID,
    ASSIGNMENT,
    PRINT,
    SEMICOLON,

    UNKNOWN,
    EOF
}
