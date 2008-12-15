package parser;

import graph.Graph;
import graph.GraphWorker;
import Node.Node;

public class PatternParser {
    
    Lexer lexer;

    public PatternParser(String pattern) {
        lexer = new Lexer(pattern);
    }

    
    public Graph parse() throws ParserException{
        Graph g = expression();
        if(!lexer.isEndOfLine())
            throw new ParserException(ParserException.WRONG_USING_OPERATORS); //bad parser        
        g.normalize();
        System.out.println(g);
        return g;        
    }
    
    private Graph atom() throws ParserException {
        Graph res = null;
        char nextLexem = lexer.nextChar();
        if(nextLexem == '('){
            res = expression();
            if(!lexer.isEndOfLine() && lexer.nextChar() != ')'){
                throw new ParserException(ParserException.WRONG_STAPLERS);
            }
            return res;
        }else if(isSymbol(nextLexem)){
            res = new Graph();
            Node start = new Node(res.getAll().size());
            res.addOneNode(start);
            Node end = new Node(res.getAll().size());
            res.addOneNode(end);
            start.addOutgoingNode(end, nextLexem);
            end.addIncomingNode(start, nextLexem);            
            res.markStart(start);
            res.markEnd(end);
            return res;
        }else{
            throw new ParserException(ParserException.WRONG_USING_OPERATORS);
        }
    }
    
    private Graph expression() throws ParserException{
        
        Graph res = sequence();
        if(lexer.isEndOfLine()) return res;
        while(!lexer.isEndOfLine() && lexer.nextChar() == '|'){
            res = GraphWorker.concatenateOR(res, sequence());
        }
        return res;
    }

    private Graph post_option() throws ParserException {
        Graph res = atom();
        if(lexer.isEndOfLine()) return res;
        char nextChar = lexer.nextChar();
        if(nextChar == '*'){
            return GraphWorker.concatanateANY(res);
        }else if(nextChar == '?'){
            return GraphWorker.concatenateONE(res);
        }else{
            lexer.decrementCursor();
            return res;
        }
    }

    private Graph sequence() throws ParserException {
        Graph res = post_option();
       
        if(lexer.isEndOfLine()) return res;
        char nextChar = lexer.nextChar();
        lexer.decrementCursor();
        while(!lexer.isEndOfLine() && isSymbol(nextChar)){
            if(nextChar == ')'){
                lexer.decrementCursor();
                return res;
            }
            Graph nw = post_option();
            res = GraphWorker.concatanateAND(res, nw);
            if(lexer.isEndOfLine()) return res;
            nextChar = lexer.nextChar();
            lexer.decrementCursor();
        }
        
        return res;
    }
    
    private boolean isSymbol(char c){
        return c != '|' && c != '*' && c != '?';
    }
}
