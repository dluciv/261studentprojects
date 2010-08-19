/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    public class Command
    {
        private CommandType CurrentCommandType;

        private String Identifier;
        private object Value;

        public Command(CommandType someType, String someId, object someVal)
        {
            CurrentCommandType = someType;
            Identifier = someId;
            Value = someVal;
        }
        public CommandType getCommandType()
        {
            return CurrentCommandType;
        }
        public String getIdName()
        {
            return Identifier;
        }
        public object getIdValue()
        {
            return Value;
        }
    }
}
