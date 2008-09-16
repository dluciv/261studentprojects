/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.graph;

/**
 *
 * @author nastya
 */
public interface GraphWorkerInterface {
    
    //slogenie
    public Graph concatenateOR(Graph g1, Graph g2);
    //slogenie s 0
    //dlya togo chto by po zadache razdelyat' dva sluchaya
    public Graph concatenateONE(Graph g1);
    //zvzdochka Klini
    public Graph concatanateANY(Graph g);
    //umnogenie
    public Graph concatanateAND(Graph g1, Graph g2);
    //transitive closure
    public Graph makeClosure(Graph g);
    //remove epsilon-nodes
    public Graph removeEpsilonNodes(Graph g);
    //mark all start and ends nodes
    public Graph markAllNodes(Graph g);
}
