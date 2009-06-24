package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;
import java.util.*;

public class DBGenerator {

    static private String FOLDER = "D:\\DB\\"; 
    static private String SURNAMES = FOLDER + "names.txt";
    static private ArrayList<String> INITIALS = new ArrayList<String>();
    static private Random generator = new Random();
    static private ArrayList<String> names = new ArrayList<String>();
    static private BufferedReader reader;
    static private BufferedWriter writer;
    static private int SURNAME_SIZE = 14;

    DBGenerator(String fileName) throws IOException {
        File db = new File( FOLDER + fileName );
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( SURNAMES ))));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(db)));
    }

    public void generate(int recordsNum) throws FileNotFoundException, IOException {
        setSurnames();
        setInitials();
        for (int i = 0; i < recordsNum; ++i) {
            writeSurname();
            writeInitials();
            writeTelNum();
            writer.newLine();

        }
        writer.close();
    }

    private void setSurnames() throws IOException {
        while (reader.ready()) {
            names.add(reader.readLine());
        }
        reader.close();
    }

    private void setInitials() {
        for (char c = 'А'; c <= 'Я'; ++c) {
            if (c != 'Й' && c != 'Ь' && c != 'Ъ' && c != 'Щ' && c != 'Ё' && c != 'Ы' 
                    && c != 'Ч' && c != 'Ц' && c!= 'Х' && c!= 'Ш') {
                INITIALS.add("" + c);
            }
        }
    }

    private void writeInitials() throws IOException {
        writer.write(INITIALS.get(generator.nextInt(23)) + ".");
        writer.write(INITIALS.get(generator.nextInt(23)) + ".");
    }

    private void writeTelNum() throws IOException {
        String telNum = "";
        for (int i = 0; i < 7; ++i) {
            telNum += generator.nextInt(9);
        }
        writer.write(telNum);
    }

    private void writeSurname() throws IOException {
        int randomNum = generator.nextInt(252);
        String surname = names.get(randomNum);
        if (randomNum % 2 == 1) {
            surname += "а";
        }
        writer.write(surname);
        for (int i = surname.length(); i <= SURNAME_SIZE; ++i) {
            writer.write(" ");
        }
    }
//    private void setSurnameSize() {
//        int maxLength = 0;
//        int length;
//        for (String surname : names) {
//            length = surname.length();
//            if (length > maxLength) {
//                maxLength = length;
//            }
//        }
//        SURNAME_SIZE = maxLength + 2;
//        System.out.print(SURNAME_SIZE);
//    }

}
