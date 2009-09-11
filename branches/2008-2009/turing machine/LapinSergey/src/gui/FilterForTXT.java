package gui;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Lapin Sergey 261group
 */
public class FilterForTXT extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.getName().endsWith(".txt")) return true;
        return false;    }

    @Override
    public String getDescription() {
        return "TXT files with tests";
    }
}
