/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.study.emailchecker;

import java.util.regex.Pattern;

/**
 *
 * @author HotHeart
 */
public class EMailChecker {
    
    private static Pattern emailPattern = Pattern.compile("[a-zA-Z_]([a-zA-Z_0-9]*)(\\.[a-zA-Z0-9_]+)*@([a-zA-Z0-9-]+\\.)+(ru|cc|info|com|museum|su)");

    public static boolean checkEMail(String data)
    {
         return emailPattern.matcher(data).matches();
    }
}
