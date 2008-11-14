/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import j2se.g261.eda.automator.representations.dfa.DFA;
import j2se.g261.eda.automator.representations.dfa.DFAWorker;
import j2se.g261.eda.automator.representations.minimisation.Minimisation;
import j2se.g261.eda.automator.representations.minimisation.MinimizedDFA;
import j2se.g261.eda.automator.representations.minimisation.MinimizedDFAWorker;
import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.representations.nfa.NFAWalker;
import j2se.g261.eda.automator.representations.nfa.NFAWalkerException;
import j2se.g261.eda.automator.representations.nfa.NFAWorker;
import j2se.g261.eda.automator.representations.nfa.NFANode;
import j2se.g261.eda.automator.representations.table.Table;
import j2se.g261.eda.automator.visualizing.dot.DotException;
import j2se.g261.eda.automator.visualizing.dot.DotUtils;

/**
 *
 * @author nastya
 */
public class PatternParser {
    
    Lexer lexer;

    public PatternParser(String pattern) {
        lexer = new Lexer(pattern);
    }

    
    public NFA parse() throws ParserException{
        NFA g = expression();
        if(!lexer.isEndOfLine())
            throw new ParserException(ParserException.WRONG_USING_OPERATORS);
        return g;
        
    }
    
    private NFA atom() throws ParserException {
            NFA res = null;
        char nextLexem = lexer.nextChar();
        if(nextLexem == '('){
            res = expression();
            if(!lexer.isEndOfLine() && lexer.nextChar() != ')'){
                throw new ParserException(ParserException.WRONG_STAPLERS);
            }
            return res;
        }else if(isSymbol(nextLexem)){
            
            return new NFA(new NFANode(nextLexem));
        }else{
            throw new ParserException(ParserException.WRONG_USING_OPERATORS);
        }
    }
    
    private NFA expression() throws ParserException{
        
        NFA res = sequence();
        if(lexer.isEndOfLine()) return res;
        while(!lexer.isEndOfLine() && lexer.nextChar() == '|'){
            res = NFAWorker.concatenateOR(res, sequence());
        }
        return res;
    }

    private NFA post_option() throws ParserException {
        NFA res = atom();
        if(lexer.isEndOfLine()) return res;
        char nextChar = lexer.nextChar();
        if(nextChar == '*'){
            return NFAWorker.concatanateANY(res);
        }else if(nextChar == '?'){
            return NFAWorker.concatenateONE(res);
        }else{
            lexer.decrementCursor();
            return res;
        }
    }

    private NFA sequence() throws ParserException {
        NFA res = post_option();
//        System.out.println("post option \n" + res);
        
        if(lexer.isEndOfLine()) return res;
        char nextChar = lexer.nextChar();
        lexer.decrementCursor();
        while(!lexer.isEndOfLine() && isSymbol(nextChar)){
            if(nextChar == ')'){
                lexer.decrementCursor();
                return res;
            }
            NFA nw = post_option();
//            System.out.println("new \n" + nw);
            res = NFAWorker.concatanateAND(res, nw);
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

        PatternParser p = new PatternParser("(1000|1010)(0|1)*");
        int c;
        try {
            NFA nfa = p.parse();
            Table t = new Table();
            NFAWorker.makeClosure(nfa);
            
            DFAWorker dfaW = new DFAWorker();
            
            DFA dfa = dfaW.convertFromNFA(nfa);
            
            MinimizedDFAWorker mDfaW = new MinimizedDFAWorker();
            
            MinimizedDFA mDfa = mDfaW.convertFromNFAToMinimizedDFA(dfa);
            
            Minimisation m1 = new Minimisation(mDfa);
            
            MinimizedDFA minDfa = m1.minimize();
            
            
            nfa.fillDeterminatedTable(t);
            t.fillTable();

//            DotUtils d = new DotUtils(g);
            DotUtils d1 = new DotUtils(nfa, dfa , minDfa);
         
            try {
//              System.out.println(d.generateDotFileForNFA("DOTFILE").getAbsolutePath());
              System.out.println(d1.generateDotFileForNFA("DOTNFA").getAbsolutePath());
          } catch (IOException ex) {
              Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
          } catch (DotException ex) {
              Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
          }
            
            try{
            	System.out.println(d1.edgeDot("DOTMIN").getAbsolutePath());
            } catch (IOException ex){
            	Logger.getLogger(Minimisation.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
//              System.out.println(d.generateDotFileForNFA("DOTFILE").getAbsolutePath());
              System.out.println(d1.generateDotFileForDFA("DOTDFA").getAbsolutePath());
          } catch (IOException ex) {
              Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
          } catch (DotException ex) {
              Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
          }


              NFAWalker walker = new NFAWalker(nfa);
          //  System.out.println(walker.check("a"));
           //TableWalker walk = new TableWalker(g,t);
           // System.out.println(walk.check(""));


        } catch (NFAWalkerException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
