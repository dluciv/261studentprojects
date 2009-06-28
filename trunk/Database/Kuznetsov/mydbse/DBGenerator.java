package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;
import java.util.*;

public class DBGenerator {

    static private ArrayList<String> INITIALS = new ArrayList<String>();
    static private Random generator = new Random();
    static private ArrayList<String> names = new ArrayList<String>();

    public static void main(String[] args) throws IOException{
        String surnamesFile = args[0], baseFile = args[1];
        int n = Integer.parseInt(args[2]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( surnamesFile ))));
        BufferedWriter writer = new BufferedWriter ( new OutputStreamWriter(new FileOutputStream(baseFile)));
        generate(n, writer, reader);
    }

    public static void generate(int recordsNum, BufferedWriter writer, BufferedReader reader) throws FileNotFoundException, IOException {
        setSurnames(reader);
        setInitials();
        for (int i = 0; i < recordsNum; ++i) {
            writeRecord(writer);
        }
        writer.close();
    }

    private static void setSurnames(BufferedReader reader) throws IOException {
        while (reader.ready()) {
            names.add(reader.readLine());
        }
        reader.close();
    }

    private static void setInitials() {
        for (char c = 'A'; c <= 'Y'; ++c) {
            if (c != 'Q' && c != 'X') {
                INITIALS.add("" + c);
            }
        }
    }

    private static void writeRecord(BufferedWriter writer) throws IOException {
        int randomNum = generator.nextInt(252);
        String record = names.get(randomNum);
        if (randomNum % 2 == 1) {
            record += "a";
        }
        for (int i = record.length(); i <= 14; ++i) {
            record += " ";
        }
        record += INITIALS.get(generator.nextInt(23)) + "."+ INITIALS.get(generator.nextInt(23)) + ".";
        record += genTelNum();
        record += "\n";
        writer.write(record);
    }

    private static String genTelNum() throws IOException {
        String telNum = "";
        for (int i = 0; i < 7; ++i) {
            telNum += generator.nextInt(9);
        }
        return telNum;
    }
    private void printHelp(){
        System.out.println("How to use: java -jar <DBGenerator.jar_path> <surnamesFile_path> <new_database_file_path> <number_of_records>");
    }

    

}
