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
        AstNode root = AstBuilder.parse("abcdefg(123718263)?hij");
        
        System.out.print(root);
    }

}
