package spusk1;

import java.util.ArrayList;

public class Sequence implements Tree {
    private ArrayList <Expression> LisStatements = new ArrayList<Expression>();
    public void addStatement(Expression expr){
         LisStatements.add(expr);
}
   public void Interpret(){
   //LisStatements.get(0).calculate();
   System.out.println(LisStatements.get(0).calculate());
   }
    public void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int calculate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
