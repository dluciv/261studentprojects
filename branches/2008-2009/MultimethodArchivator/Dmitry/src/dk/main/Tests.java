package dk.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Tests {

	public Tests() throws IOException{
		generateEmptyFile();
		generateRandomFile();
		generateOneSymbolFile();
		fileWithAllSymbols();
	}
	
	private void generateEmptyFile() throws IOException{
		File f = new File("EmptyFile");
		FileOutputStream out = new FileOutputStream(f);
		out.close();
	}
	
	private void generateRandomFile() throws IOException{
		File f = new File("RandomFile");
		FileOutputStream out = new FileOutputStream(f);
		Random r = new Random();
		for(int i = 0; i < 100000; i++){
			out.write(r.nextInt(257));
		}
		out.close();
	}
	
	private void generateOneSymbolFile() throws IOException{
		File f = new File("OneSymbolFile");
		FileOutputStream out = new FileOutputStream(f);
		for(int i = 0; i < 100000; i++){
			out.write(88);
		}
		out.close();
	}
	
	private void fileWithAllSymbols() throws IOException{
		File f = new File("AllSymbolsFile");
		FileOutputStream out = new FileOutputStream(f);
		for(int i = 0; i < 256; i++){
			out.write(i);
		}
		out.close();
	}
}
