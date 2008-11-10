package art779.regparser.main;
import ru.sscc.util.*;

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
	
	public NFABuilder getNFA() {
		return pReg();
	}
	
	private NFABuilder pReg() {
		NFABuilder NFA = pExpr();
		while(	LexemKind.EOL != reg.getCurrent().type 
			 && LexemKind.BRACKETCLOSE != reg.getCurrent().type 
			 && LexemKind.UNDEFINED != reg.getCurrent().type )
		{
			if(reg.getCurrent().type == LexemKind.VERTICALBAR)
			{
				reg.next();
				NFABuilder tNFA = pExpr();
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
				NFABuilder tNFA = pExpr();
				NFA.mergeSequence(tNFA);
			}
		}
		return NFA;
	}
		
	private NFABuilder pExpr() {
		NFABuilder NFA = pTerm();
		while(	 LexemKind.EOL  != reg.getCurrent().type 
			  && LexemKind.VERTICALBAR != reg.getCurrent().type 
			  && LexemKind.UNDEFINED != reg.getCurrent().type )
		{
			if(!NFA.graph.isEmpty())
			{
				NFABuilder tNFA = pTerm();
				if(!tNFA.graph.isEmpty())
					NFA.mergeSequence(tNFA);
				else return NFA;
			}
		}
		return NFA;
	}

	private NFABuilder pTerm() {
		NFABuilder NFA = pFactor();
		if(!NFA.graph.isEmpty())
		{
			if(reg.getNext().type == LexemKind.NODE)
			{
				NFABuilder tNFA = pFactor();
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
	private NFABuilder pFactor() {
		NFABuilder NFA = new NFABuilder();
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
