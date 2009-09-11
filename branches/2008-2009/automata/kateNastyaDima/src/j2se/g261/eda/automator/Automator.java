/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.visualizing.dot.DotException;
import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.representations.nfa.NFAWalker;
import j2se.g261.eda.automator.representations.nfa.NFAWalkerException;
import j2se.g261.eda.automator.representations.nfa.NFAWorker;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.parser.PatternParser;
import j2se.g261.eda.automator.representations.dfa.DFA;
import j2se.g261.eda.automator.representations.dfa.DFAWalker;
import j2se.g261.eda.automator.representations.dfa.DFAWorker;
import j2se.g261.eda.automator.representations.minimisation.EdgeGraphWalker;
import j2se.g261.eda.automator.representations.minimisation.Minimisation;
import j2se.g261.eda.automator.representations.minimisation.MinimizedDFA;
import j2se.g261.eda.automator.representations.minimisation.MinimizedDFAWorker;
import j2se.g261.eda.automator.representations.table.SearchTable;
import j2se.g261.eda.automator.representations.table.Table;
import j2se.g261.eda.automator.representations.table.TableWalker;
import j2se.g261.eda.automator.visualizing.dot.DotUtils;
import j2se.g261.eda.automator.visualizing.tex.TexWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author nastya
 */
public class Automator {

    private NFA graph = null;
    private NFA epsGraph = null;
    private Table table = null;
    private String pattern = null;
    private NFAWalker graphWalker = null;
    private TableWalker tableWalker = null;
    public DFAWalker dfaWalker = null;
    public EdgeGraphWalker minDFAWalker = null;
    private File texFile = null;
    private File dotNFAFile = null;
    private File dotEpsNFAFile = null;
    private File dotDFAFile = null;
    private File dotMinGraphFile = null;
    private String DOT_NFA_FILENAME = "DOTNFA";
    private String DOT_DFA_FILENAME = "DOTDFA";
    private String DOT_MIN_DFA_FILENAME = "DOTMINDFA";
    private String DOT_EPS_NFAFILENAME = "DOTEPSNFA";
    private String TEX_TABLE_FILENAME = "TABLENFA";

    public Automator(String pattern) {
        this.pattern = pattern;
        table = new Table();
    }

    public void compile() throws ParserException, NFAWalkerException,
            NoConditionsException, DotException, IOException {
        try {
            PatternParser parser = new PatternParser(pattern);
            graph = parser.parse();
            epsGraph = graph.clone();
            NFAWorker.makeClosure(graph);
            table = NFAWorker.generateTable(graph);
            table.fillTable();
            graphWalker = new NFAWalker(graph);
            tableWalker = new TableWalker(graph, table);
            texFile = new TexWriter(table).generateFile(TEX_TABLE_FILENAME);
            DFA dfa = DFAWorker.convertFromNFA(graph);
            MinimizedDFAWorker mDfaW = new MinimizedDFAWorker();
            MinimizedDFA dfaInMinView = mDfaW.convertFromNFAToMinimizedDFA(dfa);
            Minimisation m1 = new Minimisation(dfaInMinView);
            MinimizedDFA minDfa = m1.minimize();
            dfaWalker = new DFAWalker(dfa);
            minDFAWalker = new EdgeGraphWalker(minDfa);
            dotNFAFile = new DotUtils().generateDotFileForNFA(graph, DOT_NFA_FILENAME);
            dotEpsNFAFile = new DotUtils().generateDotFileForNFA(epsGraph, DOT_EPS_NFAFILENAME);
            dotMinGraphFile = new DotUtils().edgeDot(minDfa, DOT_MIN_DFA_FILENAME);
            dotDFAFile = new DotUtils().generateDotFileForDFA(dfa, DOT_DFA_FILENAME);
        } catch (NullPointerException ex) {
            throw new NoConditionsException();
        }

    }

    public boolean matchNFAGraph(String s) {
        return graphWalker.check(s);
    }

    public boolean matchTable(String s) {
        return tableWalker.check(s);
    }

    public boolean matchDFAGraph(String s) {
        return dfaWalker.check(s);
    }

    public boolean matchMinGraph(String s) {
        return minDFAWalker.check(s);
    }

    public File getTexFile() {
        return texFile;
    }

    public File getDotNFAFile() {
        return dotNFAFile;
    }

    public File getDotDFAFile() {
        return dotDFAFile;
    }

    public File getDotEpsNFAFile() {
        return dotEpsNFAFile;
    }

    public File getDotMinGraphFile() {
        return dotMinGraphFile;
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
