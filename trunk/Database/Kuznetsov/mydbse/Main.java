package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    private static int INIT_VALUE = -1;
    public static int ALL = 0;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Searcher yandex;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String from, to, request;
        int number, keyType, prevKeyType = INIT_VALUE;
        String[] arguments;

        if (args.length != 1) {
            printHelp();
        } else {
            yandex = new Searcher(args[0]);
            fullReading(args[0]);
            request = getRequest();
            while (!request.equals("q")) {
                arguments = request.split(" ");
                if (arguments.length < 3 || arguments.length > 4) {
                    printRequestHelp();
                } else {
                    from = arguments[1];
                    to = arguments[2];
                    if (arguments.length == 3) {
                        number = ALL;
                    } else {
                        number = Integer.parseInt(arguments[3]);
                    }
                    if (!arguments[0].equals("sn") && !arguments[0].equals("tel")) {
                        printRequestHelp();
                    } else {
                        if (arguments[0].equals("tel")) {
                            keyType = Record.TEL;
                        } else {
                            keyType = Record.SURNAME;
                        }
                        if (keyType != prevKeyType) {
                            prevKeyType = keyType;
                            yandex.setKeyType(keyType);
                            yandex.makeIndex();
                        }
                        yandex.search(from, to, number);
                    }
                }
                request = getRequest();
            }
        }

    }

    private static void fullReading(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader_test = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        long begin = System.nanoTime();
        while (reader_test.ready()) {
            reader_test.readLine();
        }
        System.out.println("Full reading time:\n" + (System.nanoTime() - begin) + " ns");
    }

    private static void printRequestHelp() {
        System.out.println("How to use: <key_type> <from_key> <to_key> [<how_many_records_to_show>]");
        System.out.println("* key_type:\n sn - to search by surname\n tel - to search by telephone number");
        System.out.println("* you may not to state how many recrods you need in case you need all of them");
        System.out.println("* type \"q\" if you want to quit");
    }

    private static String getRequest() throws IOException {
        System.out.println("Input your request");
        return reader.readLine();
    }

    private static void printHelp() {
        System.out.println("MyDataBaseSearchEngine Copyright (c) 2009 Kirill Kuznetsov");
        System.out.println("How to use: java -jar <mydbse.jar_path> <database_file>");
    }
}
