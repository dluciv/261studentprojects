package regexp;

/**
 * @author Renat Akhmedyanov
 */

import java.util.HashSet;

public class Parser {
    public HashSet<Character> alphabet;
    String regexp;
    int pos;
    static char EOL = 0;

    public Parser(String regexp) {
        this.regexp = regexp;
        pos = 0;
        fillAlphabet();
    }

    private void fillAlphabet() {
        alphabet = new HashSet<Character>();
        for (int i = 0; i < regexp.length(); i++) {
            Character c = regexp.charAt(i);
            if ((c != '*') & (c != '(') & (c != ')') & (c != '|') & (c != '?')) {
                alphabet.add(c);
				//System.out.println("Added to alphabet: "+c);
			}
        }
    }

    private char current() {
        if (pos>=regexp.length()) {
            return EOL;
        } else {
            return regexp.charAt(pos);
        }
    }

    private void next() {
        if (pos<=regexp.length()) {
            pos++;
        }
    }

    public boolean isLetter(char symb) {
        return alphabet.contains(symb);
    }

    public NFA parse() throws ParserException {
        NFA nfa = parseOr();
        if (current() != EOL) {
            throw new ParserException("Unexpected symbol");
        }
        return nfa;
    }

    private NFA parseOr() throws ParserException {
        NFA nfa = parseConcat();
        while (current() == '|') {
            next();
            NFA nfa_temp = parseConcat();
            nfa.applyOr(nfa_temp);
        }
        return nfa;
    }

    private NFA parseConcat() throws ParserException {
        NFA nfa = parseClosure();
        while (isLetter(current()) || current()=='(') {
            NFA nfa_temp = parseClosure();
            nfa.applyConcat(nfa_temp);
        }
        return nfa;
    };

    private NFA parseClosure() throws ParserException {
        NFA nfa = parseSymbol();
        if (current()=='*') {
            next();
            nfa.applyClosure();
        }
        return nfa;
    }

    private NFA parseSymbol() throws ParserException {
        if (isLetter(current())) {
            NFA nfa = NFA.buildLetter(current());
            next();
            return nfa;
        } else if (current() == '(') {
            next();
            NFA nfa = parseOr();
            if (current() != ')') {
                throw new ParserException("Missed closing bracket");
            }
            next();
            return nfa;
        } else {
            throw new ParserException("Unexpected symbol");
        }
    }
}
