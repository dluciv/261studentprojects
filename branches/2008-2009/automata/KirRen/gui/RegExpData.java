package gui;

import java.util.Vector;

/**
 * @author Murashov Kirill
 */

public class RegExpData
{
    private String regexp;
    private Vector<String> testWords = new Vector<String>();
    private int len;
    private int num;

    public RegExpData(String exp)
    {
        regexp = exp;
        for (int i=0; i<5; i++)
            testWords.add(i,null);
    }

    public void setTest1Word(String word)
    {
        testWords.set(1,word);
    }

    public String getTest1Word()
    {
        return testWords.get(1);
    }

    public void setTest2Word(String word)
    {
        testWords.set(2,word);
    }

    public String getTest2Word()
    {
        return testWords.get(2);
    }

    public void setTest3Word(String word)
    {
        testWords.set(3,word);
    }

    public String getTest3Word()
    {
        return testWords.get(3);
    }

    public void setTest4Word(int length, int number)
    {
        len = length;
        num = number;
    }

    public int getLengthTest4Word()
    {
        return len;
    }

    public int getNumberTest4Word()
    {
        return num;
    }

    public String getExp()
    {
        return regexp;
    }

    public String toString()
    {
        return regexp;
    }
}


