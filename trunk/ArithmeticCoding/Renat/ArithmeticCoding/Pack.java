package ArithmeticCoding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pack {
	private FileInputStream _input;
	private BitOutputStream _output;
	
	private Model _model;
	
	private double _low = 0.0;
	private double _high = 1.0;
	
	private int _follows = 0;
	
	private double _bits_needed = 0;
	private int _bits_wrote = 0;
	
	Pack(String input_filename, String output_filename) throws IOException {
		_model = new Model(input_filename);
		
		_model.print();
		
		_input = new FileInputStream(input_filename);
		_output = new BitOutputStream(new FileOutputStream(output_filename));
		
		int symbol;
		while ((symbol = _input.read()) != -1) {
			encode(symbol);
		}
		encode(Main.EOF);
		
		_follows++;
		writeBit(_low > 0.25);
		
		System.out.println("Bits needed: " + _bits_needed);
		System.out.println("Bits wrote: " + _bits_wrote);
		
		_output.close();
		_input.close();
	}
	
	private void encode(int symbol) throws IOException {
		double low = _low;
		double high = _high;
		_low = (high - low) * _model.low(symbol) + low;
		_high = (high - low) * _model.high(symbol) + low;
		
		_bits_needed -= Math.log(_model.high(symbol) - _model.low(symbol)) / Math.log(2);
		
		while (true) {
			if (_high < 0.5) {
				writeBit(false);
				_low *= 2;
				_high *= 2;
			} else if (_low >= 0.5) {
				writeBit(true);
				_low = _low * 2 - 1.0;
				_high = _high * 2 - 1.0;
			} else if (_low >= 0.25 && _high < 0.75) {
				_follows++;
				_low = _low * 2 - 0.5;
				_high = _high * 2 - 0.5;
			} else {
				break;
			}
		}
	}
	
	private void writeBit(boolean bit) throws IOException {
		_output.writeBit(bit);
		_bits_wrote++;
		while (_follows > 0) {
			_follows--;
			_output.writeBit(!bit);
			_bits_wrote++;
		}
	}
}