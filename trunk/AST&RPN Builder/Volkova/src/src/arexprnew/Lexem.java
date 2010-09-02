package arexprnew;

public class Lexem {
    public enum Operation {
        
        Plus("+"), Minus("-"), Divide("/"), Multiply("*"), LeftBracket("("), RightBracket(")"), END("\n");
        private String s;

        private Operation(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
    };

    private Operation operation;
    private Integer number;

    public Lexem(Operation operation) {
        this.operation = operation;
        number = null;
    }

    public Lexem(int number) {
        operation = null;
        this.number = number;
    }

    public boolean isOperation() {
        return operation != null;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        if (isOperation()) {
            return operation.toString();
        } else {
            return "" + number;
        }
    }
}
   