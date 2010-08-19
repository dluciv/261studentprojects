/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    public enum TokenType
    {
        WHITESPACE, // space or tab
        UMINUS,     // unary -
        DEC,        // unary --
        INC,        // unary ++
        PLUS,       // binary +
        MINUS,      // binary -        
        MULT,       // binary *
        DIV,        // binary /
        POWER,      // binary **
        ASSIGN,     // binary = (used for assignment of values to variables)
        NOTEQUAL,   // binary !=
        EQUALS,     // binary ==
        LESS,       // binary <
        LESSEQ,     // binary <=
        GREATER,    // binary >
        GREATEREQ,  // binary >=
        DIGIT,      // 0..9 (might not be used at all...)
        AZ,         // a..z A..Z
        LPAREN,     // '('
        RPAREN,     // ')'
        BEGINBL,    // '{' beginning of a block
        ENDBL,      // '}' end of a block
        EOS,        // ';' end of statement
        EOL,        // \n newline character
        EOF,        // end of file character
        INTDEC,     // integer declaration
        BOOLDEC,    // boolean declaration
        IF,         // 'if' keyword
        THEN,       // 'then' keyword
        ELSE,       // 'else' keyword
        LET,        // 'let' keyword
        IN,         // 'in' keyword
        FUN,        // 'fun' keyword
        FUNBIND,    // '->' binding [expr] to ID
        WHILE,      // 'while' keyword
        FOR,        // 'for' keyword
        GOTO,       // 'goto' keyword
        PRINT,      // 'print' keyword
        LABEL,      // series of al-num characters, starting with a letter, followed by a colon
        INTEGER,    // series of digits
        ID,         // series of alphanumeric characters, the first character is always a letter
        UNKNOWN     // unexpected character
    }
}