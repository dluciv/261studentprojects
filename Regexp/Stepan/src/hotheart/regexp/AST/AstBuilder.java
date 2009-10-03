/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.regexp.AST;

import java.text.ParseException;

/**
 *
 * @author m08ksa
 */
public class AstBuilder {
    public static AstNode parse(String regexp) throws ParseException
    {
        return _parse(regexp, null);
    }
    
    private static AstNode _parse(String regexp, AstNode prev) throws ParseException
    {
        AstNode res = new AstNode();
        res.prev = prev;
        if (prev != null)
            prev.next = res;
            
        if (regexp.isEmpty())
        {
            res.type = AstNode.TYPE_END;
            return res;
        }
        
        char symbol = regexp.charAt(0);
        
        if (symbol == '(')
        {
            int brackets = 0;
            for(int i = 1; i < regexp.length(); i++)
            {
                if (regexp.charAt(i) == ')')
                {
                    brackets--;
                    if (brackets < 0)
                    {
                        res.type = AstNode.TYPE_GROUP;
                        res.subNodes = new AstNode[1];
                        res.subNodes[0] = _parse(regexp.substring(1, i-1), res);
                        
                        _parse(regexp.substring(i+1), res);
                        return res;
                    }
                }
                
                if (regexp.charAt(i) == '(')
                {
                    brackets++;
                }
            }
            
            throw new ParseException("Brackets error!", 0);
        }
        if ((symbol == '*')||(symbol == '+')||(symbol == '?'))
        {
            if (symbol == '*')
                res.type = AstNode.TYPE_STAR;
            else if (symbol == '+')
                res.type = AstNode.TYPE_PLUS;
            else if (symbol == '?')
                res.type = AstNode.TYPE_QUESTION;
            
            res.prev = prev.prev;
            
            if(prev.prev != null)
                prev.prev.next = res;
            
            res.subNodes = new AstNode[1];
            res.subNodes[0] = prev;
            prev.prev = res;
            prev.next = new AstNode();

            _parse(regexp.substring(1), res);
            
            return res;
        }
        else
        {
            res.type = AstNode.TYPE_SYMBOL;
            res.symbol = symbol;
            
            _parse(regexp.substring(1), res);
            return res;
        }
    }
}