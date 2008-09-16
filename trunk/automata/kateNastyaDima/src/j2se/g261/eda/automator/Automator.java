/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.GraphWorkerInterface;

/**
 *
 * @author nastya
 */
public class Automator {

    private String pattern;
    private Graph graph;
    //It must be ENTITY 
    private GraphWorkerInterface worker;

    public Automator(String s) {
        pattern = s;
        makeGraph();
    }
    
    private void makeGraph(){
        graph = startParse(pattern);
        graph = worker.markAllNodes(graph);
        graph = worker.makeClosure(graph);
        graph = worker.removeEpsilonNodes(graph);
    }

    public boolean match(String s) {
        return true;
    }

    private Graph startParse(String s){
        return null;
    }
    
    private Graph parse(String s) {
        Regex r = split(pattern);
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
                    res = worker.concatenateOR(res, g1);
                    regex = split(regex.getRest(), RegexType.OR);
                }
                return res;
            }
            case ONE: {
                return worker.concatenateONE(parse(r.getPat()));
            }
            case ANY: {
                return worker.concatanateANY(parse(r.getPat()));
            }
            case AND: {
                String pat = r.getPat();
                Graph res = new Graph();

                Regex regex = split(pat, RegexType.AND);
                while (regex.getRest().length() != 0) {
                    Graph g1 = parse(r.getPat());
                    res = worker.concatanateAND(res, g1);
                    regex = split(regex.getRest(), RegexType.AND);
                }
                return res;
            }
            
            default:
                return new Graph();
        }
    }

    private Regex split(String toSplit, RegexType t) {
        //todo fill with some code =)
        return null;
    }
    private Regex split(String toSplit) {
        return null;
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
