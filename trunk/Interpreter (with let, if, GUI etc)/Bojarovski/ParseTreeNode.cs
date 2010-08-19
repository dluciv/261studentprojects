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
    class ParseTreeNode<T> where T : ITreeNode
    {
        private T Component;
        private ParseTreeNode<T> leftNode;// = null;
        private ParseTreeNode<T> rightNode;// = null;

        public ParseTreeNode(ParseTreeNode<T> left, ParseTreeNode<T> right, T parsedComp)
        {
            Component = parsedComp;
            leftNode = left;
            rightNode = right;
        }
        public ParseTreeNode<T> getLeft()
        {
            return leftNode;
        }
        public ParseTreeNode<T> getRight()
        {
            return rightNode;
        }
        public void setLeft(ParseTreeNode<T> left)
        {
            leftNode = left;
        }
        public void setRight(ParseTreeNode<T> right)
        {
            rightNode = right;
        }
        public void printPTreeNode(StreamWriter someStream)
        {
            if (Component != null)
            {
                Component.printNode(" ", someStream);
            }
        }
        public LexemeType getComponentType()
        {
            return this.Component.getLexemeType();
        }
        public T getComponent()
        {
            return Component;
        }
        public List<Command> evalNodeComponent(ref Direction next, 
                                                ref Dictionary<String, Pair<IdentifierType, object>> Environment)
        {
            List<Command> curCommands = new List<Command>();
            if (this.Component != null)
            {
                curCommands = this.Component.evalNode(ref next, ref Environment);
            }
            if ((this.rightNode == null) || (next == Direction.JUMP)) 
            {
                return curCommands;
            }
            else
            {
                List<Command> childCommands = this.rightNode.evalNodeComponent(ref next, ref Environment);
                foreach (Command com in childCommands)
                {
                    curCommands.Add(com);
                }
                return curCommands;
            }
        }
    }
}
