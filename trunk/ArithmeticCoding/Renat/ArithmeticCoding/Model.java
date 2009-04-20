package ArithmeticCoding;

import java.io.FileInputStream;
import java.io.IOException;

public class Model {
	private double[] _bounds = new double[Main.EOF+2]; // first = 0.0, last = 1.0
	private int[] _freq = new int[Main.EOF+1];
	
	Model(String filename) throws IOException {
		for (int i=0; i<Main.EOF; i++) {
			_freq[i] = 0;
		}
		_freq[Main.EOF] = 1;
		
		FileInputStream input = new FileInputStream(filename);
		int symbol;
		int filesize = 0;
		while ((symbol = input.read()) != -1) {
			_freq[symbol]++;
			filesize++;
		}
		filesize++;
		input.close();
		
		_bounds[0] = 0.0;
		_bounds[Main.EOF+1] = 1.0;
		int current = 0;
		for (int i=0; i<Main.EOF+1; i++) {
			_bounds[i] = (double)current / (double)filesize;
			current += _freq[i];
		}
		
	}
	
	public double low(int symbol) {
		return _bounds[symbol];
	}
	
	public double high(int symbol) {
		return _bounds[symbol+1];
	}
	
	public void print() {
		for (int i=0; i<Main.EOF+1; i++) {
			if (_freq[i] > 0) {
				System.out.println("'" + (i == Main.EOF ? "EOF" : (char)i) + "': " + low(i) + " - " + high(i));
			}
		}
	}
}