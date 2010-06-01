package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParseSetting {

    public static int getHeight(String line) {
        String currentWord = line.substring(0, 6);
        if (currentWord.equals("height") && line.substring(6, 7).equals("=")) {
            return getNumber(line.substring(7));
        } else {
            return 0;
        }
    }

    public static String setHeight(int heigth) {
        String word = "height=" + heigth + ";";
        return word;
    }

    public static String findHeight(String fileName) {
        String textProgramm = "";
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while (stdin.ready()) {
                textProgramm += stdin.readLine();
                if (stdin.ready()) {
                    textProgramm += "\n";
                }
            }

        } catch (FileNotFoundException Exception) {
        } catch (IOException Exception) {
        }

        return textProgramm;

    }

    public static void writeHeight(String fileName, String line) {
        try {
            BufferedWriter stdout = new BufferedWriter(new FileWriter(fileName));
            stdout.write(line);
            stdout.close();

        } catch (IOException e) {
        }
    }

    public static int getNumber(String expression) {
        int i = 0;
        while (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
            if (i == expression.length() - 1) {
                return Integer.valueOf(expression.substring(0, i + 1));
            }
            i++;
        }
        return Integer.valueOf(expression.substring(0, i));
    }
}
