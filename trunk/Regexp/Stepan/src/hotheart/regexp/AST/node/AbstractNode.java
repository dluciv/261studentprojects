/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.regexp.AST.node;

/**
 *
 * @author m08ksa
 */
public abstract class AbstractNode {

    public AbstractNode parent;

    @Override
    public abstract String toString();


    private String nodeId = null;
    private static int count = 0;
    public String getNodeId()
    {
        if (nodeId == null)
            nodeId = "N_"+(count++);

        return nodeId;
    }

    public abstract String getGraphVizString();

    protected String getGraphVizNodeString(String label)
    {
        return this.getNodeId() + " [label=\""+ label + "\"] ;\n";
    }

    protected String getChildVizNodeString(AbstractNode child)
    {
        String res = child.getGraphVizString();
        res += getNodeId() +" -> " + child.getNodeId() + " ;\n";
        return res;
    }
}
