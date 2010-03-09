
//autor MurKa

public class NodOfTree
{
    String symbol;
    NodOfTree left;
    NodOfTree right;
    public NodOfTree(String symbol, NodOfTree left, NodOfTree right)
    {
        this.symbol  = symbol;
        this.left = left;
        this.right = right;
    }

    public NodOfTree parser(String exp)
    {
        exp = exp.replaceAll(" ","");
        boolean highPrior = false;
        boolean  lowPrior = false;
        int i =exp.length()-1;
        int counter =0;
        int firstSymbolHighPrior = 0;
        int firstSymbolLowPrior = 0;
        int beginSubExp = 0;
        int endSubExp = 0;
        while(i>=0)
        {
            if (exp.charAt(i)=='(')
            {
                counter--;
                if (counter == 0)
                {
                    beginSubExp = i;
                }
            }
            if (exp.charAt(i)==')')
            {
                if (counter == 0)
                {
                    endSubExp = i;
                }
                counter++;
            }

            if (((exp.charAt(i)=='*')||(exp.charAt(i)=='/'))&&(counter == 0))
            {
                if (!(highPrior))
                {
                    firstSymbolHighPrior = i;
                }
                highPrior = true;
            }
            if (((exp.charAt(i)=='+')||(exp.charAt(i)=='-'))&&(counter == 0))
            {
                if (!(lowPrior))
                {
                    firstSymbolLowPrior = i;
                }
                lowPrior = true;
            }
            i--;
        }
        if (!(highPrior || lowPrior))
        {
            if (beginSubExp != endSubExp)
            {
                return parser(exp.substring(beginSubExp+1,endSubExp)) ;
            }
            else
            {
                if (exp.equals(""))
                {
                    return new NodOfTree("0", null, null);
                }
                else
                {
                    return new NodOfTree(exp, null, null);
                }
            }
        }
        else
        {
            if (lowPrior)
            {
                return new NodOfTree(exp.substring(firstSymbolLowPrior,firstSymbolLowPrior+1),
                        parser(exp.substring(0,firstSymbolLowPrior)),
                        parser(exp.substring(firstSymbolLowPrior+1)));
            }
            else
            {
                return new NodOfTree(exp.substring(firstSymbolHighPrior,firstSymbolHighPrior+1),
                        parser(exp.substring(0,firstSymbolHighPrior)),
                        parser(exp.substring(firstSymbolHighPrior+1)));
            }
        }
    }

    public String toString()
    {
        if (left == null)
        {
            return " "+ symbol;
        }
        else
        {
            return left.toString() + right.toString()+" "+symbol;
        }
    }

}
