package LexerAndParser;

public class Number implements Expression {
    
    private int number;

    Number(int value){
        number = value;
    }

    public int Value(){
        return number;
    }

    /*@Override
    public void print() {
        System.out.print(number);
    }
    */
    public int calculate(){
        return number;
    }

}
