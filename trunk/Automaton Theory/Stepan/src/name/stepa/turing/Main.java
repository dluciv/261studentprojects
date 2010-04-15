package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("USAGE:");
            System.out.println("TuringMachine <app_path> <input_data>");
            System.out.println("For testing execute:");
            System.out.println("TuringMachine sum.txt \"111x11=\"");
            return;
        }
        Machine m = MachineFactory.createMachine(args[0], args[1]);

        System.out.print(m.getStateString());
        while (m.iterate(true))
            System.out.print(m.getStateString());
        System.out.print(m.getStateString());
    }
}
