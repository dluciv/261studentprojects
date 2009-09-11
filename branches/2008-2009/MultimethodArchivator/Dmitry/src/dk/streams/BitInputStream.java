package dk.streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import dk.ariphmeticCoding.AriphmeticArchivator;
import dk.ariphmeticCoding.Model;

public class BitInputStream {

	private int bits_to_go;
	private int garbage_bits;
	private int buffer;
	private FileInputStream in;
	private int bit;

	public BitInputStream(FileInputStream inFile) throws FileNotFoundException {
		bits_to_go = 0;
		garbage_bits = 0;
		in = inFile;
	}

	public int inputBit() throws IOException {
		if (bits_to_go == 0) {
			buffer = in.read();
			bits_to_go = 8;
		}
		bit = (buffer & 0x80) / 0x80;
		buffer <<= 1;
		bits_to_go--;
		return bit;
	}

	public int inputInvBit() throws IOException {
		if (bits_to_go == 0) {
			buffer = in.read();
			if (buffer == Model.EOF) {
				garbage_bits += 1;
				if (garbage_bits > AriphmeticArchivator.CODE_VALUE_BITS - 2) {
					System.out.println("Bad input file\n");
				}
			}
			bits_to_go = 8;
		}
		bit = buffer & 1;
		buffer >>= 1;
		bits_to_go--;
		return bit;
	}
}
