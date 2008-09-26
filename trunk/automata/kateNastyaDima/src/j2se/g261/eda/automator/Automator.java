/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.Node;
import j2se.g261.eda.automator.parser.Parser;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.table.Table;
import j2se.g261.eda.automator.table.TableRecord;

/**
 *
 * @author nastya
 */
public class Automator {

    private Graph graph;
    private Parser parser;
    private Table table;

    public Automator(String s) {
        parser = new Parser(s);
        table = new Table();
    }

    public void compile() throws ParserException {
        makeGraph();
    }

    private void makeGraph() throws ParserException {
//        graph = parser.parse();
//        GraphWorker.makeClosure(graph);
    }

    public boolean match(String s) {
        System.out.println(this);
        return true;
    }

    @Override
    public String toString() {
        return graph.toString();
    }

    private void createDeterminatedTable() {
        table.clear();
        int a = graph.allSize();
        for (int i = 0; i < a; i++) {
            writeNodeInfoToTable(graph.getNodeFromAllAt(i));
        
        }
    }

    private void writeNodeInfoToTable(Node n) {
        TableRecord t = new TableRecord();
        
        if(Node.isEndNode(n)){
            t.add(TableRecord.SYMBOL_END, 0);
        }
        int a = n.getOutgoingSize();
        for (int i = 0; i < a; i++) {
            Node n1 = n.getOutgoingAt(i);
            t.add(n1.getName(), graph.getNodeIndex(n1));
        }
        table.add(graph.getNodeIndex(n), t);
    }
}
