/*
 *описывает как интерфейс реагирует на управляющие воздействия пользователя
 * описана подсветка ключивых слов и дебаггера
 * Antonov Kirill(c), 2010
 */
package gui;

import exception.IncompatibleTypedException;
import exception.InterpreterException;
import exception.NullIDException;
import exception.ParserException;
import exception.RightBracketException;
import exception.SemicolonException;
import exception.UnexpectedRightBracketException;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Controler {

    private IMainView view = null;
    private Program program = null;

    public Controler(IMainView mainv) {
        view = mainv;
    }

    public void interpret() {
        view.resetConsole();
        try {
            view.setProgressBarText("I interpreting program...");
            program = new Program(view.getProgramText());
            String result = program.Interpret();
            if (result == null) {
                view.printResult("BuildSuccessfull!");
            } else {
                view.printResult("BuildSuccessfull!\n" + result);
            }
        } catch (RightBracketException e) {
            view.printError("Right bracket expected", e.getPosition());
        } catch (UnexpectedRightBracketException e) {
            view.printError("UnexpectedRightBracket", e.getPosition());
        } catch (SemicolonException e) {
            view.printError("Semicolon expected", e.getPosition());
        } catch (IncompatibleTypedException e) {
            view.printError("Incompatible Type Error", e.getPosition());
        } catch (ParserException e) {
            view.printError("Unknown parser error", e.getPosition());
        } catch (NullIDException e) {
            view.printError("Null ID Exception", e.getPosition());
        } catch (InterpreterException e) {
            view.printError("Unknown interpreter error", e.getPosition());
        }

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

    public void lightKeywords() {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Color.BLUE);
        String[] keywordList = {"bool", "true", "false", "let", "int", "in","unit", "begin", "end", "if", "then", "print", "else", "fun"};
        String currentTextProgramm = view.getProgramText();
        view.getStyledDocumentInTextPane().setCharacterAttributes(0, view.getProgramText().length(), new SimpleAttributeSet(), true);

        for (String keyword : keywordList) {
            int pointer = 0;

            while ((pointer = currentTextProgramm.indexOf(keyword, pointer)) != -1) {
                boolean isKeyword = true;
                if (pointer > 0) {
                    if (((currentTextProgramm.charAt(pointer - 1) >= 'a') && (currentTextProgramm.charAt(pointer - 1) <= 'z'))
                            || ((currentTextProgramm.charAt(pointer - 1) >= 'A') && (currentTextProgramm.charAt(pointer - 1) <= 'Z'))
                            || ((currentTextProgramm.charAt(pointer - 1) >= '0') && (currentTextProgramm.charAt(pointer - 1) <= '9'))) {
                        isKeyword = false;
                    }
                }
                if (pointer + keyword.length() < currentTextProgramm.length()) {
                    if (((currentTextProgramm.charAt(pointer + keyword.length()) >= 'a') && (currentTextProgramm.charAt(pointer + keyword.length()) <= 'z'))
                            || ((currentTextProgramm.charAt(pointer + keyword.length()) >= 'A') && (currentTextProgramm.charAt(pointer + keyword.length()) <= 'Z'))
                            || ((currentTextProgramm.charAt(pointer + keyword.length()) >= '0') && (currentTextProgramm.charAt(pointer + keyword.length()) <= '9'))) {
                        isKeyword = false;
                    }
                }
                if (isKeyword) {
                    view.getStyledDocumentInTextPane().setCharacterAttributes(pointer - getLineNumber(pointer), keyword.length(), attr, false);
                }
                pointer++;
            }
        }
        view.setCharacterAttributesInTextPane();
    }

    private int getLineNumber(int pointer) {
        int i = 0;
        int line = 0;
        while (i < pointer) {
            if (view.getProgramText().charAt(i) == '\n') {
                line++;
            }
            i++;
        }
        return line;
    }
}
