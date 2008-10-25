package j2se.g261.eda.automator.tests;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author nastya
 */
 class TestItem implements Serializable{
    private String pattern;
    private String string;
    private boolean matches;

     TestItem(String pattern, String string, boolean expectedResult) {
        this.pattern = pattern;
        this.string = string;
        this.matches = expectedResult;
    }

    boolean isMatches() {
        return matches;
    }


    String getPattern() {
        return pattern;
    }


    String getString() {
        return string;
    }

    
}
