package turingmachine;

/**
 *
 * @author Кирилл
 */
import java.util.HashMap;
import java.util.Vector;
import java.io.*;

public class Machine {

    private Vector<String> tape = new Vector<String>();
    private String first,  fin;
    private HashMap<InitialCondition, Action> rules = new HashMap<InitialCondition, Action>();
    private int tapePointer = 0;

    private void interpet() {
        String newState, newSymbol, direction;
        
        getInputData();
        InitialCondition currentCondition = new InitialCondition(first, tape.get(tapePointer));
        
        while (!currentCondition.getState().equals(fin)) {
            Action act = rules.get(currentCondition); 
            newState = act.getState();
            newSymbol = act.getSymbol();
            direction = act.getDirection();
            tape.set(tapePointer, newSymbol);
            currentCondition.setState(newState);
            move(direction);
            currentCondition.setSymbol(tape.get(tapePointer));
        }
    }

    private void getInputData() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputLine = in.readLine();
            for (int i = 0; i < inputLine.length(); ++i) {
                tape.add("" + inputLine.charAt(i));
            }
        } catch (IOException ioe) {
        }
    }

//    private void writeOnTape(ArrayList<String> input) {
//        tape.addAll(input);
//    }

    private void move(String direction) {
        if (direction.equals("RIGHT") || direction.equals("R")) {
            if(tapePointer == tape.size() - 1 ){
                tape.add(" ");
            }
            ++tapePointer;
        } else if (direction.equals("LEFT") || direction.equals("L")) {
            if (tapePointer == 0) {
                tape.insertElementAt(" ", 0);
            } else {
                --tapePointer;
            }
        }

    }

    public static void runMachine() {
        Machine tm = new Machine();
        tm = XMLDomParser.getMachine(Paths.XML_PATH);
        tm.interpet();
        tm.printTape();
    }

    private void printTape() {
        for (String tapeSymbol : tape) {
            System.out.print(tapeSymbol);
        }
        System.out.println("");
    }

    public HashMap<InitialCondition, Action> getRules() {
        return rules;
    }

    public String getFirst() {
        return first;
    }

    public String getFin() {
        return fin;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
}
