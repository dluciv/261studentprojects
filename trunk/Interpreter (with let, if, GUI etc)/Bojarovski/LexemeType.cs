/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    public enum LexemeType
    {
        BLANK,              // empty node
        ERROR,
        CONST,              // integer constant
        ID_DEC,             // identifier declaration
        FUN_DEF,            // fun <id> -> <expr>
        FUN_CALL,           // <id> (<expr>)
        ASSIGNMENT,         // <id> = <expr>
        EXPRESSSION,    // arithmetic expression
        BOOL_EXPRESSION,    // boolean expression
        GOTO,               // goto statement
        PRINT,              // print statement
        TERM,               // term
        BLOCK,              // one or more expressions or assignments
        LABELED_BLOCK,      // one or more expressions or assignments combined under a label
        IF_OPERATOR,        // if <expr> then { <block> } else { <block> }
        WHILE_OPERATOR,     // while <expr> { <block> }
        FOR_OPERATOR,       // for (<init_val> ; <increment> ; <condition>) { <block> }
        LET_OPERATOR,       // let <id> = <expr> in <block>
        IDENTIFIER          // int, bool or unknown
    }
}
