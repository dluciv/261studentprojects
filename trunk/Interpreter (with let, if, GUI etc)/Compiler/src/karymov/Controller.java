package karymov;

import java.awt.Color;
import java.io.*;
import java.util.LinkedList;
import javax.swing.JTextPane;
import javax.swing.text.*;
import lebedev.*;
import tools.*;
import yaskov.*;

public class Controller {

    private IMainForm iMainForm;

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
            iMainForm.getTextPane().setText(String.copyValueOf(buff));
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

    public String runProgram(String input, boolean isUnderDebug) {
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(input + '\0');
        lexer.analyzeSourceProgram();
        lexer.printTokenStream();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Interpreter interpreter = new Interpreter(parser.getOutput(), Tool.getErrorQnt(), isUnderDebug, this);
        interpreter.start();

        if (Tool.getErrorQnt() > 0) {
            errorRevise(Tool.getErrorLog());
            return "";
        } else {
            return interpreter.getOutput();
        }

    }

    public void lightKeywords(JTextPane pane) {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Color.blue);
        String[] keywordList = {"true", "false", "let", "in", "begin", "end", "if", "then", "print", "else"};
        StyledDocument doc = pane.getStyledDocument();
        String currentTextProgramm = pane.getText();
        doc.setCharacterAttributes(0, pane.getText().length(), new SimpleAttributeSet(), true);

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
                    doc.setCharacterAttributes(pointer - getLineNumber(pointer, pane), keyword.length(), attr, false);
                }
                pointer++;
            }
        }
        pane.setCharacterAttributes(new SimpleAttributeSet(), true);
    }

    public void selectDebugLine(int line, int column) {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBackground(attr, Color.green);
        StyledDocument doc = iMainForm.getTextPane().getStyledDocument();
        String text = iMainForm.getTextPane().getText();
        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);
        lightKeywords(iMainForm.getTextPane());

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

        iMainForm.getTextPane().setCaretPosition(startSelection + column - 1);
        doc.setCharacterAttributes(startSelection, lengthSelection, attr, false);
        iMainForm.getTextPane().setCharacterAttributes(new SimpleAttributeSet(), true);
        iMainForm.getTextPane().requestFocus();
    }

    public void selectLine(JTextPane pane, int line, int column) {

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBackground(attr, Color.yellow);
        StyledDocument doc = pane.getStyledDocument();
        String text = pane.getText();
        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);
        lightKeywords(pane);

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

        pane.setCaretPosition(startSelection + column - 1);
        doc.setCharacterAttributes(startSelection, lengthSelection, attr, false);
        pane.setCharacterAttributes(new SimpleAttributeSet(), true);
        pane.requestFocus();
    }

    private int getLineNumber(int pointer, JTextPane pane) {
        int i = 0;
        int line = 0;
        while (i < pointer) {
            if (pane.getText().charAt(i) == '\n') {
                line++;
            }
            i++;
        }
        return line;
    }

    public void errorRevise(LinkedList<ErrorLogCell> errorList) {
        for (ErrorLogCell cellError : errorList) {
            iMainForm.setTextInErrorPane(cellError.getErrorMessage(), cellError.getPosition().getColumn(), cellError.getPosition().getLine());
        }
    }
}
