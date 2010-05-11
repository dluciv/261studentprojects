/*
 * Analizator
 * LexType
 * Dmitriy Zabranskiy g261 (c)2009
 */
package analizator;

public enum LexType {

    NUMBER, ASSIGN, BREAKITOPEN, BREAKITCLOSE,
    EOL, VARIABLE, MULT, DIV, SUB, ADD,
    IF, THEN, ELSE, PRINT, EQUAL, UNKNOWN,
    LESS, MORE, NEQUAL, DEGREE, EOB, EOP
}
