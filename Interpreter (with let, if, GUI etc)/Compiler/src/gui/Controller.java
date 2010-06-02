
/**
 *
 * @author Карымов Антон 261 группа
 */
package gui;

import java.awt.Color;
import java.io.*;
import java.util.LinkedList;
import javax.swing.text.*;
import lexerandparser.*;
import tools.*;
import interpreter.*;

public class Controller implements iController {

    private IMainForm iMainForm;
    private boolean interpreterIsRunning;

    public Controller(IMainForm iMainForm) {
        this.iMainForm = iMainForm;
    }

    public void printInOutputPane(String string) {
        iMainForm.setTextInOutputPane(string);
    }

    public void openFile(File file) {
        iMainForm.clearOutputPane();
        iMainForm.clearErrorPane();
        try {
            InputStream fileInpStream = new FileInputStream(file);
            int size = fileInpStream.available();
            fileInpStream.close();
            char[] buff = new char[size];
            Reader fileReadStream = new FileReader(file);
            int count = fileReadStream.read(buff);
            iMainForm.setTextInTextPane(String.copyValueOf(buff));
            fileReadStream.close();
        } catch (FileNotFoundException Exception) {
            iMainForm.setTextInOutputPane("File can not open");
        } catch (IOException Exception) {
            iMainForm.setTextInOutputPane("File can't be read");
        }

    }

    public void saveFile(String textProgramm, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(textProgramm);
            fileWriter.close();
        } catch (IOException Exception) {
            iMainForm.setTextInOutputPane("File can't be write");
        } catch (SecurityException Exception) {
            iMainForm.setTextInOutputPane("File can not find");
        }
    }

    public void runProgram(String input, boolean isUnderDebug) {
        //stopInterpreter();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(input + '\0');
        lexer.analyzeSourceProgram();
        if (Tool.getErrorQnt() == 0) {
            Parser parser = new Parser(lexer.getTokenStream());
            parser.parseProgram();
            if (Tool.getErrorQnt() == 0) {
                Interpreter interpreter = new Interpreter(parser.getOutput(), isUnderDebug, this);
                interpreterIsRunning = true;
                interpreter.start();
            }
        }
    }

    public void lightKeywords() {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Color.blue);
        String[] keywordList = {"true", "false", "let", "in", "begin", "end", "if", "then", "print", "else"};
        String currentTextProgramm = iMainForm.getTextInTextPane();
        iMainForm.getStyledDocumentInTextPane().setCharacterAttributes(0, iMainForm.getTextInTextPane().length(), new SimpleAttributeSet(), true);

        for (String keyword : keywordList) {
            int pointer = 0;

            while ((pointer = currentTextProgramm.indexOf(keyword, pointer)) != -1) {
                boolean isKeyword = true;
                if (pointer > 0) {
                    if (((currentTextProgramm.charAt(pointer - 1) >= 'a') && (currentTextProgramm.charAt(pointer - 1) <= 'z')) ||
                            ((currentTextProgramm.charAt(pointer - 1) >= 'A') && (currentTextProgramm.charAt(pointer - 1) <= 'Z')) ||
                            ((currentTextProgramm.charAt(pointer - 1) >= '0') && (currentTextProgramm.charAt(pointer - 1) <= '9'))) {
                        isKeyword = false;
                    }
                }
                if (pointer + keyword.length() < currentTextProgramm.length()) {
                    if (((currentTextProgramm.charAt(pointer + keyword.length()) >= 'a') && (currentTextProgramm.charAt(pointer + keyword.length()) <= 'z')) ||
                            ((currentTextProgramm.charAt(pointer + keyword.length()) >= 'A') && (currentTextProgramm.charAt(pointer + keyword.length()) <= 'Z')) ||
                            ((currentTextProgramm.charAt(pointer + keyword.length()) >= '0') && (currentTextProgramm.charAt(pointer + keyword.length()) <= '9'))) {
                        isKeyword = false;
                    }
                }
                if (isKeyword) {
                    iMainForm.getStyledDocumentInTextPane().setCharacterAttributes(pointer - getLineNumber(pointer), keyword.length(), attr, false);
                }
                pointer++;
            }
        }
        iMainForm.setCharacterAttributesInTextPane();
    }

    public void selectDebugLine(int line, int column) {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBackground(attr, Color.green);
        String text = iMainForm.getTextInTextPane();
        iMainForm.getStyledDocumentInTextPane().setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);
        lightKeywords();

        int curLine = 1;
        int startSelection = 0;
        int lengthSelection = 0;
        int curPos = 0;

        while ((curLine < line + 1) && (curPos < text.length())) {
            if (text.charAt(curPos) == '\n') {
                curLine++;
                if ((line == curLine) && (line != 1)) {
                    startSelection = curPos + 2 - line;
                }
            }
            curPos++;
        }
        if (curPos != text.length()) {
            lengthSelection = curPos - startSelection - 1 - line;
        } else {
            lengthSelection = curPos - startSelection + 1 - line;
        }

        iMainForm.setCaretPositionInTextPane(startSelection + column - 1);
        iMainForm.getStyledDocumentInTextPane().setCharacterAttributes(startSelection, lengthSelection, attr, false);
        iMainForm.setCharacterAttributesInTextPane();
        iMainForm.setFocusInTextPane();
    }

    public void selectLine(int line, int column) {

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBackground(attr, Color.yellow);
        String text = iMainForm.getTextInTextPane();
        iMainForm.getStyledDocumentInTextPane().setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);
        lightKeywords();

        int curLine = 1;
        int startSelection = 0;
        int lengthSelection = 0;
        int curPos = 0;

        while ((curLine < line + 1) && (curPos < text.length())) {
            if (text.charAt(curPos) == '\n') {
                curLine++;
                if ((line == curLine) && (line != 1)) {
                    startSelection = curPos + 2 - line;
                }
            }
            curPos++;
        }
        if (curPos != text.length()) {
            lengthSelection = curPos - startSelection - 1 - line;
        } else {
            lengthSelection = curPos - startSelection + 1 - line;
        }

        iMainForm.setCaretPositionInTextPane(startSelection + column - 1);
        iMainForm.getStyledDocumentInTextPane().setCharacterAttributes(startSelection, lengthSelection, attr, false);
        iMainForm.setCharacterAttributesInTextPane();
        iMainForm.setFocusInTextPane();
    }

    private int getLineNumber(int pointer) {
        int i = 0;
        int line = 0;
        while (i < pointer) {
            if (iMainForm.getTextInTextPane().charAt(i) == '\n') {
                line++;
            }
            i++;
        }
        return line;
    }
    
    public void cleanErrorPane(){
        iMainForm.clearErrorPane();
    }

    public void errorRevise(LinkedList<ErrorLogCell> errorList) {
        for (ErrorLogCell cellError : errorList) {
            iMainForm.setTextInErrorPane(cellError.getErrorMessage(), cellError.getPosition().getColumn(), cellError.getPosition().getLine());     
        }
         iMainForm.setStatus("You have some error in the programm.");
    }

    public void stopInterpreter() {
        Interpreter.stopInterpreter();
    }

    public void setInterpreterStateFalse() {
        interpreterIsRunning = false;
    }

    public boolean interpreterIsRunning() {
        return interpreterIsRunning;
    }
}
