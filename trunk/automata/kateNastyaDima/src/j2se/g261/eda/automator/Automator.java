/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.GraphWorker;

/**
 *
 * @author nastya
 */
public class Automator {

    private String pattern;
    private Graph graph;

    public Automator(String s) {
        pattern = s;
        makeGraph();
    }
   
    public void compile(){
        makeGraph();
    }
    
    private void makeGraph(){
        graph = parse(pattern);
        graph = GraphWorker.markAllNodes(graph);
        graph = GraphWorker.makeClosure(graph);
        graph = GraphWorker.removeEpsilonNodes(graph);
    }

    public boolean match(String s) {
        return true;
    }

    private Graph startParse(String s){
        return null;
    }
    
    private Graph parse(String s) {
        Regex r = split(s);
        RegexType t = r.getType();
        
        switch (t) {
            case NOTHING: {
                Graph g = new Graph();
                g.addNode(r.getPat());
                return g;
            }
            case OR: {
                String pat = r.getPat();
                Graph res = new Graph();

                Regex regex = split(pat, RegexType.OR);
                while (regex.getPat().length() != 0) {
                    Graph g1 = parse(r.getPat());
                    res = GraphWorker.concatenateOR(res, g1);
                    regex = split(regex.getRest(), RegexType.OR);
                }
                return res;
            }
            case ONE: {
                return GraphWorker.concatenateONE(parse(r.getPat()));
            }
            case ANY: {
                return GraphWorker.concatanateANY(parse(r.getPat()));
            }
            case AND: {
                String pat = r.getPat();
                Graph res = new Graph();

                Regex regex = split(pat, RegexType.AND);
                while (regex.getRest().length() != 0) {
                    Graph g1 = parse(r.getPat());
                    res = GraphWorker.concatanateAND(res, g1);
                    regex = split(regex.getRest(), RegexType.AND);
                }
                return res;
            }
            
            default:
                return new Graph();
        }
    }

    private Regex split(String toSplit, RegexType t) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private Regex split(String toSplit) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class Regex {

        private RegexType type;
        private String pat;
        private String rest;

        public Regex(RegexType type, String rest, String pat) {
            this.type = type;
            this.pat = pat;
            this.rest = rest;
        }

        public String getPat() {
            return pat;
        }

        public RegexType getType() {
            return type;
        }

        public String getRest() {
            return rest;
        }
        
    }

    enum RegexType {

        NOTHING, OR, ONE, ANY, AND
    }
}
