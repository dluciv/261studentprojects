package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Main {

    public static void main(String[] args) {

        boolean verbose = false;
        int parIndex = 0;
        if (args[0] == "-t") {
            parIndex++;
            verbose = true;
        }


        Machine m = MachineFactory.createMachine(args[parIndex], args[parIndex + 1]);

        if (verbose)
            System.out.print(m);
        while (m.iterate(verbose))
            if (verbose)
                System.out.print(m);
        System.out.print(m);
    }
}
