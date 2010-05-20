package karymov;

import java.io.*;

public class MyFilter extends javax.swing.filechooser.FileFilter {

    String ext;

    MyFilter(String ext) {
        this.ext = ext;
    }

    public boolean accept(File f) {
        if (f == null) {
            return false;
        }
        if (f.isDirectory()) {
            return true;
        } else {
            return (f.getName().endsWith(ext));
        }
    }

    public String getDescription() {
        return "Text files (.txt)";
    }
}
