/*
 * списсок все возможных лекем
 * Antonov Kirill(c), 2010
 */
package Lexer;

public enum LexemKind {

    Plus, Minus, Divide, Multiply, LeftBracket, RightBracket,
    Number, EOL, print, Unknown, equality, semicolon, Or, And, unequality, Less, Greater,
    Identifier, IF, THEN, ELSE, LET, IN, BEGIN, END, FUN, ARROW, PRINT, TRUE, FALSE, LE, GE,
    No, Colon, Int, Bool, TypeArrow, Unit
}
