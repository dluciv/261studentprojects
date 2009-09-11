package Arithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Renat Akhmedyanov
 */
public class Arithm {
	public static final int BITS      = 24;
	
	public static final long MAX       = (1 << BITS) - 1;
	public static final long FIRST_QTR = (MAX / 4) + 1;
	public static final long HALF      = FIRST_QTR * 2;
	public static final long THIRD_QTR = FIRST_QTR * 3;
	
	// number of chars
	public static final int CHARS     = 256;
	
	public Arithm() {
	}
	
	public void pack(String inputfn, String outputfn) throws IOException {
		new Pack(inputfn, outputfn);
	}
	
	public void unpack(String inputfn, String outputfn) throws IOException, Unpack.UnpackException {
		new Unpack(inputfn, outputfn);
	}
	
	class Pack {
		private Model _model = new Model();
		private FileInputStream _inp;
		private BitOutputStream _out;
		private long _low = 0, _high = MAX, _follows = 0, _bits_wrote = 0;
		private double _bits_needed = 0.0;
		
		public Pack(String inputfn, String outputfn) throws IOException {
			_model.build(new FileInputStream(inputfn));
			//_model.print();
			
			_inp = new FileInputStream(inputfn);
			_out = new BitOutputStream(new FileOutputStream(outputfn));
			_model.write(_out.stream());
			
			int symbol;
			while ((symbol = _inp.read()) != -1) {
				encode(symbol);
			}
			
			_follows++;
			write(_low > FIRST_QTR);
		
			_out.close();
			_inp.close();
			
			System.out.println("Bits wrote: " + _bits_wrote);
			System.out.println("Bits needed: " + _bits_needed);
		}
		
		private void encode(int symbol) throws IOException {
			_bits_needed -= Math.log((double)(_model.high(symbol) - _model.low(symbol)) / _model.size()) / Math.log(2);
			
			long range = _high - _low + 1;
			_high = _low + (range * _model.high(symbol)) / _model.size() - 1;
			_low  = _low + (range * _model.low (symbol)) / _model.size();
			
			while (true) {
				if (_high < HALF) {
					write(false);
				} else if (_low >= HALF) {
					write(true);
					_low -= HALF;
					_high -= HALF;
				} else if (_low >= FIRST_QTR && _high < THIRD_QTR) {
					_follows++;
					_low -= FIRST_QTR;
					_high -= FIRST_QTR;
				} else {
					break;
				}
				_low = _low * 2;
				_high = _high * 2 + 1;
				//System.out.println(_low+" - "+_high);
			}
		}
		
		private void write(boolean bit) throws IOException {
			_out.write(bit);
			_bits_wrote++;
			while (_follows > 0) {
				_follows--;
				_out.write(!bit);
				_bits_wrote++;
			}
		}
	}
	
	class Unpack {
		Model _model = new Model();
		BitInputStream _inp;
		FileOutputStream _out;
		int _garbage;
		long _value, _low, _high = MAX;
		
		public Unpack(String inputfn, String outputfn) throws IOException, UnpackException {
			_inp = new BitInputStream(new FileInputStream(inputfn));
			_out = new FileOutputStream(outputfn);
			_model.read(_inp.stream());
			//_model.print();
			
			for (int i=0; i<BITS; i++) {
				_value = _value * 2 + (read() ? 1 : 0);
			}
			
			int symbol;
			int size = 0;
			while (true) {
				symbol = decode();
				_out.write(symbol);
				size++;
				if (size == _model.size()) {
					break;
				}
			}
			
			_out.close();
			_inp.close();
		}
		
		private int decode() throws UnpackException {
			long range = _high - _low + 1;
			int symbol;
			try {
				symbol = _model.find(((_value - _low + 1) * _model.size() - 1) / range);
			} catch (Model.CannotFindSymbolException e) {
				throw new UnpackException();
			}
			
			_high = _low + (range * _model.high(symbol)) / _model.size() - 1;
			_low  = _low + (range * _model.low (symbol)) / _model.size();
			
			while (true) {
				if (_high < HALF) {
				} else if (_low >= HALF) {
					_low -= HALF;
					_high -= HALF;
					_value -= HALF;
				} else if (_low >= FIRST_QTR && _high < THIRD_QTR) {
					_low -= FIRST_QTR;
					_high -= FIRST_QTR;
					_value -= FIRST_QTR;
				} else {
					break;
				}
				_low = _low * 2;
				_high = _high * 2 + 1;
				_value = _value * 2 + (read() ? 1 : 0);
				//System.out.println(_low+" - "+_value+" - "+_high);
			}
			
			return symbol;
		}
		
		private boolean read() throws UnpackException {
			try {
				return _inp.read();
			} catch (IOException e) {
				_garbage++;
				if (_garbage > BITS) {
					throw new UnpackException();
				} else {
					return false;
				}
			}
		}
		
		class UnpackException extends Exception {
		}
	}
	
	class Model {
		int _size;
		int[] _freq = new int[CHARS];
		int[] _bounds = new int[CHARS+1];
		
		public void build(FileInputStream stream) throws IOException {
			int symbol;
			while ((symbol = stream.read()) != -1) {
				_freq[symbol]++;
				_size++;
			}
			//_size++;
			//_freq[EOF]++;
			
			buildModel();
		}
		
		private void buildModel() {
			int count = 0;
			for (int i=0; i<CHARS; i++) {
				count += _freq[i];
				_bounds[i+1] = count;
			}
		}
		
		public int low(int symbol) {
			return _bounds[symbol];
		}
		
		public int high(int symbol) {
			return _bounds[symbol + 1];
		}
		
		public int find(long value) throws CannotFindSymbolException {
			for (int i=0; i<CHARS; i++) {
				if (_bounds[i] <= value && value < _bounds[i+1]) {
					return i;
				}
			}
			throw new CannotFindSymbolException();
		}
		
		public int size() {
			return _size;
		}
		
		public void print() {
			for (int i=0; i<CHARS; i++) {
				if (_freq[i] > 0) {
					System.out.println(
						"'"+(char)i+"'" +
						": " +
						_freq[i] + ", " + _bounds[i] + " - " + _bounds[i+1]
					);
				}
			}
			System.out.println("Size: "+_size);
		}
		
		public void write(FileOutputStream stream) throws IOException {
			int symbols = 0;
			for (int i=0; i<CHARS; i++) {
				if (_freq[i] > 0) {
					symbols++;
				}
			}
			stream.write(symbols - 1);
			for (int i=0; i<CHARS; i++) {
				if (_freq[i] > 0) {
					stream.write(i);
					stream.write(intToByteArray(_freq[i]));
				}
			}
		}
		
		public void read(FileInputStream stream) throws IOException {
			int symbols = stream.read() + 1;
			byte[] bb = new byte[4];
			for (int i=0; i<symbols; i++) {
				int c = stream.read();
				stream.read(bb);
				_freq[c] = byteArrayToInt(bb);
				_size += _freq[c];
			}
			//_freq[EOF] = 1;
			//_size++;
			
			buildModel();
		}
	
		private byte[] intToByteArray(int value) {
			return new byte[] {
				(byte)(value >>> 24),
				(byte)(value >>> 16),
				(byte)(value >>> 8),
				(byte)value
			};
		}
		
		private int byteArrayToInt(byte[] b) {
			return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
		}
		
		class CannotFindSymbolException extends Exception {
		}
	}
}