package regparser.main;

public class Lexer extends Lexem{
	private String str;
	private int pointer = 0;
	
	public Lexem getCurrent() {
		Lexem lexem = new Lexem();
		if(!isEOL())
		{
			if(Character.isLetter( str.charAt(pointer) ))
			{
				lexem.type = LexemKind.NODE;
				lexem.val = str.charAt(pointer);
			}
			else if('?' == str.charAt(pointer))
			{
				lexem.type = LexemKind.VOPROS;
			}
			else if('*' == str.charAt(pointer))
			{
				lexem.type = LexemKind.ZVEZDA;
			}
			else if('|' == str.charAt(pointer))
			{
				lexem.type = LexemKind.VERTICALBAR;
			}
			else if('(' == str.charAt(pointer))
			{
				lexem.type = LexemKind.BRACKETOPEN;
			}
			else if(')' == str.charAt(pointer))
			{
				lexem.type = LexemKind.BRACKETCLOSE;
			}
			else
			{
				lexem.type = LexemKind.UNDEFINED;
			}
		}
		else lexem.type = LexemKind.EOL;
		
		return lexem;
	}
	public Lexem getNext() {
		next();
		Lexem result = getCurrent();
		prev();
		return result;
	}
	
	public Lexer(String value) {
		str = value;
	}
	
	private boolean isEOL() {
		if(pointer+1 > str.length()) return true;
		else return false;
	}

	public void next() {
		if(pointer < str.length())
			pointer++;
	}
	
	public void prev() {
		if(pointer > 0)
			pointer--;
	}
	
	public int getPointer() {
			return pointer;
	}
}
