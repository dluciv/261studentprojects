/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.IO;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    public interface ITreeNode : IEnumerable
    {
        LexemeType getLexemeType();
        void printNode(String level, StreamWriter someStream);
        List<Command> evalNode(ref Direction next, 
                                ref Dictionary<String, Pair<IdentifierType, object>> Environment);
    }
}
