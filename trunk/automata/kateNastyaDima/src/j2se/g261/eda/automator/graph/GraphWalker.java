/*
 * GraphWalker.java
 * 
 * Version 1.0
 * 
 * 6.10.2008
 * 
 * (c) "EDA"
 */
package j2se.g261.eda.automator.graph;

/**
 * Make graph detour for cheking if word tenance FA 
 * Process epsilon-closed graph only
 * @author Dmitry
 */
public class GraphWalker {

    private Node node;
/**
 * default constructor http://bash.org.ru/abysstop
 * @param graph walked graph 
 * @throws j2se.g261.eda.automator.graph.WalkerException 
 * if graph not epsilon-closed
 */
    public GraphWalker(Graph graph) throws WalkerException {
        try {
            node = graph.getNodeFromStartsAt(0);
        } catch (Exception ex) {
            throw new WalkerException();
        }

        if (!Node.isStartNode(node)) {
            throw new WalkerException();
        }
    }
/**
 * cheks if word tenances FA
 * @param s string to be checked
 * @return if word tenances FA then <code> true</code> ,<code> false</code> otherwise
 */
    public boolean check(String s) {
        return checkNextElement(s, node);
    }

    private boolean checkNextElement(String rest, Node n) {
        if (rest.length() == 0) {
            for (int i = 0; i < n.getOutgoingSize(); i++) {
                if (Node.isEndNode(n.getOutgoingAt(i))) {
                    return true;
                }
            }
            return false;
        } else {
            char current = rest.charAt(0);
            for (int i = 0; i < n.getOutgoingSize(); i++) {
                System.out.println(n.getName());
                if (n.getOutgoingAt(i).getName() == current && checkNextElement(rest.substring(1),
                        n.getOutgoingAt(i))) {
                    return true;
                }
            }
            return false;
        }

    }
}
