/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    class Token
    {
        private String Value;
        private TokenType Type;

        public Token(String someString, TokenType someType)
        {
            Value = someString;
            Type = someType;
        }

        public String getValue()
        {
            return Value;
        }
        public void setValue(String someString)
        {
            Value = someString;
        }
        public TokenType getType()
        {
            return Type;
        }
        public void print(StreamWriter some_str)
        {
            if (Type != TokenType.WHITESPACE)
            {
                some_str.WriteLine("Token value: " + Value + " Token type: " + Type.ToString());
            }
        }
    }
}
