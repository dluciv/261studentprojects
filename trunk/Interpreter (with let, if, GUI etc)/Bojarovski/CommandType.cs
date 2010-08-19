/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    public enum CommandType
    {
        FLOWCONTROLL, //if, while, for, goto
        ENVIRONMENTMODIFICATION, //assignment, declaration
        VOID, 
        EVALUABLE, //unattached expressions
        OUTPUT, //print
        ERRORNOSUCHID,
        ERRORALREADYDECLARED,
        ERRORNULLARGUMENT,
        ERRORUNCOMPATIBLETYPES,
        ERRORUNDECLAREDID
    }
}
