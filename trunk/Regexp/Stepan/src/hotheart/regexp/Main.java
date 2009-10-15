/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.regexp;

import hotheart.regexp.AST.*;
import hotheart.regexp.AST.node.AbstractNode;
import java.text.ParseException;


/**
 *
 * @author m08ksa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        AstBuilder builder = new AstBuilder("abcd|1234");

        AbstractNode root = builder.parse();


        System.out.println("-----------------------------");
        System.out.println("Basic tree visualization:");
        System.out.println(root);
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        System.out.println("GraphViz tree visualization:");
        System.out.println("digraph G{");
        System.out.println(root.getGraphVizString());
        System.out.println("}");
    }

}
