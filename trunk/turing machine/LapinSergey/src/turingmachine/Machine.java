package turingmachine;

/**
 *
 * @author Кирилл
 */
import java.util.*;
import java.io.*;

public class Machine {
    private Vector<Character> tape = new Vector<Character>();
    private RulesParser ruleParse;
    private int tapePointer = 0;    

    public Machine(String fName) {
        //repr = new ProgrammLog();
        this.ruleParse = new RulesParser(fName);
    }

    private ProgrammLog interpret() {
        ProgrammLog res = new ProgrammLog();
        String newState, direction;
        Character newSymbol;        
        InitialCondition currentCondition = new InitialCondition(ruleParse.first, tape.get(tapePointer));
        while (!currentCondition.getState().equals(ruleParse.fin)) {
            CurrentState tmp = new CurrentState();
            tmp.rule = ruleParse.rules.findByCondition(currentCondition);
                        
            if (tmp.rule == null) {
                res.addState(null);
                return res;
            }

            Action act = ruleParse.rules.findByCondition(currentCondition).getAct();
            tmp.tape = (Vector<Character>)tape.clone();
            tmp.tapePointer = tapePointer;
            res.addState(tmp);

            newState = act.getState();
            newSymbol = act.getSymbol();
            direction = act.getDirection();

            tape.set(tapePointer, newSymbol);
            currentCondition.setState(newState);
            move(direction);
            currentCondition.setSymbol(tape.get(tapePointer));            
        }        
        return res;
    }

    private void getInputData(BufferedReader in) {
        try {
            prepareTape();
            String inputLine = in.readLine();
            for (int i = 0; i < inputLine.length(); ++i) {
                tape.add(inputLine.charAt(i));
            }
        } catch (IOException ioe) {
        }

    }

    public Vector<TestLog> testFromFileLog(String path) {
        Vector<TestLog> res = new Vector<TestLog>();
        BufferedReader in;
        try {
            File fpath = new File(path);
            in = new BufferedReader(new FileReader(path));
            Vector<String> strings = getStrings(fpath);
            for (int i = 0; i < strings.size(); ++i) {
                TestLog tmp = new TestLog();
                getInputData(in);
                tmp.inittape = (Vector<Character>)tape.clone();
                tmp.log = interpret();
                res.add(tmp);
            }
            } catch (IOException ioe) {
        }
        return res;
    }
    
    public String testFromFile(String path) {
        String res = "";
        BufferedReader in;
        try {
            File fpath = new File(path);
            in = new BufferedReader(new FileReader(path));
            Vector<String> strings = getStrings(fpath);
            for (int i = 0; i < strings.size(); ++i) {
                TestLog tmp = new TestLog();
                getInputData(in);
                tmp.inittape = (Vector<Character>)tape.clone();
                tmp.log = interpret();
                res += tmp.getString();                
            }
            } catch (IOException ioe) {
        }
        return res;
    }

    public TestLog testOnStringLog(String str) {
        prepareTape();
        TestLog tmp = new TestLog();
        if(str != null)
            for (int i = 0; i < str.length(); i++) {
                tape.add(str.charAt(i));
            }
        tmp.inittape = (Vector<Character>)tape.clone();
        tmp.log = interpret();
        return tmp;
    }

    public String testOnString(String str) {
        String res = "";
        prepareTape();
        TestLog tmp = new TestLog();
        if(str != null)
            for (int i = 0; i < str.length(); i++) {
                tape.add(str.charAt(i));
            }
        tmp.inittape = (Vector<Character>)tape.clone();
        tmp.log = interpret();
        res += tmp.getString();
        return res;
    }

    private void move(String direction) {
        if (direction.equals("RIGHT") || direction.equals("R")) {
            if (tapePointer == tape.size() - 1) {                
                tape.add(' ');
            }
            ++tapePointer;
        } else if (direction.equals("LEFT") || direction.equals("L")) {
            if (tapePointer == 0) {
                tape.insertElementAt(' ', 0);
            } else {
                --tapePointer;
            }
        }

    }

    private void printTape() {
        System.out.print(getTape());
    }

    private String getTape() {
        String res = "";
        for (Character tapeSymbol : tape) {
            res += tapeSymbol;
        }
        res += "   ";
        return res;
    }

    private void printRule(InitialCondition condition, Action act) {
        System.out.println(getRule(condition, act));
    }

    private String getRule(InitialCondition condition, Action act) {
        return condition.state + " " + condition.symbol +
                "->" + act.state + " " + act.symbol + " " + act.getDirection();
    }

    public RulesTable getRules() {
        return ruleParse.rules;
    }

    public String getFirst() {
        return ruleParse.first;
    }

    public String getFin() {
        return ruleParse.fin;
    }

    public void setFirst(String first) {
        this.ruleParse.first = first;
    }

    public void setFin(String fin) {
        this.ruleParse.fin = fin;
    }

    private static Vector<String> getStrings(File fileName) {
        Vector<String> strings = new Vector<String>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while (in.ready()) {
                strings.add(in.readLine());
            }

        } catch (IOException e) {
        }
        return strings;

    }

    private void prepareTape() {
        tape.clear();
        tapePointer = 0;
    }
}
