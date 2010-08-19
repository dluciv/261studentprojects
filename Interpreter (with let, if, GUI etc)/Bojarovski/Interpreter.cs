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
    class Interpreter : Parser
    {
        public Interpreter(String someString, StreamWriter someStream)
                            : base(someString, someStream)
        {
        }
        private ParseTreeNode<ITreeNode> jumpToLabel(ParseTreeNode<ITreeNode> curNode, String label)
        {
            ILabelNode sdf = new LabelNode("");
            if (curNode.getComponentType() == LexemeType.LABELED_BLOCK)
            {
                 sdf = (ILabelNode)curNode.getComponent();
                 if (sdf.getLabel() == label)
                 {
                     return curNode;
                 }
                 else
                 {
                     return jumpToLabel(curNode.getRight(), label);
                 }
            }            
            else
            {
                return jumpToLabel(curNode.getRight(), label);
            }
        }

        public void runParseTree(ParseTreeNode<ITreeNode> someNode)
        {
            Direction nextCommand = Direction.NEXT;
            List<Command> commands = someNode.evalNodeComponent(ref nextCommand, ref Identifiers);

            foreach (Command curCommand in commands)
            {
                CommandType curComType = curCommand.getCommandType();
                switch (curComType)
                {
                    case CommandType.EVALUABLE:
                        outputStream.WriteLine(curCommand.getIdValue().ToString());
                        break;
                    case CommandType.FLOWCONTROLL:
                        //outputStream.WriteLine("Jumping to label: " + curCommand.getIdName());
                        ParseTreeNode<ITreeNode> jumpNode = jumpToLabel(this.programTree.getRoot().getRight(),                                                                                                 curCommand.getIdName());
                        runParseTree(jumpNode);
                        break;
                    case CommandType.OUTPUT:
                        outputStream.WriteLine(curCommand.getIdValue());
                        break;
                    case CommandType.ERRORALREADYDECLARED:
                        outputStream.WriteLine("Error: Identifier "+curCommand.getIdName()+ " is already declared");
                        break;
                    case CommandType.ERRORNOSUCHID:
                        outputStream.WriteLine("Error: Unknown Identifier " + curCommand.getIdName());
                        break;
                    case CommandType.ERRORNULLARGUMENT:
                        outputStream.WriteLine("Error: Identifier " + curCommand.getIdName() + " has null value");
                        break;
                    case CommandType.ERRORUNCOMPATIBLETYPES:
                        outputStream.WriteLine("Error: Uncompatible type of identifier " + curCommand.getIdName());
                        break;
                    case CommandType.ERRORUNDECLAREDID:
                        outputStream.WriteLine("Error: Identifier " + curCommand.getIdName() + " is not declared");
                        break;
                    default:
                        //???
                        break;
                }
            }
        }
    }
}
