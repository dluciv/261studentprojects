/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LexerAndParser;

public class Identificator implements Expression {

    public String name;

    public Identificator(String name){
        this.name = name;

    }

    public String getName(){
        return name;
    }

    public void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int calculate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
