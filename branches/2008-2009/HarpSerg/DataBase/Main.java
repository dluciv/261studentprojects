/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
public class Main {

    /**
     * @param args the command line arguments
     */
    
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Selector selector;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String from;
        String to;
        String request;
        int number;
        Keys prevKeyType = Keys.INIT_VALUE;
        Keys keyType;
        String[] arguments;
        Comparator c;


             
        if (args.length != 1) {
            printHelp();
        } else {
            printRequestHelp();
            selector = new Selector(args[0]);
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
                        number = 0;
                    } else {
                        number = Integer.parseInt(arguments[3]);
                    }
                    keyType = parseKey(arguments[0]);
                    if (!keyType.equals(Keys.ERROR)) {
                        c = new KeyComparator(keyType);
                        if (keyType != prevKeyType) {
                            System.out.println("indexing in progress" + "\n");
                            selector.makeIndex(c);
                            System.out.println("indexing completed" + "\n");
                        }
                        prevKeyType = keyType;
                        long begin = System.nanoTime();
                        ArrayList<Entry> entryes = selector.search(from, to, number);
                        if (entryes.size() != 0) {
                            System.out.println(entryes.size() + " results found in " + (System.nanoTime() - begin) + " ns\n" +
                                    "Press Enter to watch results.\nType \"q\" to stop watching.");
                        } else {
                            System.out.println(entryes.size() + " results found in " + (System.nanoTime() - begin) + " ns\n");
                        }
                        showRecords(entryes, number);
                    }                    
                }
                request = getRequest();
            }
        }

    }

    private static Keys parseKey(String key) {
        if (key.equals("sn")) {
            return Keys.SURNAME;
        } else if (key.equals("tel")) {
            return Keys.TEL;
        } else {
            printRequestHelp();
            return Keys.ERROR;
        }
    }

    public static void showRecords(ArrayList<Entry> entryestoshow, int n) throws IOException {
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        ListIterator<Entry> iter = entryestoshow.listIterator();
        if (n == 0) {
            n = entryestoshow.size();
        }
        if (entryestoshow.size() != 0) {
            while (!inReader.readLine().equals("q")) {
                for (int i = 0; i < n && iter.hasNext(); ++i) {
                    showNthLine(iter.next());
                }
            }
        }
    }

    public static void showNthLine(Entry n) throws IOException {
        System.out.println(n.getSN() + " " + n.getTel());
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
        System.out.println("How to use: <key_type>[:<second_key_type>] " +
                "<from_key>[:<from_second_key>] <to_key>[:<to_second_key>] [<how_many_records_to_show>]");
        System.out.println("* key_type:\n sn - to search by surname\n tel - to search by telephone number\n " +
                "sn:tel or tel:sn to combine");
        System.out.println("* you may not to state how many recrods you need in case you need all of them");
        System.out.println("* type \"q\" if you want to quit");
    }

    private static String getRequest() throws IOException {
        System.out.println("Input your request");
        return reader.readLine();
    }

    private static void printHelp() {
        System.out.println("MyDataBaseSearchEngine Copyright (c) 2009 HarpSerg");
        System.out.println("How to use: <database_file>");
    }
}
