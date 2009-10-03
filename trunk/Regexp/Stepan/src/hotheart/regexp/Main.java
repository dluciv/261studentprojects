/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.regexp;

import hotheart.regexp.AST.*;
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
        AstBuilder builder = new AstBuilder("abcdefg(123718263)?hij");

        AstNode root = builder.parse();
        
        System.out.println(root);
    }

}
