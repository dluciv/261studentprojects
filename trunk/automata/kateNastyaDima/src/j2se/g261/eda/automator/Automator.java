/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.dot.DotException;
import j2se.g261.eda.automator.dot.DotUtils;
import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.GraphWalker;
import j2se.g261.eda.automator.graph.GraphWorker;
import j2se.g261.eda.automator.graph.WalkerException;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.parser.PatternParser;
import j2se.g261.eda.automator.table.Table;
import j2se.g261.eda.automator.table.TableWalker;
import j2se.g261.eda.automator.tex.TexWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author nastya
 */
public class Automator {

    private Graph graph = null;
    private Graph determinedGraph = null;
    private Table table = null;
    private String pattern = null;
    private GraphWalker graphWalker = null;
    private TableWalker tableWalker = null;
    private File texFile = null;
    private File dotFile = null;

    public Automator(String pattern) {
        this.pattern = pattern;
        table = new Table();
    }

    public void compile() throws ParserException, WalkerException, 
            NoConditionsException, DotException, IOException {
        try {
            PatternParser parser = new PatternParser(pattern);
            graph = parser.parse();
            GraphWorker.makeClosure(graph);
//            determinedGraph = graph.clone();
            graph.fillDeterminatedTable(table);
            table.fillTable();
//            graphWalker = new GraphWalker(determinedGraph);
            tableWalker = new TableWalker(graph, table);
            texFile = new TexWriter(table).generateFile();
            dotFile = new DotUtils(graph).generateDotFileForNFA("GRAPHNFA");
        } catch (NullPointerException ex) {
            throw new NoConditionsException();
        }

    }

    public boolean matchGraph(String s) {
        return graphWalker.check(s);
    }

    public boolean matchTable(String s) {
        return tableWalker.check(s);
    }

    public File getTexFile(){
        return texFile;
    }
    public File getDotFile(){
        return dotFile;
    }
    
    @Override
    public String toString() {
        return graph.toString();
    }
}
