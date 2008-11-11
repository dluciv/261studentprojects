/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.visualizing.dot.DotException;
import j2se.g261.eda.automator.visualizing.dot.DotUtils;
import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.representations.nfa.NFAWalker;
import j2se.g261.eda.automator.representations.nfa.NFAWalkerException;
import j2se.g261.eda.automator.representations.nfa.NFAWorker;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.parser.PatternParser;
import j2se.g261.eda.automator.representations.table.Table;
import j2se.g261.eda.automator.representations.table.TableWalker;
import j2se.g261.eda.automator.visualizing.tex.TexWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author nastya
 */
public class Automator {

    private NFA graph = null;
    private NFA determinedGraph = null;
    private Table table = null;
    private String pattern = null;
    private NFAWalker graphWalker = null;
    private TableWalker tableWalker = null;
    private File texFile = null;
    private File dotNFAFile = null;
    private File dotEpsNFAFile = null;

    private File dotDFAFile = null;
    private File dotMinGraphFile = null;

    public Automator(String pattern) {
        this.pattern = pattern;
        table = new Table();
    }

    public void compile() throws ParserException, NFAWalkerException,
            NoConditionsException, DotException, IOException {
        try {
            PatternParser parser = new PatternParser(pattern);
            graph = parser.parse();
            NFAWorker.makeClosure(graph);
            determinedGraph = graph.clone();
//            NFAWorker.makeDeterministic(determinedGraph);
            graph.fillDeterminatedTable(table);
            table.fillTable();
            graphWalker = new NFAWalker(determinedGraph);
            tableWalker = new TableWalker(graph, table);
            texFile = new TexWriter(table).generateFile();
            dotNFAFile = new DotUtils(graph).generateDotFileForNFA("GRAPHNFA");
          /*  Minimisation m1 = new Minimisation();
            m1.transform(graph);
            m1.minimize();


            dotMinGraphFile = m1.edgeDot("DOTMIN"); */
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
        return true;
    }

    public boolean matchMinGraph(String s) {
        return true;
    }

    public File getTexFile(){
        return texFile;
    }
    public File getDotNFAFile(){
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