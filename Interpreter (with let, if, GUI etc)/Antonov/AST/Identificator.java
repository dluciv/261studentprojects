/*
 * Identificator node
 * Antonov Kirill(c), 2010
 */
package AST;

public class Identificator extends Expression {

    private String identificator;
    
    public Identificator(String new_identificator){
        identificator = new_identificator;
       
    }

    public String GetName(){
        return identificator;
    }

}
