package savenko;

import savenko.AST.Begin;
import savenko.AST.Sequence;
import savenko.AST.If;
import savenko.AST.Value;
import savenko.AST.Print;
import savenko.AST.Multiply;
import savenko.AST.Number;
import savenko.AST.Identifier;
import savenko.AST.Expression;
import savenko.AST.Binding;
import savenko.AST.Plus;
import savenko.AST.Minus;
import savenko.AST.Divide;
import savenko.AST.Tree;


public class Interpreter {
    
    Environment environment;
    
    public Interpreter (){
        environment = new Environment();
    }
    /*
    public Value interpret(ProgrammToInterpret programm) throws InterpreterException{
        return interpret(programm.getSequence());
    }*/
    
    public Value interpret(Sequence sequence) throws InterpreterException{
    	Value val = null;
    	
    	for (Expression statement : sequence.returnStatement()){
     	   val = interpret(statement);
     	}
    	
    	return val;
    }
    
    private Value interpret(Binding binding) throws InterpreterException{
    	Value old_value = null, val;
    	
    	if (environment.ifIdentifierExist(binding.getIdentifier())){
    		old_value = environment.addToEnvironment(binding.getIdentifier(), interpret(binding.getExpression()));
    		val = interpret(binding.getValue());
    		environment.addToEnvironment(binding.getIdentifier(), old_value);
    	}
    	else {
    		environment.addToEnvironment(binding.getIdentifier(), interpret(binding.getExpression()));
    		val = interpret(binding.getValue());
    		environment.removeIdentifier(binding.getIdentifier());
    	}
    	
        return val;
    }
    
    private Value interpret(Plus plus) throws InterpreterException{
        Value left = interpret(plus.LeftNode());
        Value right = interpret(plus.RightNode());
        return new Value(left.getIntValue() + right.getIntValue());
    }
    
    private Value interpret(Minus minus) throws InterpreterException{
        Value left = interpret(minus.LeftNode());
        Value right = interpret(minus.RightNode());
        return new Value(left.getIntValue() - right.getIntValue());
    }
    
    private Value interpret(Divide divide) throws InterpreterException{
        Value left = interpret(divide.LeftNode());
        Value right = interpret(divide.RightNode());
        return new Value(left.getIntValue() / right.getIntValue());
    }
    
    private Value interpret(Multiply multiply) throws InterpreterException{
        Value left = interpret(multiply.LeftNode());
        Value right = interpret(multiply.RightNode());
        return new Value(left.getIntValue() * right.getIntValue());
    }
    
    private Value interpret(Number number) throws InterpreterException{
        Value val = new Value(number.calculate());
        return val;
    }
    
    private Value interpret(Identifier id) throws NullIDException{
    	Value val = environment.getValue(id);
        if (val != null) return val;
        else throw new NullIDException();
    }
    
    private Value interpret(Print id) throws InterpreterException{
        Value val = interpret(id.getExpression());
        System.out.print(val.getIntValue()); //?????????
        return val;
    }
    
    private Value interpret(If id) throws InterpreterException{
        Value val = interpret(id.getClause());
        if (val.getBoolValue()) val = interpret(id.getIfExpression());
        else if (id.getElseExpression() != null) val = interpret(id.getElseExpression());
        
        return val;
    }
    
    private Value interpret(Begin id) throws InterpreterException{
        return interpret(id.getSequence());
    }
    
    private Value interpret(Tree node) throws InterpreterException{
    	if (node instanceof Sequence)
    		return interpret((Sequence) node);
    	else
    	if (node instanceof Number)
    	    return interpret((Number) node);
    	else
    	if (node instanceof Binding)
    		return interpret((Binding) node);
    	else
        if (node instanceof Plus)
            return interpret((Plus) node);
        else 
        if (node instanceof Minus)
            return interpret((Minus) node);
        else
        if (node instanceof Divide)
            return interpret((Divide) node);
        else
        if (node instanceof Multiply)
            return interpret((Multiply) node);
        if (node instanceof Identifier)
        	return interpret((Identifier) node);
        else 
        if (node instanceof Print)
            return interpret((Print) node);
        if (node instanceof If)
            return interpret((If) node);
        if (node instanceof Begin)
            return interpret((Begin) node);
        else new Exception("");
        return null;
    }

}
