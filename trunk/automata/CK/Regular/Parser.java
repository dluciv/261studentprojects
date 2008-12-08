package Regular;
/**
 *
 * @author Кирилл
 */

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

    public static NFA parse( String expr ){
        NFA auto = parseConcat(expr);
    	while ( currentChar(expr) == '|'){
           next();
    	   auto = NFA.buildAltern( auto, parse(expr));
    	}
    	return auto;
    }
    private static NFA parseConcat( String expr){
        NFA auto = parseClosureQuestion( expr);
    	while( isLetter(currentChar(expr))| (currentChar(expr) == '(')){
    		auto = NFA.buildConcat(auto, parseConcat( expr));
    	}
        return auto;
    }
    private static NFA parseClosureQuestion( String expr){
        NFA auto = parseSymb( expr);
    	if( currentChar(expr) == '*'){
            auto = NFA.buildClosure(auto);
            next();
    	} else if( currentChar(expr) == '?'){
            auto = NFA.buildQuestion(auto);
            next();
    	}
        return auto;
    }
    private static NFA parseSymb(String expr){
        NFA auto;
    	if(isLetter(currentChar(expr))){
    	    auto = NFA.buildPrimitive(currentChar(expr))	;
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



