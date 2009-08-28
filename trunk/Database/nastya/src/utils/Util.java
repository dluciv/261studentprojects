package utils;

import tree.Key;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 20.08.2009
 * Time: 22:45:05
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    public static final int MILLI = 1000;
    public static final int MIKRO = 1000000;
    public static final int NANO = 1000000000;

    public static int maxElement(String[] stringArray) {
        int max = 0;
        for (String street : stringArray) {
            if (max < street.length()) {
                max = street.length();
            }
        }
        return max;

    }

    public static int compare(Comparable s1, Object s2) {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s2 != null) {
            return -1;
        } else {
            return s1.compareTo(s2);
        }

    }

    public static File openFileChooser(JComponent parent, boolean save, String defaultName, final String filterPattern, final String filterDescription) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setSelectedFile(new File(defaultName));
        chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if(f.isDirectory()) return true;
                Pattern pattern = Pattern.compile(filterPattern);
                Matcher matcher = pattern.matcher(f.getName());
                return matcher.matches();
            }

            public String getDescription() {
                return filterDescription;
            }
        });
        int result = 0;
        if (save) {
            result = chooser.showSaveDialog(parent);
        } else {
            result = chooser.showOpenDialog(parent);
        }
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    public static String reprsentTime(long time) {
        String result = "";
        long mks = time % MILLI;
        long ms = time % MIKRO / MILLI;
        long s = time / NANO;
        if (s > 0) {
            result = s + "c " + ms + "мс " + mks + "мкс";
        } else if (ms > 0) {
            result = ms + "мс " + mks + "мкс";
        }else{
            result = mks + "мкс";
        }
        return result;
    }

    public static void cutFirstHalf(Vector<Key> list) {
        Vector<Key> tail = firstHalf(list);
        for(int i =0; i < tail.size(); i++){
            list.removeElement(tail.get(i));
        }
    }
    public static Vector<Key> firstHalf(Vector<Key> list) {
        Vector<Key> result = new Vector<Key>();
        for(int i =0;i < list.size() / 2; i++){
            result.add(list.get(i));
        }
        return result;
    }
}
