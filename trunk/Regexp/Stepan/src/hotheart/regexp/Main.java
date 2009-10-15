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
        AstBuilder builder = new AstBuilder("sdfsdf(1237|18263)*12134");

        AbstractNode root = builder.parse();
        
        System.out.println(root);
    }

}
