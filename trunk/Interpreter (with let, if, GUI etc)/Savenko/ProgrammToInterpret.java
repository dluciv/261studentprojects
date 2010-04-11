package savenko;


public class ProgrammToInterpret {
    
    private static Sequence sequence;
    private Interpreter interpreter;
    
    public ProgrammToInterpret(Parser parser) throws ParserException {
        sequence = parser.getSequence();
    }
    
    public Sequence getSequence(){
        return sequence;
    }
    
    public void Interpret(){
        interpreter = new Interpreter();
        try {
            interpreter.interpret(sequence);
        }
        catch (NullIDException e) {
            System.out.print("NullIDException");
        }
        catch (InterpreterException e) {
            System.out.print("unknown");
        }
    }

}
