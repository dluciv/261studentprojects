/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 * список возможных типов токенов;
 *
 */

package lebedev;

public enum TokenType {
    NUMBER,

    PLUS,
    MINUS,
    DIVISION,
    MULTIPLICATION,

    IF,
    THEN,
    ELSE,

    BEGIN,
    END,

    LOG_OPERAND,

    AND,
    OR,
    NOT,
    EQUALITY,
    INEQUALITY,
    GREATER,
    LESS,
    GE,
    LE,

    LEFT_BRACKET,
    RIGHT_BRACKET,

    ID,
    EQUALS_SIGN,
    PRINT,

    SEMICOLON, // td

    LET,
    IN,

    FUNCTION,
    ARROW,

    UNKNOWN,
    EOL,
    EOF
}
