package art779.regparser.main;

/**
 * Class is a parser. getNFA() returns NFA by regular expression  
 *
 * @author  art779
 * @return  HashMap that represents NFA 
 * @see     getNFA()
 */

public class Parser {
	private Lexer reg;
	
	public Parser(String regstr) {
		reg = new Lexer(regstr);
	}
	
	public NDFABuilder getNFA() {
		return pReg();
	}
	
	public Lexer getLexer() {
		return reg;
	}
	
	private NDFABuilder pReg() {
		NDFABuilder NFA = pExpr();
		while(	LexemKind.EOL != reg.getCurrent().type 
			 && LexemKind.BRACKETCLOSE != reg.getCurrent().type 
			 && LexemKind.UNDEFINED != reg.getCurrent().type )
		{
			if(reg.getCurrent().type == LexemKind.VERTICALBAR)
			{
				reg.next();
				NDFABuilder tNFA = pExpr();
				if(!tNFA.graph.isEmpty())
					NFA.mergeParallel(tNFA);
			}
			else if(	LexemKind.EOL != reg.getCurrent().type 
					 && LexemKind.BRACKETCLOSE != reg.getCurrent().type 
					 && LexemKind.UNDEFINED != reg.getCurrent().type )
					return NFA;
			else 
			{
				reg.next();
				NDFABuilder tNFA = pExpr();
				NFA.mergeSequence(tNFA);
			}
		}
		return NFA;
	}
		
	private NDFABuilder pExpr() {
		NDFABuilder NFA = pTerm();
		while(	 LexemKind.EOL  != reg.getCurrent().type 
			  && LexemKind.VERTICALBAR != reg.getCurrent().type 
			  && LexemKind.UNDEFINED != reg.getCurrent().type )
		{
			if(!NFA.graph.isEmpty())
			{
				NDFABuilder tNFA = pTerm();
				if(!tNFA.graph.isEmpty())
					NFA.mergeSequence(tNFA);
				else return NFA;
			}
		}
		return NFA;
	}

	private NDFABuilder pTerm() {
		NDFABuilder NFA = pFactor();
		if(!NFA.graph.isEmpty())
		{
			if(reg.getNext().type == LexemKind.NODE)
			{
				NDFABuilder tNFA = pFactor();
				if(!tNFA.graph.isEmpty())
					NFA.mergeSequence(tNFA);
			}
			else if(reg.getNext().type == LexemKind.VOPROS)
			{
				reg.next();
				NFA.bVopros();
			}
			else if(reg.getNext().type == LexemKind.ZVEZDA)
			{
				reg.next();
				NFA.bZvezda();
			}
			else
			{
				reg.next();
				return NFA;
			}
			reg.next();
		}
		return NFA;
	}
	private NDFABuilder pFactor() {
		NDFABuilder NFA = new NDFABuilder();
		if(reg.getCurrent().type == LexemKind.NODE)
		{
			NFA.bNode(reg);
		}
		else if(reg.getCurrent().type == LexemKind.BRACKETOPEN)
		{		
			reg.next();
			NFA = pReg();
		}
		return NFA;
	}
}
