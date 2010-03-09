/*
 * JUnit test for Exception By Savenko Maria (c)2009
 */

package msavenko;

import java.lang.System;

public class exception {
    
    public static void checkTheInterface(IAskAnswer askAnswerInterface)
            throws IllegalArgumentException {
        if (askAnswerInterface == null)
            throw new IllegalArgumentException("Argument can not be null");
    }
    
}
