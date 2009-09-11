package art779.regparser.main;

/**
 * Class is a parser. getNFA() returns NFA by regular expression  
 *
 * @author  art779
 * @return  HashMap that represents NFA 
 * @see     getNFA()
 */

public class Parser {
	private Lexer currentLexem;
	
	public Parser(String regstr) {
		currentLexem = new Lexer(regstr);
	}
	
	public Graph getNFA() {
		return pReg();
	}
	
	public Lexer getLexer() {
		return currentLexem;
	}
	
	private Graph pReg() {
		Graph NFA = pExpr();
		while(	LexemKind.EOL != currentLexem.getCurrent().type 
			 && LexemKind.BRACKETCLOSE != currentLexem.getCurrent().type 
			 && LexemKind.UNDEFINED != currentLexem.getCurrent().type )
		{
			if(currentLexem.getCurrent().type == LexemKind.VERTICALBAR)
			{
				currentLexem.next();
				Graph tNFA = pExpr();
				if(!tNFA.graph.isEmpty())
					NFA.mergeParallel(tNFA);
			}
			else if(	LexemKind.EOL != currentLexem.getCurrent().type 
					 && LexemKind.BRACKETCLOSE != currentLexem.getCurrent().type 
					 && LexemKind.UNDEFINED != currentLexem.getCurrent().type )
					return NFA;
			else 
			{
				currentLexem.next();
				Graph tNFA = pExpr();
				NFA.mergeSequence(tNFA);
			}
		}
		return NFA;
	}
		
	private Graph pExpr() {
		Graph NFA = pTerm();
		while(	 LexemKind.EOL  != currentLexem.getCurrent().type 
			  && LexemKind.VERTICALBAR != currentLexem.getCurrent().type 
			  && LexemKind.UNDEFINED != currentLexem.getCurrent().type )
		{
			if(!NFA.graph.isEmpty())
			{
				Graph tNFA = pTerm();
				if(!tNFA.graph.isEmpty())
					NFA.mergeSequence(tNFA);
				else return NFA;
			}
		}
		return NFA;
	}

	private Graph pTerm() {
		Graph NFA = pFactor();
		if(!NFA.graph.isEmpty())
		{
			if(currentLexem.getNext().type == LexemKind.NODE)
			{
				Graph tNFA = pFactor();
				if(!tNFA.graph.isEmpty())
					NFA.mergeSequence(tNFA);
			}
			else if(currentLexem.getNext().type == LexemKind.VOPROS)
			{
				currentLexem.next();
				NFA.buildVopros();
			}
			else if(currentLexem.getNext().type == LexemKind.ZVEZDA)
			{
				currentLexem.next();
				NFA.buildZvezda();
			}
			else
			{
				currentLexem.next();
				return NFA;
			}
			currentLexem.next();
		}
		return NFA;
	}
	private Graph pFactor() {
		Graph NFA;
		if(currentLexem.getCurrent().type == LexemKind.NODE)
		{
			NFA = new Graph();
			NFA.buildNode(currentLexem);
		}
		else if(currentLexem.getCurrent().type == LexemKind.BRACKETOPEN)
		{		
			currentLexem.next();
			NFA = pReg();
		}
		else NFA = new Graph();
		return NFA;
	}
}
