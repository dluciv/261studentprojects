
import j2se.g261.eda.automator.graph.Graph;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nastya
 */
public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("first", true, false);
        g.addNode("second");
        g.addNode("third");
        g.addNode("forth", true, false);
        g.addNode("fifth", false, true);
        
        System.out.println(g);
    }
    
}
