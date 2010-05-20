package karymov;

import ast.*;
import java.awt.FileDialog;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import lebedev.*;
import yaskov.*;

public class Controller {

    private IMainForm iMainForm;
    //private byte[] buf;
    public Controller(IMainForm iMainForm) {
        this.iMainForm = iMainForm;
    }

    public String openFile(String fileName) {

              String textProgramm = "";
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while (stdin.ready()) {
                textProgramm += stdin.readLine();
                 if (stdin.ready()) {
                         textProgramm += "\n";
                    }
            }
          iMainForm.setTextInConsolePanel("File "+fileName+" opened.");
        } catch (FileNotFoundException Exception) {
            iMainForm.setTextInConsolePanel("File can not open");
        } catch (IOException Exception) {
            iMainForm.setTextInConsolePanel("File can't be read");
        }
        
        return textProgramm;

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
    public void saveFile(String textProgramm,String fileName) throws FileNotFoundException, IOException {
//        FileOutputStream out = new FileOutputStream(fileName);
//        buf = textProgramm.getBytes();
//        BufferedOutputStream bout = new BufferedOutputStream(out);
//        bout.write(buf);
//        bout.close();
       // BufferedWriter out = null;
          iMainForm.clearConsolePanel();

          try {
               BufferedWriter stdout = new BufferedWriter(new FileWriter(fileName));
               stdout.write(textProgramm);
               stdout.close();
               iMainForm.setTextInConsolePanel("File saved successfully");
          } catch (IOException e) {
               iMainForm.setTextInConsolePanel("Caught IOException while writing " + fileName);
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
        interpreter.run();

        if (lexer.getErrorQnt() + parser.getErrorQnt() + interpreter.getErrorQnt() > 0) {
            String errorLog = lexer.getErrorLog() + parser.getErrorLog() + interpreter.getErrorLog();
            return "there are some errors in source program:\n" + errorLog;
        }
        else {
            return interpreter.getOutput();
        }
    }
}
