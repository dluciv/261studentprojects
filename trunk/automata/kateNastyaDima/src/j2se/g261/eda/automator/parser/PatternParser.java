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
        PatternParser p = new PatternParser("(a|b)(ac)*|d*g?h");
        int c;
        try {
            Graph g = p.parse();
            Table t = new Table();
            GraphWorker.makeClosure(g);
//            GraphWorker.makeDeterministic(g);
            System.out.println(g);
            g.fillDeterminatedTable(t);
            t.fillTable();
            
            GraphWalker walker = new GraphWalker(g);
            System.out.println(walker.check("g"));
            TableWalker walk = new TableWalker(g,t);
            System.out.println(walk.check("aacac"));
            
//            TexWriter tex = new TexWriter(t);
//            File f = tex.generateFile();
//            Process pr = Runtime.getRuntime().exec("latex " + f.getAbsolutePath());
            
//            while((c = pr.getInputStream().read()) != -1){
//                System.out.print(Character.toChars(c));
//                if(Character.toChars(c).equals('?')){
//                pr.getOutputStream().write(new String("\n").getBytes());
//                pr.getOutputStream().flush();
//                }
//            }
//            String nm = f.getName();
//            System.out.println("--------------");
//            System.out.println(nmнала г);
//            System.out.println(nm.substring(0, nm.length() - 5));
            
//            Runtime.getRuntime().exec("kdvi " + nm.substring(0, nm.length() - 4) + ".dvi");
            
//            System.out.println(t);
//        } catch (IOException ex) {
//            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WalkerException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
