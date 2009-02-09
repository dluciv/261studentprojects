package mulya;

import Regexp.Transitions;
import Regexp.Transition;
import Regexp.NFA;

import java.util.HashMap;
import java.util.Vector;
import java.util.Set;
import java.util.Arrays;


public class TexTable
{
    public static final String PRE_DOC = "\\documentclass[10pt]{article}\n" +
            "\\textwidth 115mm\n" +
            "\\textheight 170mm\n" +
            "\\usepackage[russian]{babel}\n" +
            "\\usepackage{graphicx}\n" +
            "\\DeclareGraphicsRule{.bmp}{bmp}{}{}\n" +
            "\\begin{document}\n";
    public static final String POST_DOC = "\n\\end{document}";

    private HashMap<Integer, Transitions> map;
    private String table[][];

    public TexTable(HashMap<Integer, Transitions> map)
    {
        this.map = map;

        Set<Integer> keysFromMap = map.keySet();
        Vector<Character> chars = getVectorCharFromMap();
        Integer[] keys = new Integer[keysFromMap.size()];

        int i=0;
        for ( Integer k : keysFromMap )
        {
            keys[i]=k;
            i++;
        }
        Arrays.sort(keys);

        table = new String[keys.length + 1][chars.size() + 1];

        for (i=0; i < keys.length + 1; i++)
        {
            for(int j = 0; j < chars.size() + 1; j++)
            {
                table[i][j] = "";
            }
        }

        i = 1;
        for ( Integer k : keys )
        {
            table[i][0] = "" + k;
            i++;
        }

        i = 1;
        for ( Character ch : chars )
        {
            table[0][i] = "" + ch;
            i++;
        }

        for (i=0; i < keys.length; i++ )
        {
            for(Transition tr: map.get(keys[i]).getVectorOfTransition())
            {
                char c = tr.getChar();
                if ( c == NFA.EMPTY )
                    c = '.';
//                System.out.println(c + ", ");

                table[i+1][chars.indexOf(c) + 1] += tr.getTo() + " ";
            }
        }
    }

    public String getTexTable()
    {
        String res = PRE_DOC;

        if ( table.length == 0 || table[0].length == 0 )
            return res + "Таблицу строить не из чего" + POST_DOC;

        res += "\\begin{tabular}{|";
        for ( int i = 0; i < table[0].length; i++)
            res += "c|";

        res += "}\n\\hline\n";
        for ( int i = 0; i < table.length; i++ )
        {
            for (int j = 0; j < table[i].length; j++ )
            {
                res += table[i][j];
                if ( j < table[i].length - 1 )
                    res += " & ";
                else
                    res += " \\\\\n";
            }
            res += "\\hline\n";
        }

        res += "\\end{tabular}";

        return res + POST_DOC;
    }

    private Vector<Character> getVectorCharFromMap()
    {
        Vector<Character> res = new Vector<Character>();

        for (int i: map.keySet())
        {
            for(Character c:map.get(i).getAllChars())
            {
                if ( c == NFA.EMPTY )
                    c = '.';

                if ( !res.contains(c))
                {
                    res.add(c);
                }
            }
        }
        return res;
    }

}
