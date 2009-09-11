package gui;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Lapin Sergey 261group
 */
public class FilterForXML extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.getName().endsWith(".xml")) return true;
        return false;
    }

    @Override
    public String getDescription() {
        return "XML files with rules";
    }
}
