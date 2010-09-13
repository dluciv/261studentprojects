package arexprnew;

public enum Lexeme {

    // public enum Operation {
    Plus, Minus, Div, Mult, LeftBracket, RightBracket,
    EOL, And, Or, Not, Assignment, True, False,
    Equal, UnEq, Less, Greater, LessEq, GreaterEq,
    LBr, RBr, UnKnown, Semi,
    If, Then, Else, Let, In, Print, Begin, End, Fun, OpFun,
    Number, Id, Rec;
    /* private String s;

    private Operation(String s) {
    this.s = s;
    }

    @Override
    public String toString() {
    return s;
    }
    };

    public Operation operation;
    public Integer number;
    public String variable;

    /*  KeyWords keyWordsTable[] = {
    new KeyWords("if", Words.If),
    new KeyWords("then", Words.Then),
    new KeyWords("else", Words.Else),
    new KeyWords("let", Words.Let),
    new KeyWords("in", Words.In),
    new KeyWords("print", Words.Begin),
    new KeyWords("begin", Words.Begin),
    new KeyWords("end", Words.Fun),
    new KeyWords("->", Words.OpLet)
    };

    public HashMap<String, Operation> keyTable = new HashMap<String, Operation>() {

    {
    put("if", Operation.If);
    put("then", Operation.Then);
    put("else", Operation.Else);
    put("let", Operation.Let);
    put("in", Operation.In);
    put("print", Operation.Print);
    put("begin", Operation.Begin);
    put("end", Operation.Fun);
    put("->", Operation.OpLet);

    }
    };

    public boolean isKeyWord(String kw){
    return (keyTable.containsKey(kw));
    }

    public Lexem(Operation operation) {
    this.operation = operation;
    number = null;
    variable = null;
    }

    public Lexem(int number) {
    operation = null;
    this.number = number;
    variable = null;
    }

    public Lexem(String variable) {
    operation = null;
    number = null;
    this.variable = variable;
    }

    public boolean isOperation() {
    return operation != null;
    }

    public Operation getOperation() {
    return operation;
    }

    public Integer getNumber() {
    return number;
    }

    public String getVariable() {
    return variable;
    }

    @Override
    public String toString() {
    if (isOperation()) {
    return operation.toString();
    } else {
    if (variable.charAt(0) >= 'a' && variable.charAt(0) <= 'z') {
    return variable.toString();
    } else {
    return "" + number;
    }
    }
    }
     */
}


   


   