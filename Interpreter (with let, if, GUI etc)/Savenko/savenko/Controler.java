package savenko;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import savenko.ast.Program;

public class Controler {

     private IMainView view = null;
     private Program program = null;

     public Controler(IMainView mainv) {
          view = mainv;
     }

     public void interpret() {
          view.resetConsole();
          try {
               view.setProgressBarText("Interpreting program...");
               program = new Program(view.getProgramText());
               String result = program.Interpret();
               if (result == null) {
                    view.printResult("BuildSuccessfull!");
               } else {
                    view.printResult("BuildSuccessfull!\n" + result);
               }
          } catch (RightBracketExpectedException e) {
               view.printError("Right bracket expected", e.getPosition());
          } catch (UnexpectedRightBracketException e) {
               view.printError("UnexpectedRightBracket", e.getPosition());
          } catch (SemicolonExpectedException e) {
               view.printError("Semicolon expected", e.getPosition());
          } catch (ParserException e) {
               view.printError("Unknown parser error", e.getPosition());
          } catch (NullIDException e) {
               view.printError("Null ID Exception", e.getPosition());
          } catch (InterpreterException e) {
               view.printError("Unknown interpreter error", e.getPosition());
          }

          view.resetProgressBar();
     }

     public void debug() {
          view.setProgressBarText("Starting debug...");
          view.resetProgressBar();
     }

     public void openFile(String filename) {
          BufferedReader in = null;
          view.resetConsole();
          String programm_text = "";

          try {
               in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
               view.setProgressBarText("Opening the file...");
          } catch (FileNotFoundException e1) {
               view.printError("File " + filename + " not found.", null);
          }
          try {
               while (in.ready()) {
                    programm_text += in.readLine();
                    if (in.ready()) {
                         programm_text += "\n";
                    }
               }
               in.close();
          } catch (IOException e) {
               view.printError("Caught IOException while reading " + filename, null);
          } finally {
               //in.close();
          }

          view.resetProgressBar();
          view.setProgramText(programm_text);
     }

     public void saveFile(String filename, String program) {
          BufferedWriter out = null;
          view.resetConsole();

          try {
               out = new BufferedWriter(new FileWriter(filename));
               view.setProgressBarText("Saving to file...");
               out.write(program);
               out.close();
               view.printResult("File saved successfully");
          } catch (IOException e) {
               view.printError("Caught IOException while writing " + filename, null);
          }

          view.resetProgressBar();
     }
}
