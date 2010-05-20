package karymov;

import java.awt.FileDialog;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;
import lebedev.*;
import yaskov.Interpreter;

public class Controller {

    private IMainForm iMainForm;
    private byte[] buf;
    // String szCurrentFilename = "";

    public Controller(IMainForm iMainForm) {
        this.iMainForm = iMainForm;
    }

    public void openFile(File file) {
        iMainForm.clearConsolePanel();
        try {
            InputStream fileInpStream = new FileInputStream(file);
            int size = fileInpStream.available();
            fileInpStream.close();
            char[] buff = new char[size];
            Reader fileReadStream = new FileReader(file);
            int count = fileReadStream.read(buff);
            iMainForm.getTextPanel().setText(String.copyValueOf(buff));
            fileReadStream.close();
        } catch (FileNotFoundException Exception) {
            iMainForm.setTextInConsolePanel("File can not open");
        } catch (IOException Exception) {
            iMainForm.setTextInConsolePanel("File can't be read");
        }

    }

//   public void runProgramm(textProgramm){
//   Programm programm = new Programm(textProgramm);
//   try{
//       iMainForm.clearConsolePanel();
//       programm.interpret();
//   } catch(Exception e){
//       iMainForm.setTextInConsolePanel("File can not open");
//      }
//   
//  
//   }
    public void saveFile(String textProgramm, String fileName) throws FileNotFoundException, IOException {

        FileOutputStream outputStream = null;
        buf = textProgramm.getBytes();

        try {
            outputStream = new FileOutputStream(fileName);
            outputStream.write(buf);
            outputStream.close();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (SecurityException ex) {
            System.out.println(ex.toString());
        }
    }

    public void saveAsFile(String textProgramm, String fileName) {
    }

    public String runProgram(String input) {
        Lexer lexer = new Lexer(input + '\0');
        lexer.analyzeSourceProgram();

        Parser parser = new Parser(lexer.getTokenStream(), lexer.getErrorQnt());
        parser.parseProgramm();

        Interpreter interpreter = new Interpreter(parser.getOutput(), parser.getErrorQnt(), -1);
        interpreter.interpretProgram();

        if (lexer.getErrorQnt() + parser.getErrorQnt() + interpreter.getErrorQnt() > 0) {
            String errorLog = lexer.getErrorLog() + parser.getErrorLog() + interpreter.getErrorLog();
            return "there are some errors in source program:\n" + errorLog;
        }
        else {
            return interpreter.getOutput();
        }
    }
}
















