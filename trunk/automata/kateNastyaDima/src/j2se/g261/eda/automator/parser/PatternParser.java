/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.parser;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.GraphWalker;
import j2se.g261.eda.automator.graph.GraphWorker;
import j2se.g261.eda.automator.graph.Node;
import j2se.g261.eda.automator.graph.WalkerException;
import j2se.g261.eda.automator.table.Table;
import j2se.g261.eda.automator.table.TableWalker;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import j2se.g261.eda.automator.dot.DotException;
import j2se.g261.eda.automator.dot.DotUtils;
import java.util.HashMap;
import j2se.g261.eda.automator.minimization.Minimisation;

/**
 *
 * @author nastya
 */
public class PatternParser {
    
    Lexer lexer;

    public PatternParser(String pattern) {
        lexer = new Lexer(pattern);
    }

    
    public Graph parse() throws ParserException{
        Graph g = expression();
        if(!lexer.isEndOfLine())
            throw new ParserException(ParserException.WRONG_USING_OPERATORS);
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
            
            return new Graph(new Node(nextLexem));
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
//        System.out.println("post option \n" + res);
        
        if(lexer.isEndOfLine()) return res;
        char nextChar = lexer.nextChar();
        lexer.decrementCursor();
        while(!lexer.isEndOfLine() && isSymbol(nextChar)){
            if(nextChar == ')'){
                lexer.decrementCursor();
                return res;
            }
            Graph nw = post_option();
//            System.out.println("new \n" + nw);
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
    
    public static void main(String[] args) {
        
        PatternParser p = new PatternParser("(0101|0001)(10)*");
        int c;
        try {
            Graph g = p.parse();
            Table t = new Table();
            GraphWorker.makeClosure(g);
            Graph g1 = GraphWorker.makeDeterministic(g);

            g.fillDeterminatedTable(t);
            t.fillTable();
            
//            DotUtils d = new DotUtils(g);
            DotUtils d1 = new DotUtils(g1);
            System.out.println(g1);
            System.out.println("!!!!!!!!!!");
            for (int i = 0; i < g1.allSize(); i++) {
                System.out.println(g1.getNodeFromAllAt(i));
            }
            
            Minimisation m1 = new Minimisation();
            m1.transform(g1);
            m1.addAbsorbingState();
            m1.minimizate();
     
            try{
            	System.out.println(m1.edgeDot("DOTTry").getAbsolutePath());
            } catch (IOException ex){
            	Logger.getLogger(Minimisation.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            GraphWalker walker = new GraphWalker(g);
            System.out.println(walker.check("a"));
           TableWalker walk = new TableWalker(g,t);
            System.out.println(walk.check(""));
            

        } catch (WalkerException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
