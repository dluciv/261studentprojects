

package LexerAndParser;

public class BooleanOp implements Expression{

	boolean ifTrue = false;

	public BooleanOp(boolean TRUE){
		ifTrue = TRUE;
	}

	public boolean getBool(){
		return ifTrue;
	}

}
