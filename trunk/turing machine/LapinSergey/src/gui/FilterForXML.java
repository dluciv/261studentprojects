/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Administrator
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
