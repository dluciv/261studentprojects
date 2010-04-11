package savenko;

//import java.util.Scanner;

public class Programm {
    
    //public static Sequence sequence;
    private static ProgrammToInterpret programm;
    
    public static void main(String[] args){
    	//"W:/WorkspaceForJava/Interpriter/src/savenko/1.txt"
        Lexer lexer = new Lexer("F:/Program Files/Workspace for Java/Interpriter/src/savenko/1.txt");
        try {
			programm = new ProgrammToInterpret(new Parser(lexer));
		} catch (SyntaxesException e) {
			System.out.print("Semicolon expected");
		} catch (ParserException e){
            System.out.print("parser error");
        }
        
        programm.Interpret();
    }

}
