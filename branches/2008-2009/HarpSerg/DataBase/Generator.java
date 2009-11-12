/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.io.*;
import java.util.*;
/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class Generator {

    static private ArrayList<String> INITIALS = new ArrayList<String>();
    static private Random generator = new Random();
    static private ArrayList<String> names = new ArrayList<String>();

    public static void main(String[] args) throws IOException{
        if (args.length < 3) {
            System.out.println("How to use: <surnamesFile_path> <new_database_file_path> <number_of_entryes>");
        } else {
            String surnamesFile = args[0], baseFile = args[1];
            int n = Integer.parseInt(args[2]);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( surnamesFile ))));
            BufferedWriter writer = new BufferedWriter ( new OutputStreamWriter(new FileOutputStream(baseFile)));
            generate(n, writer, reader, baseFile);
        }
    }

    public static void generate(int recordsNum, BufferedWriter writer, BufferedReader reader, String target) throws FileNotFoundException, IOException {
        setSurnames(reader);        
        for (int i = 0; i < recordsNum; ++i) {
            writeRecord(writer);
        }
        writer.close();
        System.out.println("\n" + "Generated in " + target);
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
        System.out.println("How to use: java -jar <Generator.jar_path> <surnamesFile_path> <new_database_file_path> <number_of_entryes>");
    }

    

}
