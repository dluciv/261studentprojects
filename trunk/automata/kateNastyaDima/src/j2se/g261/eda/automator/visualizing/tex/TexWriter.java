/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.visualizing.tex;

import j2se.g261.eda.automator.representations.table.Table;
import j2se.g261.eda.automator.representations.table.TableRecord;
import j2se.g261.eda.automator.tests.TestResultItemStorage;
import j2se.g261.eda.automator.tests.TestResultItem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListSet;
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
    private static final String TABLE_CAPTION_RES = "\n\\caption{RESULTS} \\\\ " + TABLE_HLINE;
    private Table table;
    private Vector<Character> keys;
    private static final String EXTENSION = ".tex";

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

        ConcurrentSkipListSet<Table.Entry<Integer, TableRecord>> a = table.listOfRecords();
        for (Table.Entry<Integer, TableRecord> e : a) {
            res += e.getKey() + " " + getRowByTableRecord(e.getValue()) + "\n";

        }

        return res;
    }

    public File generateFile(String filename) {
        BufferedWriter bf;
        try {
            File f1 = File.createTempFile(filename, EXTENSION);
            f1.deleteOnExit();
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

    private String getColumns() {
        return String.valueOf(keys.size() + 1);
    }

    private String getCell(Vector v) {
        String s = v.toString();
        return s.substring(1, s.length() - 1);
    }

    public static File representateResultsAsTex(TestResultItemStorage dataForSerializing) {
        BufferedWriter bf;
        try {
            File f2 = File.createTempFile("Result", EXTENSION);
            f2.deleteOnExit();
            bf = new BufferedWriter(new FileWriter(f2));


            String tableCaption = getTableCaptionRes();
            bf.write(DOCUMENT_CLASS);
            bf.write(TABLE_ARRAY);
            bf.write(DOCUMENT_BEGIN);
            bf.write(TABLE_BEGIN_1);
            bf.write(String.valueOf(getColumnsRes()));
            bf.write(TABLE_BEGIN_2);
            bf.write(TABLE_CAPTION_RES);
            bf.write(tableCaption);
            bf.write(getTableBodyRes(dataForSerializing));

            bf.write(TABLE_END);

            bf.write(TABLE_BEGIN_1);
            bf.write(String.valueOf(2));
            bf.write(TABLE_BEGIN_2);
            bf.write(TABLE_CAPTION_RES);
            bf.write("Pattern & Bandwidth " + " \\\\ " + TABLE_HLINE + "\n");
            bf.write(bandWidthTableBody(dataForSerializing));

            bf.write(TABLE_END);

            bf.write(DOCUMENT_END);


            bf.close();

            return f2;
        } catch (IOException ex) {
            Logger.getLogger(TexWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String getTableCaptionRes() {
        String res = "";
        return res + " Pattern " + " & " + " String " + " & " + "Matches" + " & " + " Matches Table " + " & " + " Table Stat " + " & " + " Matches NFA " + " & " + " NFA Stat" + " & " + " Matches DFA " + " & " + " DFA Stat" + " & " + " Matches MinDFA " + " & " + " MinDFA Stat" + " \\\\ " + TABLE_HLINE + "\n";

    }

    private static Integer getColumnsRes() {
        int res = 11;
        return res;
    }

    private static String getTableBodyRes(TestResultItemStorage storage) {
        String res = "";

        for (int i = 0; i < storage.size(); i++) {
            res += getRow(storage.getTestResult(i));
        }

        return res;
    }

    private static String bandWidthTableBody(TestResultItemStorage storage) {
        storage.countBandwidth();
        String res = "";

        Vector<String> patterns = storage.getAllPatterns();
        for (int i = 0; i < patterns.size(); i++) {
            double f = storage.getBandwidthByPattern(patterns.get(i));
            String s = "";
            if (f > 1024 * 1024) {
                s = f / (1024 * 1024) + " Mb/sec";
            } else if (f > 1024) {
                s = f / 1024 + " Kb / sec";
            } else {
                s = f + "b / sec";
            }
            res += patterns.get(i) + " & " + s + " \\\\ " + TABLE_HLINE + "\n";
        }

        return res;
    }

    private static String getRow(TestResultItem item) {
        String res = "";

        res += item.getPattern() + " & " + item.getString() + " & " + item.isMatches() + "&" + item.getTable().isMatches() + " & " + item.getTable() + " & " + item.getNFA().isMatches() + " & " + item.getNFA() + " & " + item.getDFA().isMatches() + " & " + item.getDFA() + " & " + item.getMinGraph().isMatches() + " & " + item.getMinGraph() + " \\\\ " + TABLE_HLINE + "\n";
        return res;
    }
}
