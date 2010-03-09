/*
 * Main for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp;

import hotheart.regexp.AST.*;
import hotheart.regexp.AST.node.AbstractNode;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Korshakov Stepan
 */
public class Main {

    private static AbstractNode build(IBuilder builder) throws ParseException {
        if (builder == null) {
            throw new NullPointerException("argument `builder` is null");
        }
        return builder.parse();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Regular Expresion for parsing(leave it blank to exit):");

            String regexp = in.nextLine();
            if (regexp.equals("")) {
                System.out.println("Exiting...");
                return;
            }

            AstBuilder builder = new AstBuilder(regexp);
            AbstractNode root = build(builder);

            System.out.println("-----------------------------");
            System.out.println("Basic tree visualization:");
            System.out.println(root);
            System.out.println("-----------------------------");
            System.out.println("GraphViz tree visualization:");
            System.out.println("digraph G{");
            System.out.println(root.getGraphVizString());
            System.out.println("}");
            System.out.println("-----------------------------");
        }
    }
}
