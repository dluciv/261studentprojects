
package LexerAndParser;

public class Identificator implements Expression {

    public String name;

    public Identificator(String name){
        this.name = name;

    }

    public String getName(){
        return name;
    }

}
