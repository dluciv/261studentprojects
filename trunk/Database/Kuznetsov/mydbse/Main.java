package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Main {

    /**
     * @param args the command line arguments
     */
    private static int INIT_VALUE = -1;
    private static int ALL = 0;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Searcher yandex;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String from, to, request;
        int number, keyType, prevKeyType = INIT_VALUE;
        String[] arguments;
        yandex = new Searcher("base.txt");
        ArrayList<Integer> lineNums;

//        DBGenerator dbGen = new DBGenerator("base.txt");
//        dbGen.generate(1000000);
        request = reader.readLine();
        while (!request.equals("q")) {
            arguments = request.split(" ");
            if (arguments.length < 3 || arguments.length > 4) {
                printHelp();
            } else {
                from = arguments[1];
                to = arguments[2];
                if (arguments.length == 3) {
                    number = ALL;
                } else {
                    number = Integer.parseInt(arguments[3]);
                }
                if (!arguments[0].equals("sn") && !arguments[0].equals("tel")) {
                    printHelp();
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
                    lineNums = yandex.search(from, to, number);
                    showRecords(lineNums);
                }
            }
            request = reader.readLine();
        }
//        BufferedReader reader_test = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\DB\\base.txt"))));
//        while(reader_test.ready())
//            reader_test.readLine();
    }

    private static void showRecords(ArrayList<Integer> lineNums) throws IOException {
        ListIterator<Integer> iter = lineNums.listIterator();

        System.out.println(lineNums.size() + " results found");
        while (!reader.readLine().equals("q") && iter.hasNext()) {
            yandex.showNthLine(iter.next());
        }
    }

    private static void printHelp() {
        System.out.println("MyDataBaseSearchEngine Copyright (c) 2009 Kirill Kuznetsov");
        System.out.println("How to use: <key_type> <from_key> <to_key> <how_many_records>");
        System.out.println("* key_type:\n sn - to search by surname\n tel - to search by telephone number");
        System.out.println("* you may not to state how many recrods you need in case you need all of them");
        System.out.println("* type \"q\" if you want to quit");

    }
}
