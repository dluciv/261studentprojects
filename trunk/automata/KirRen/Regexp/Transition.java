package Regexp;

public class Transition
{
    char c;
    Integer to;

    public Transition(char c, Integer to) {
        this.c = c;
        this.to = to;
    }

    public char getChar()
    {
        return c;
    }

    public Integer getTo()
    {
        return to;
    }
}
