package eda.tm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import eda.tm.representations.gui.Trace;
import eda.tm.representations.gui.TraceTape;

public class TexWriter {

	private static String DOCUMENTCLASS = "\\documentclass[a4paper,12pt]{article}[2008/12/22]\n";
	private static String USEPACKAGE = "\\usepackage[dvips]{graphicx,colortbl}\n";
    private static String BEGIN_DOCUMENT = "\\begin{document}\n";
    private static String END_DOCUMENT = "\\end{document}\n";
    private static String HLINE = "\\hline\n";
    private static String BEGIN_TABULAR = "\\begin{tabular}{|";
    private static String CLOSE = "}\n";
    private static String END_TABULAR = "\\end{tabular}\n";
    private static String SIMPLE_COLUMN = "c|";
    private static String RED_COLUMN_COLOUR = ">{\\columncolor{red}}c|";
    private static String BLUE_COLUMN_COLOUR = ">{\\columncolor{blue}}c|";
    private static String END_LINE = "\\\\\n";
    private static String WHITE_SPACE = "&";
    private static String NEWPAGE = "\\newpage\n";
    
    private static final String EXTENSION = ".tex";
    
	
	private Trace trace;


	public TexWriter(Trace trace) {
		this.trace = trace;
	}
	
	private void addTape(int i,BufferedWriter bf)throws IOException{
		TraceTape tt = trace.get(i);
		int size = tt.size();
		int current = 0;
		int last = 0;
		//currentPoint
		for(int d = 0; d < size;d++){
		if(tt.get(d).isCurrentPoint()){
			current = d;
			break;
			}
		}
		//lastPoint
		for(int k = 0; k < size;k++){
			if(tt.get(k).isLastPoint() && (last-1 != current || last+1 != current)){
				if(last-1 < current) last = current-1;
				else if(last-1 > current) last = current + 1;
				else last = k;
				break;
				}
			}
		//begin write
		   //write: \begin{tabular}{|c|>{\columncolor{red}}c|c|}
		bf.write(BEGIN_TABULAR);
		for(int m = 0; m < size; m++){
			if(m == current) bf.write(RED_COLUMN_COLOUR);
			else if(m == last) bf.write(BLUE_COLUMN_COLOUR);
			else bf.write(SIMPLE_COLUMN);
		}
		bf.write(CLOSE);
		   // write: /hline
		bf.write(HLINE);
		   // write: 1 & 1 & 1 \\
		for(int n = 0; n < size; n++){
			if(n == size - 1) bf.write(tt.get(n).getSymbol());
			else{
			bf.write(tt.get(n).getSymbol());
			bf.write(WHITE_SPACE);
			}
		}
		bf.write(END_LINE);
		bf.write(HLINE);
		bf.write(END_TABULAR);
	}
	
	public File createTexFile(String filename)throws IOException {
            File f1 = File.createTempFile(filename, EXTENSION);
            f1.deleteOnExit();
            BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
            bf.write(DOCUMENTCLASS);
            bf.write(USEPACKAGE);
            bf.write(BEGIN_DOCUMENT);
            bf.write("\n");
            int size = trace.size();
            
            for(int i = 0; i < size; i++){
            	addTape(i,bf);
            	if(i % 35 == 0 && i != 0) bf.write(NEWPAGE);
            }
            
            bf.write(END_DOCUMENT);
     
            bf.close();
            return f1;
    }
	
}
