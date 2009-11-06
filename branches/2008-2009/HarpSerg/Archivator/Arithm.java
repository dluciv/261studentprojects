/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
package archiver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class Arithm {

    static final int BITS = 24;
    static final long MAX = (1 << BITS) - 1;
    static final long FIRST_QTR = (MAX / 4) + 1;
    static final long HALF = FIRST_QTR * 2;
    static final long THIRD_QTR = FIRST_QTR * 3;
    // number of chars
    static final int CHARS = 256;

    public Arithm() {
    }

    public void pack(String inputfn, String outputfn, int size, HashMap<Byte, Integer> frequency) throws IOException {
        new Pack(inputfn, outputfn, size, frequency);
    }

    public void unpack(String inputfn, String outputfn) throws IOException, Unpack.UnpackException {
        new Unpack(inputfn, outputfn);
    }

    private class Pack {

        private Model model = new Model();
        private InputFile input;
        private BitsOutput output;
        private long _low = 0,  _high = MAX,  _follows = 0,  _bits_wrote = 0;
        private double _bits_needed = 0.0;

        public Pack(String inputfn, String outputfn, int size, HashMap<Byte, Integer> frequency) throws IOException {
            input = new InputFile(inputfn);
            output = new BitsOutput(new OutputFile(outputfn));

            output.stream().write(Bits.intToByteArray(size));
            Dictionary.WriteFreqRLE(frequency, output.stream());
            model.build(size, frequency);

            int symbol;
            int size_rest = input.lenLeft();
            for (int i = 0; i < size_rest; i++) {
                symbol = (int) (input.readOneByte() - Byte.MIN_VALUE);
                encode(symbol);
            }

            _follows++;
            write(_low > FIRST_QTR);

            output.close();
            input.close();
        }

        private void encode(int symbol) throws IOException {
            _bits_needed -= Math.log((double) (model.high(symbol) - model.low(symbol)) / model.size()) / Math.log(2);

            long range = _high - _low + 1;
            _high = _low + (range * model.high(symbol)) / model.size() - 1;
            _low = _low + (range * model.low(symbol)) / model.size();

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
            output.write(bit);
            _bits_wrote++;
            while (_follows > 0) {
                _follows--;
                output.write(!bit);
                _bits_wrote++;
            }
        }
    }

    public class Unpack {

        Model _model = new Model();
        BitsInput _inp;
        FileOutputStream _out;
        int _garbage;
        long _value, _low, _high = MAX;

        public Unpack(String inputfn, String outputfn) throws IOException, UnpackException {
            _inp = new BitsInput(new InputFile(inputfn));
            _out = new FileOutputStream(outputfn);

            byte[] buf = new byte[4];
            _inp.stream().read(buf);
            int model_size = Bits.byteArrayToInt(buf);

            _model.build(model_size, Dictionary.RestoreFreq(_inp.stream()));

            //_ model.print();

            for (int i = 0; i < BITS; i++) {
                _value = _value * 2 + (read() ? 1 : 0);
            }

            int symbol;
            int size = 0;
            while (true) {
                symbol = decode();
                _out.write((byte) (symbol + Byte.MIN_VALUE));
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
            _low = _low + (range * _model.low(symbol)) / _model.size();

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

    private class Model {

        int _size;
        HashMap<Byte, Integer> frequency;
        int[] _bounds = new int[CHARS + 1];

        public void build(int size, HashMap<Byte, Integer> freq) throws IOException {
            _size = size;
            frequency = freq;


            buildModel();
        }

        private void buildModel() {
            int count = 0;
            for (int i = 0; i < CHARS; i++) {
                if (frequency.containsKey((byte) (i + Byte.MIN_VALUE))) {
                    count += frequency.get((byte) (i + Byte.MIN_VALUE)).intValue();
                }
                _bounds[i + 1] = count;
            }
        }

        public int low(int symbol) {
            return _bounds[symbol];
        }

        public int high(int symbol) {
            return _bounds[symbol + 1];
        }

        public int find(long value) throws CannotFindSymbolException {
            for (int i = 0; i < CHARS; i++) {
                if (_bounds[i] <= value && value < _bounds[i + 1]) {
                    return i;
                }
            }
            throw new CannotFindSymbolException();
        }

        public int size() {
            return _size;
        }

        public void print() {
            for (int i = 0; i < CHARS; i++) {
                int j = i + Byte.MIN_VALUE;
                if (frequency.get(j) > 0) {
                    System.out.println(
                            "'" + (char) i + "'" +
                            ": " +
                            frequency.get(j) + ", " + _bounds[i] + " - " + _bounds[i + 1]);
                }
            }
            System.out.println("Size: " + _size);
        }

        class CannotFindSymbolException extends Exception {
        }
    }
}