package Regular;

public class Parser {
    static int pos = 0 ;
    static char EOL = '#';
    
    public static String markEnd( String expr){
        return expr += EOL;
    }
    public static boolean isLetter(char symb) {
        if ((symb >= 'a') & (symb <= 'z') ) {
            return true;
        } else {
            return false;
        }
    }

    public static Automaton parse( String expr ){
        Automaton auto = parseConcat(expr);
    	while ( currentChar(expr) == '|'){
           next();
    	   auto = Automaton.buildAltern(auto, parse(expr));
    	}
    	return auto;
    }
    private static Automaton parseConcat( String expr){
        Automaton auto = parseClosureQuestion( expr);
    	while( isLetter(currentChar(expr))){
    		auto = Automaton.buildConcat(auto,parseConcat( expr));
    	}
        return auto;
    }
    private static Automaton parseClosureQuestion( String expr){
        Automaton auto = parseSymb( expr);
    	if( currentChar(expr) == '*'){
            auto = Automaton.buildClosure(auto);
            next();
    	} else if( currentChar(expr) == '?'){
            auto = Automaton.buildQuestion(auto);
            next();
    	}
        return auto;
    }
    private static Automaton parseSymb(String expr){
        Automaton auto;
    	if(isLetter(currentChar(expr))){
    	    auto = Automaton.buildPrimitive(currentChar(expr))	;
            next();
    	} else if (currentChar(expr) == '('){
            next();
            auto = parse(expr);
            next();
//            if(isCloseingBracket){
//       	    } else {
//       	    	error;
//       	    }
        } else {
            return null;
        }
   
        return auto;
    }
    private static void next(){
        ++pos;
    }
    private static char currentChar(String expr){
        return expr.charAt(pos);
    }
}



