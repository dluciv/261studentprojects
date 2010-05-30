package karymov;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class Memento {

    private static Memento memento = new Memento();

    private Memento() {
    }
    private int height;
    private int width;
    private int coordinateX;
    private int coordinateY;
    private String fileName = "src/Karymov/setting.txt";
    private LinkedList<String> list = new LinkedList<String>();

    public static Memento getMemento() {
        return memento;
    }

    public LinkedList<String> getList() {
        return list;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static int identifyHeight(String line) {
        String currentWord = line.substring(0, 6);
        if (currentWord.equals("height") && line.substring(6, 7).equals("=")) {
            return getNumber(line.substring(7));
        } else {
            return 0;
        }
    }

    public static int identifyWidth(String line) {
        String currentWord = line.substring(0, 5);
        if (currentWord.equals("width") && line.substring(5, 6).equals("=")) {
            return getNumber(line.substring(6));
        } else {
            return 0;
        }
    }

    public static int identifyCoordinateX(String line) {
        String currentWord = line.substring(0, 11);
        if (currentWord.equals("coordinateX") && line.substring(11, 12).equals("=")) {
            return getNumber(line.substring(12));
        } else {
            return 0;
        }
    }

    public static int identifyCoordinateY(String line) {
        String currentWord = line.substring(0, 11);
        if (currentWord.equals("coordinateY") && line.substring(11, 12).equals("=")) {
            return getNumber(line.substring(12));
        } else {
            return 0;
        }
    }

    public static int getNumber(String expression) {

        if (expression.charAt(0) == '-') {
            int i = 1;
            while (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                if (i == expression.length() - 1) {
                    return Integer.valueOf(expression.substring(0, i + 1));
                }
                i++;
            }
            return Integer.valueOf(expression.substring(0, i));
        } else {
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

    public void loadBounds() {

        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            this.height = identifyHeight(stdin.readLine());
            this.width = identifyWidth(stdin.readLine());
            this.coordinateX = identifyCoordinateX(stdin.readLine());
            this.coordinateY = identifyCoordinateY(stdin.readLine());
            while (stdin.ready()) {
                this.list.add(stdin.readLine());
            }
        } catch (FileNotFoundException Exception) {
        } catch (IOException Exception) {
        }
    }

//    public void readRecentFile(String fileName) {
//        try {
//            BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
//            String text = "";
//            for (int i = 1; i <= 4; i++) {
//                text = stdin.readLine();
//            }
//            while (stdin.ready()) {
//
//               this list.add(stdin.readLine());
//            }
//
//        } catch (FileNotFoundException Exception) {
//        } catch (IOException Exception) {
//        }
//
//    }
    public void saveBounds(int height, int width, int coordinateX, int coordinateY, LinkedList<String> list) {
        try {
            BufferedWriter stdout = new BufferedWriter(new FileWriter(fileName));
            stdout.write("height=" + height + "\n");
            stdout.write("width=" + width + "\n");
            stdout.write("coordinateX=" + coordinateX + "\n");
            stdout.write("coordinateY=" + coordinateY + "\n");
            for (String file : list) {
                stdout.write(file + "\n");
            }
            stdout.close();
        } catch (IOException e) {
        }
    }
}
 