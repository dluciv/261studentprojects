package Karymov;

import java.awt.FileDialog;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private IMainForm iMainForm;
    private FileDialog fdlg;
    private byte[] buf;
    public Controller(IMainForm iMainForm) {
        this.iMainForm = iMainForm;
    }

    public String openFile(String fileName) {

              String textProgramm = "";
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while (stdin.ready()) {
                textProgramm += stdin.readLine();
            }
        } catch (FileNotFoundException Exception) {
            iMainForm.setTextInConsolePanel("File can not open");
        } catch (IOException Exception) {
            iMainForm.setTextInConsolePanel("File can't be read");
        }

        return textProgramm;

    }

    public void saveFile(String textProgramm,String fileName) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        buf = textProgramm.getBytes();
        BufferedOutputStream bout = new BufferedOutputStream(out);
        bout.write(buf);
        bout.close();
    }

    public void saveAsFile(String textProgramm, String fileName) {
    }
}
















