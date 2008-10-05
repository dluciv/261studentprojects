/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tex;

import j2se.g261.eda.automator.table.Table;
import j2se.g261.eda.automator.table.TableRecord;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author katerina
 */
public class TexWriter {

    private static final String DOCUMENT_CLASS = "\\documentclass{article}\n\n";
    private static final String DOCUMENT_BEGIN = "\\begin{document}\n\n";
    private static final String DOCUMENT_END = "\\end{document}\n\n";
    private static final String TABLE_ARRAY = "\\usepackage{array, longtable}\n";
    private static final String TABLE_HLINE = "\\hline\n";
    private static final String TABLE_BEGIN_1 = "\\begin{longtable}{*{";
    private static final String TABLE_BEGIN_2 = "}{|c}|}";
    
    private static final String TABLE_END = "\\end{longtable}\n";
    private static final String TABLE_CAPTION = "\n\\caption{NFA TABLE} \\\\ " + TABLE_HLINE;
    private Table table;
    private Vector<Character> keys;

    public TexWriter(Table table) {
        this.table = table;
        keys = new Vector<Character>();
    }

    private String getTableCaption() {
        String res = "";

        res += " ";

        HashSet<Character> set = table.collectCaracterKeys();

        Iterator<Character> a = set.iterator();
        while (a.hasNext()) {

            res += " & ";
            Character var = a.next();
            res += var;
            keys.add(var);

        }

        return res + " \\\\ " + TABLE_HLINE + "\n";
    }

    private String getRowByTableRecord(TableRecord record) {

        String res = "";

        for (Character character : keys) {
            res += " & " + getCell(record.getVectorByChar(character));
        }

        return res + " \\\\ " + TABLE_HLINE + "\n";

    }

    private String getTableBody() {
        String res = "";

        Iterator<Entry<Integer, TableRecord>> a = table.iteratorByRecords();
        while (a.hasNext()) {
            Entry<Integer, TableRecord> e = a.next();
            res += e.getKey() + " " + getRowByTableRecord(e.getValue()) + "\n";

        }

        return res;
    }

    public File generateFile() {
        BufferedWriter bf;
        try {
            File f1 = File.createTempFile("NFA", ".tex");
//        f1.deleteOnExit();
            bf = new BufferedWriter(new FileWriter(f1));


            String tableCaption = getTableCaption();
            bf.write(DOCUMENT_CLASS);
            bf.write(TABLE_ARRAY);
            bf.write(DOCUMENT_BEGIN);
            bf.write(TABLE_BEGIN_1);
            bf.write(getColumns());
            bf.write(TABLE_BEGIN_2);
            bf.write(TABLE_CAPTION);
            bf.write(tableCaption);
            bf.write(getTableBody());
            
            bf.write(TABLE_END);
            bf.write(DOCUMENT_END);
            
            
            bf.close();

            return f1;
        } catch (IOException ex) {
            Logger.getLogger(TexWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String getColumns(){
        return String.valueOf(keys.size() + 1);
    }
    
    private String getCell(Vector v){
        String s = v.toString();
        return s.substring(1, s.length() - 1);
    }
}
