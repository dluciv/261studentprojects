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
    class Parser : Lexer
    {
        private Token matchToken;
        private Token lookAheadToken;
        private TokenType curTokenType;
        private int next = 0;
        protected ParseTree programTree;

        public Parser(String someString, StreamWriter someStream)
            : base(someString, someStream)
        {
        }
        public ParseTreeNode<ITreeNode> extractLabels(ILabelNode node)
        {
            bool labelFound = false;
            int position = 0;
            List<ITreeNode> curBlock = node.getBlock(); //the highest block, with possible nested blocks 
            String curLabel = node.getLabel();
            ParseTreeNode<ITreeNode> labelNode;//the node we return
            ParseTreeNode<ITreeNode> nextLabelNode = new ParseTreeNode<ITreeNode>(null, null, node);

            while (position < curBlock.Count)
            {
                if (curBlock[position].getLexemeType() == LexemeType.LABELED_BLOCK)
                {
                    ILabelNode tempNode = (ILabelNode)curBlock[position];
                    //create a new ParseTreeNode, with that element as a component
                    //and with the result of extractLabels() as a right node
                    labelFound = true;
                    ILabelNode subNode = new LabelNode(tempNode.getLabel(), tempNode.getBlock());
                    nextLabelNode = extractLabels(subNode);
                }
                position++;
            }
            if (labelFound)
            {
                labelNode = new ParseTreeNode<ITreeNode>(null, nextLabelNode, node);
            }
            else
            {
                labelNode = new ParseTreeNode<ITreeNode>(null, null, node);
            }
            return labelNode;
        }
        public ParseTreeNode<ITreeNode> buildParseTree()
        {
            ParseTreeNode<ITreeNode> node;

            if (curTokenType == TokenType.EOF)
            {
                return null;
            }
            else
            {
                ITreeNode component = Program();

                if (component.getLexemeType() == LexemeType.LABELED_BLOCK)
                {
                    //it allready contains the lower labeled blocks, if any
                    ParseTreeNode<ITreeNode> labels = extractLabels((ILabelNode)component);
                    return labels;
                }
                else
                {
                    ParseTreeNode<ITreeNode> right = buildParseTree();
                    node = new ParseTreeNode<ITreeNode>(null, right, component);
                    return node;
                }
            }
        }
        public ParseTree getParseTree()
        {
            ParseTreeNode<ITreeNode> node = buildParseTree();
            programTree = new ParseTree(null, node);
            return programTree;
        }
        public void Expect(TokenType expectedTokenType, Token gotToken)
        {
            outputStream.WriteLine("Expected: " + expectedTokenType.ToString());
            outputStream.WriteLine("Got: " + gotToken.getValue());
            throw new DivideByZeroException(); // :)
        }
        public void Match(ref Token retToken, TokenType expectedTokenType)
        {
            if (lookAheadToken.getType() == expectedTokenType)
            {
                retToken = lookAheadToken;
                getLookAhead();

            }
            else
            {
                Expect(expectedTokenType, retToken);
            }
        }
        public void getLookAhead()
        {
            if (next <= tokenCount)
            {
                lookAheadToken = tokenList[next];
                curTokenType = lookAheadToken.getType();
                next++;
            }
        }
        public ITreeNode Program()
        {
            ITreeNode programNode = new ErrorNode();

            switch (curTokenType)
            {
                case TokenType.BEGINBL:
                    Match(ref matchToken, TokenType.BEGINBL);
                    ITreeNode someStatement = Program();
                    BlockNode block = new BlockNode();
                    block.addStatement(someStatement);
                    while (curTokenType != TokenType.ENDBL)
                    {
                        someStatement = Program();
                        block.addStatement(someStatement);
                    }
                    Match(ref matchToken, TokenType.ENDBL);
                    programNode = block;
                    break;
                case TokenType.LABEL:
                    Match(ref matchToken, TokenType.LABEL);
                    LabelNode labeledBlock = new LabelNode(matchToken.getValue());
                    ITreeNode labeledStatement;
                    do
                    {
                        labeledStatement = Program();
                        labeledBlock.addStatement(labeledStatement);
                    }
                    while (curTokenType != TokenType.EOF);
                    programNode = labeledBlock;
                    break;
                case TokenType.INTDEC:
                    Match(ref matchToken, TokenType.INTDEC);
                    Match(ref matchToken, TokenType.ID);
                    programNode = new IdentifierDeclarationNode(IdentifierType.INT, matchToken.getValue());
                    Match(ref matchToken, TokenType.EOS);
                    break;
                case TokenType.BOOLDEC:
                    Match(ref matchToken, TokenType.BOOLDEC);
                    Match(ref matchToken, TokenType.ID);
                    programNode = new IdentifierDeclarationNode(IdentifierType.BOOL, matchToken.getValue());
                    Match(ref matchToken, TokenType.EOS);
                    break;
                case TokenType.FOR:
                    Match(ref matchToken, TokenType.FOR);
                    Match(ref matchToken, TokenType.LPAREN);
                    AssignmentNode init = (AssignmentNode)Program();
                    AssignmentNode step = (AssignmentNode)Program();
                    BooleanExpressionNode condition = (BooleanExpressionNode)BooleanExpression();
                    Match(ref matchToken, TokenType.RPAREN);
                    BlockNode forBody = (BlockNode)Program();
                    programNode = new ForNode(init, step, condition, forBody);
                    break;
                /*case TokenType.FUN:
                    Match(ref matchToken, TokenType.FUN);
                    IdentifierNode id = (IdentifierNode)Factor();
                    programNode = new FunctionNode(id);
                    Match(ref matchToken, TokenType.EOS);
                    break;*/
                case TokenType.GOTO:
                    Match(ref matchToken, TokenType.GOTO);
                    Match(ref matchToken, TokenType.ID);
                    IdentifierNode gotoLabel = new IdentifierNode(matchToken.getValue(), IdentifierType.LABEL);
                    programNode = new GotoNode(gotoLabel);
                    Match(ref matchToken, TokenType.EOS);
                    break;
                case TokenType.ID:
                    Match(ref matchToken, TokenType.ID);
                    IdentifierNode assignId = new IdentifierNode(matchToken.getValue(), IdentifierType.UNKNOWN);
                    Match(ref matchToken, TokenType.ASSIGN);
                    BooleanExpressionNode assignValue = (BooleanExpressionNode)BooleanExpression();
                    programNode = new AssignmentNode(assignId, assignValue);
                    Match(ref matchToken, TokenType.EOS);
                    break;
                case TokenType.IF:
                    Match(ref matchToken, TokenType.IF);
                    ITreeNode thenBranch;
                    ITreeNode elseBranch;

                    Match(ref matchToken, TokenType.LPAREN);
                    BooleanExpressionNode ifCondition = (BooleanExpressionNode)BooleanExpression();
                    Match(ref matchToken, TokenType.RPAREN);
                    Match(ref matchToken, TokenType.THEN);
                    thenBranch = (BlockNode)Program();
                    if (curTokenType == TokenType.ELSE)
                    {
                        Match(ref matchToken, TokenType.ELSE);
                        elseBranch = (BlockNode)Program();
                    }
                    else
                    {
                        elseBranch = new BlankNode();
                    }
                    programNode = new IfNode(ifCondition, thenBranch, elseBranch);
                    break;
                /*case TokenType.LET:
                    Match(ref matchToken, TokenType.LET);
                    IdentifierNode shortId = (IdentifierNode)Factor();
                    Match(ref matchToken, TokenType.ASSIGN);
                    BooleanExpressionNode subst = (BooleanExpressionNode)BooleanExpression();
                    Match(ref matchToken, TokenType.IN);
                    BooleanExpressionNode target = (BooleanExpressionNode)BooleanExpression();
                    programNode = new LetNode(shortId, subst, target);
                    Match(ref matchToken, TokenType.EOS);
                    break;*/
                case TokenType.PRINT:
                    Match(ref matchToken, TokenType.PRINT);
                    Match(ref matchToken, TokenType.LPAREN);
                    BooleanExpressionNode printArgument = (BooleanExpressionNode)BooleanExpression();
                    Match(ref matchToken, TokenType.RPAREN);
                    programNode = new PrintNode(printArgument);
                    Match(ref matchToken, TokenType.EOS);
                    break;
                case TokenType.WHILE:
                    Match(ref matchToken, TokenType.WHILE);
                    Match(ref matchToken, TokenType.LPAREN);
                    BooleanExpressionNode whileCondition = (BooleanExpressionNode)BooleanExpression();
                    Match(ref matchToken, TokenType.RPAREN);
                    BlockNode whileBody = (BlockNode)Program();
                    programNode = new WhileNode(whileCondition, whileBody);
                    break;
                case TokenType.EOF:
                    programNode = new BlankNode();
                    break;
                default:
                    Expect(TokenType.UNKNOWN, lookAheadToken);
                    break;
            }
            return programNode;
        }
        public IArithmeticNode BooleanExpression()
        {
            ITreeNode firstOpNode = Expression();
            ITreeNode secondOpNode;
            IArithmeticNode boolExprNode = new BooleanExpressionNode(TokenType.UNKNOWN, firstOpNode, null);

            while ((curTokenType == TokenType.EQUALS) ||
                    (curTokenType == TokenType.NOTEQUAL) ||
                    (curTokenType == TokenType.GREATER) ||
                    (curTokenType == TokenType.GREATEREQ) ||
                    (curTokenType == TokenType.LESS) ||
                    (curTokenType == TokenType.LESSEQ))
            //  (curTokenType == TokenType.FUNBIND))
            {
                switch (curTokenType)
                {
                    case TokenType.EQUALS:      //operands might be bool or int
                        Match(ref matchToken, TokenType.EQUALS);
                        secondOpNode = BooleanExpression();
                        boolExprNode = new BooleanExpressionNode(TokenType.EQUALS, firstOpNode, secondOpNode);
                        break;
                    case TokenType.NOTEQUAL:    //...
                        Match(ref matchToken, TokenType.NOTEQUAL);
                        secondOpNode = BooleanExpression();
                        boolExprNode = new BooleanExpressionNode(TokenType.NOTEQUAL, firstOpNode, secondOpNode);
                        break;
                    case TokenType.GREATER:     //operands may only be int
                        Match(ref matchToken, TokenType.GREATER);
                        secondOpNode = BooleanExpression();
                        boolExprNode = new BooleanExpressionNode(TokenType.GREATER, firstOpNode, secondOpNode);
                        break;
                    case TokenType.GREATEREQ:
                        Match(ref matchToken, TokenType.GREATEREQ);
                        secondOpNode = BooleanExpression();
                        boolExprNode = new BooleanExpressionNode(TokenType.GREATEREQ, firstOpNode, secondOpNode);
                        break;
                    case TokenType.LESS:
                        Match(ref matchToken, TokenType.LESS);
                        secondOpNode = BooleanExpression();
                        boolExprNode = new BooleanExpressionNode(TokenType.LESS, firstOpNode, secondOpNode);
                        break;
                    case TokenType.LESSEQ:
                        Match(ref matchToken, TokenType.LESSEQ);
                        secondOpNode = BooleanExpression();
                        boolExprNode = new BooleanExpressionNode(TokenType.LESSEQ, firstOpNode, secondOpNode);
                        break;
                    /*case TokenType.FUNBIND:
                        Match(ref matchToken, TokenType.FUNBIND);
                        secondOpNode = BooleanExpression();
                        boolExprNode = new BooleanExpressionNode(TokenType.FUNBIND, firstOpNode, secondOpNode);
                        break;*/
                    default:
                        Expect(TokenType.UNKNOWN, lookAheadToken);
                        break;
                }
            }
            return boolExprNode;
        }
        public IArithmeticNode Expression()
        {
            IArithmeticNode firstOpNode = Term();
            IArithmeticNode intConst;
            IArithmeticNode secondOpNode;
            IArithmeticNode exprNode = new ExpressionNode(TokenType.UNKNOWN, firstOpNode, null);

            while ((curTokenType == TokenType.PLUS) ||
                    (curTokenType == TokenType.MINUS) ||
                    (curTokenType == TokenType.DEC) ||
                    (curTokenType == TokenType.INC))
            {
                switch (curTokenType)
                {
                    case TokenType.PLUS:
                        Match(ref matchToken, TokenType.PLUS);
                        secondOpNode = Expression();
                        exprNode = new ExpressionNode(TokenType.PLUS, firstOpNode, secondOpNode);
                        break;
                    case TokenType.MINUS:
                        Match(ref matchToken, TokenType.MINUS);
                        secondOpNode = Expression();
                        exprNode = new ExpressionNode(TokenType.MINUS, firstOpNode, secondOpNode);
                        break;
                    case TokenType.DEC:
                        Match(ref matchToken, TokenType.DEC);
                        intConst = new IntegerNode(1);
                        exprNode = new ExpressionNode(TokenType.MINUS, intConst, firstOpNode);
                        break;
                    case TokenType.INC:
                        Match(ref matchToken, TokenType.INC);
                        intConst = new IntegerNode(1);
                        exprNode = new ExpressionNode(TokenType.PLUS, firstOpNode, intConst);
                        break;
                    default:
                        Expect(TokenType.UNKNOWN, lookAheadToken);
                        break;
                }
            }
            return exprNode;
        }
        public IArithmeticNode Term()
        {
            IArithmeticNode firstOpNode = Factor();
            IArithmeticNode secondOpNode;
            IArithmeticNode termNode = new TermNode(TokenType.UNKNOWN, firstOpNode, null);

            while ((curTokenType == TokenType.MULT) ||
                    (curTokenType == TokenType.DIV) ||
                    (curTokenType == TokenType.POWER))
            {
                switch (curTokenType)
                {
                    case TokenType.MULT:
                        Match(ref matchToken, TokenType.MULT);
                        secondOpNode = Term();
                        termNode = new TermNode(TokenType.MULT, firstOpNode, secondOpNode);
                        break;
                    case TokenType.DIV:
                        Match(ref matchToken, TokenType.DIV);
                        secondOpNode = Term();
                        termNode = new TermNode(TokenType.DIV, firstOpNode, secondOpNode);
                        break;
                    case TokenType.POWER:
                        Match(ref matchToken, TokenType.POWER);
                        secondOpNode = Term();
                        termNode = new TermNode(TokenType.POWER, firstOpNode, secondOpNode);
                        break;
                    default:
                        Expect(TokenType.UNKNOWN, lookAheadToken);
                        termNode = new ErrorNode();
                        break;
                }
            }
            return termNode;
        }
        public IArithmeticNode Factor()
        {
            IArithmeticNode factorNode = new BlankNode();

            switch (curTokenType)
            {
                case TokenType.LPAREN:
                    Match(ref matchToken, TokenType.LPAREN);
                    factorNode = BooleanExpression();
                    Match(ref matchToken, TokenType.RPAREN);
                    break;
                case TokenType.ID:
                    String idName = lookAheadToken.getValue();
                    factorNode = new IdentifierNode(idName, IdentifierType.UNKNOWN);
                    Match(ref matchToken, TokenType.ID);
                    /*if (lookAheadToken.getType() == TokenType.LPAREN)
                    {
                        Match(ref matchToken, TokenType.LPAREN);
                        ITreeNode argument = BooleanExpression();
                        Match(ref matchToken, TokenType.RPAREN);
                        factorNode = new FunctionCallNode(idName, argument);
                    }*/
                    break;
                case TokenType.INTEGER:
                    factorNode = new IntegerNode(int.Parse(lookAheadToken.getValue()));
                    Match(ref matchToken, TokenType.INTEGER);
                    break;
                case TokenType.MINUS:       //unary minus
                    Match(ref matchToken, TokenType.MINUS);
                    IArithmeticNode tempNode = Expression();
                    tempNode.setNegative();
                    factorNode = tempNode;
                    break;
                default:
                    Expect(TokenType.UNKNOWN, lookAheadToken);
                    break;
            }
            return factorNode;
        }
    }
}
