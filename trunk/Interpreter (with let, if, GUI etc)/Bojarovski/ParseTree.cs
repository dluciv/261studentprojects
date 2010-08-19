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
    class ParseTree
    {
        private ParseTreeNode<ITreeNode> root;

        public ParseTree(ParseTreeNode<ITreeNode> left, ParseTreeNode<ITreeNode> right)
        {
            root = new ParseTreeNode<ITreeNode>(left, right, null);
        }
        public void printTree(ParseTreeNode<ITreeNode> node,  StreamWriter someStream)
        {
            if (node.getLeft() != null)
            {
                node.getLeft().printPTreeNode(someStream);
                printTree(node.getLeft(), someStream);
            }
            if (node.getRight() != null)
            {
                node.getRight().printPTreeNode(someStream);
                printTree(node.getRight(), someStream);
            }
        }
        public ParseTreeNode<ITreeNode> getRoot()
        {
            return root;
        }
    }
}
