package turingmachine;

/**
 *
 * @author Кирилл
 */
import java.util.*;
import java.io.*;

public class Machine {

    private Vector<String> tape = new Vector<String>();
    private String first,  fin;
    private HashMap<InitialCondition, Action> rules = new HashMap<InitialCondition, Action>();
    private int tapePointer = 0;

    private void interpret() {
        String newState, newSymbol, direction;

        InitialCondition currentCondition = new InitialCondition(first, tape.get(tapePointer));
        while (!currentCondition.getState().equals(fin)) {
            printTape();
            Action act = rules.get(currentCondition);
            if (act == null) {
                System.out.println("Machine reject");
                break;
            }
            printRule(currentCondition, act);
            newState = act.getState();
            newSymbol = act.getSymbol();
            direction = act.getDirection();
            tape.set(tapePointer, newSymbol);
            currentCondition.setState(newState);
            move(direction);
            currentCondition.setSymbol(tape.get(tapePointer));
        }
        System.out.println();
    }

    private void getInputData(BufferedReader in) {
        try {
            prepareTape();
            String inputLine = in.readLine();
            for (int i = 0; i < inputLine.length(); ++i) {
                tape.add("" + inputLine.charAt(i));
            }
        } catch (IOException ioe) {
        }

    }

    private void test(String path) {
        BufferedReader in;
        if (path.equals("console")) {
            in = new BufferedReader(new InputStreamReader(System.in));
            getInputData(in);
            interpret();
        } else {
            try {
                File fpath = new File(path);
                in = new BufferedReader(new FileReader(path));
                ArrayList<String> strings = getStrings(fpath);
                for (int i = 0; i < strings.size(); ++i) {
                    getInputData(in);
                    interpret();
                }
            } catch (IOException ioe) {
            }
        }
    }

    private void move(String direction) {
        if (direction.equals("RIGHT") || direction.equals("R")) {
            if (tapePointer == tape.size() - 1) {
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
        tm = MachineParser.getMachine(Paths.XML_PATH);
        tm.test(Paths.TEST);
    }

    private void printTape() {
        for (String tapeSymbol : tape) {
            System.out.print(tapeSymbol);
        }
        System.out.print("   ");
    }

    private void printRule(InitialCondition condition, Action act) {
        System.out.println(condition.state + " " + condition.symbol +
                "->" + act.state + " " + act.symbol + " " + act.getDirection());
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

    private static ArrayList<String> getStrings(File fileName) {
        ArrayList<String> strings = new ArrayList<String>();

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
