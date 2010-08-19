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
    class Lexer
    {
        protected String input;
        protected char currSymbol;
        protected int currPosition;
        protected Token currentToken;
        protected StreamWriter outputStream;
        protected int tokenCount;
        protected List<Token> tokenList;//private list of tokens with an EOF token at the end
        protected Dictionary<String, Pair<IdentifierType, object>> Identifiers = 
                            new Dictionary<String, Pair<IdentifierType, object>>(new StringComparer());

        public Lexer(String someString, StreamWriter someStream)
        {
            input = someString;     //where the input code is stored
            currPosition = 0;
            currentToken = getNextToken();
            outputStream = someStream;
        }
        public void setInputString(String newInputString)
        {
            input = newInputString;
            currPosition = 0;
        }
        public void appendInputString(String toAppend)
        {
            input = input + " " + toAppend;
        }

        /*
         * Analyses the current character, and determines its token class.
         * If the token consists of more than one character, the currPosition index
         * is shifted to point to the next character that doesn't belong to the token.
         */
        public Token getNextToken()
        {
            TokenType currType = TokenType.UNKNOWN;
            String currTokenString = "";

            //IndexOutOfRangeException
            if (currPosition < input.Length)
            {
                currSymbol = input[currPosition];
                int ind = 0;

                if (char.IsWhiteSpace(currSymbol))
                {
                    ind++;
                    currType = TokenType.WHITESPACE;
                }
                //reads a Number
                else if (char.IsDigit(currSymbol)) //Numbers are series of digits
                {
                    currTokenString = currTokenString + currSymbol.ToString();
                    ind++;
                    while (currPosition + ind < input.Length
                            && char.IsDigit(input[currPosition + ind]))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        ind++;
                    }
                    currType = TokenType.INTEGER;
                    currentToken.setValue(currTokenString);
                }
                //reads an identifier or a keyword
                else if (char.IsLetter(currSymbol)) //identifiers must start with a letter
                {
                    currTokenString = currTokenString + currSymbol.ToString();
                    ind++;
                    while (currPosition + ind < input.Length
                            && char.IsLetterOrDigit(input[currPosition + ind]))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        ind++;
                    }
                    if (currTokenString.Equals("int", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.INTDEC;
                    }
                    else if (currTokenString.Equals("bool", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.BOOLDEC;
                    }
                    else if (currTokenString.Equals("while", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.WHILE;
                    }
                    else if (currTokenString.Equals("if", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.IF;
                    }
                    else if (currTokenString.Equals("then", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.THEN;
                    }
                    else if (currTokenString.Equals("else", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.ELSE;
                    }
                    else if (currTokenString.Equals("let", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.LET;
                    }
                    else if (currTokenString.Equals("in", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.IN;
                    }
                    else if (currTokenString.Equals("fun", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.FUN;
                    }
                    else if (currTokenString.Equals("for", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.FOR;
                    }
                    else if (currTokenString.Equals("goto", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.GOTO;
                    }
                    else if (currTokenString.Equals("print", StringComparison.CurrentCultureIgnoreCase))
                    {
                        currType = TokenType.PRINT;
                    }
                    else if ((currPosition + ind < input.Length)
                                && (input[currPosition + ind] == ':'))
                    {
                        currType = TokenType.LABEL;
                        ind++;
                        currentToken.setValue(currTokenString);
                        if (!Identifiers.ContainsKey(currTokenString))
                        {
                            Identifiers.Add(currTokenString, 
                                new Pair<IdentifierType, object>(IdentifierType.LABEL, null));
                        }
                    }
                    else
                    {
                        currType = TokenType.ID;
                        currentToken.setValue(currTokenString); 
                    }
                }
                else if (currSymbol == '/')
                {
                    currType = TokenType.DIV;
                    currTokenString = currSymbol.ToString();
                    ind++;
                }
                else if (currSymbol == '+')
                {
                    currTokenString = currSymbol.ToString();
                    ind++;

                    if ((currPosition + ind < input.Length)
                            && (input[currPosition + ind] == '+'))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        currType = TokenType.INC;
                        ind++;
                    }
                    else
                    {
                        currType = TokenType.PLUS;
                    }                    
                }
                else if (currSymbol == '-')
                {
                    currTokenString = currSymbol.ToString();
                    ind++;
                    if ((currPosition + ind < input.Length)
                           && (input[currPosition + ind] == '>'))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        currType = TokenType.FUNBIND;
                        ind++;
                    }
                    else if ((currPosition + ind < input.Length)
                           && (input[currPosition + ind] == '-'))
                    {
                        currType = TokenType.DEC;
                    }
                    else
                    {
                        currType = TokenType.MINUS;
                    }
                }
                else if (currSymbol == '*')
                {
                    currTokenString = currSymbol.ToString();
                    ind++;  //check if the next one is a '*' too

                    if ((currPosition + ind < input.Length)
                           && (input[currPosition + ind] == '*'))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        currType = TokenType.POWER;
                        ind++;
                    }
                    else
                        currType = TokenType.MULT;
                }
                else if (currSymbol == '!')
                {
                    currTokenString = currSymbol.ToString();
                    ind++;

                    if ((currPosition + ind < input.Length)
                            && (input[currPosition + ind] == '='))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        currType = TokenType.NOTEQUAL;
                        ind++;
                    }
                    else
                    {
                        currType = TokenType.UNKNOWN;
                    }
                }
                else if (currSymbol == '=')
                {
                    currTokenString = currSymbol.ToString();
                    ind++;

                    if ((currPosition + ind < input.Length)
                            && (input[currPosition + ind] == '='))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        currType = TokenType.EQUALS;
                        ind++;
                    }
                    else
                    {
                        currType = TokenType.ASSIGN;
                    }
                }
                else if (currSymbol == '<')
                {
                    currTokenString = currSymbol.ToString();
                    ind++;

                    if ((currPosition + ind < input.Length)
                            && (input[currPosition + ind] == '='))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        currType = TokenType.LESSEQ;
                        ind++;
                    }
                    else
                    {
                        currType = TokenType.LESS;
                    }
                }
                else if (currSymbol == '>')
                {
                    currTokenString = currSymbol.ToString();
                    ind++;

                    if ((currPosition + ind < input.Length)
                            && (input[currPosition + ind] == '='))
                    {
                        currSymbol = input[currPosition + ind];
                        currTokenString = currTokenString + currSymbol.ToString();
                        currType = TokenType.GREATEREQ;
                        ind++;
                    }
                    else
                    {
                        currType = TokenType.GREATER;
                    }
                }
                else if (currSymbol == '(')
                {
                    currType = TokenType.LPAREN;
                    currTokenString = currSymbol.ToString();
                    ind++;
                }
                else if (currSymbol == ')')
                {
                    currType = TokenType.RPAREN;
                    currTokenString = currSymbol.ToString();
                    ind++;
                }
                else if (currSymbol == '{')
                {
                    currType = TokenType.BEGINBL;
                    currTokenString = currSymbol.ToString();
                    ind++;
                }
                else if (currSymbol == '}')
                {
                    currType = TokenType.ENDBL;
                    currTokenString = currSymbol.ToString();
                    ind++;
                }
                else if (currSymbol == ';')
                {
                    currType = TokenType.EOS;
                    currTokenString = currSymbol.ToString();
                    ind++;
                }
                else if (currSymbol == '\n')
                {
                    currType = TokenType.EOL;
                }
                else
                {
                    currType = TokenType.UNKNOWN;
                    currTokenString = "Unexpected symbol";
                }

                Token someToken = new Token(currTokenString, currType);
                currPosition += ind;
                return someToken;
            }
            else if (input.Length == 0)
            {
                Token someToken = new Token("",TokenType.UNKNOWN);
                return someToken;
            }
            else
            {
                throw new IndexOutOfRangeException();
            }
        }
        /*
         * Reads the input string, and tries to classify all the characters
         * or words into tokens.
         */
        public void getAllTokens()
        {
            tokenList = new List<Token>();
            currPosition = 0;
            while (currPosition < input.Length)
            {
                Token currToken = getNextToken();
                if (currToken.getType() != TokenType.WHITESPACE)
                { 
                    tokenList.Add(currToken); 
                }
            }
            tokenList.Add(new Token("EOF", TokenType.EOF));
            tokenCount = tokenList.Count;
        }
        public void printTokenList()
        {
            foreach (Token cur in tokenList)
            {
                cur.print(outputStream);
            }
        }
    }
}