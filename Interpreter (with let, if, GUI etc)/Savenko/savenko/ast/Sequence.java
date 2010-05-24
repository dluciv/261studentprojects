/*
 * узел для последрвательности выражений
 * каждое следущее отделяется от предыдущего точкой с запятой
 * после последнего ставить точку с запятой не обязательно
 * Savenko Maria(c)
 */
package savenko.ast;

import java.util.ArrayList;
import java.util.List;

public class Sequence implements Tree{
    
    private ArrayList<Expression> statements = new ArrayList<Expression>();
    
    public Expression addStatement(Expression new_statement){
    	statements.add(new_statement);
    	return new_statement;
    }

    public List<Expression> returnStatement(){
    	return statements;
    }
}
