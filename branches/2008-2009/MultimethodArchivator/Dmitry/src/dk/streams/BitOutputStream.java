package dk.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream {

	private int buffer;
	private int bitsToGo;
	private FileOutputStream out;

	public BitOutputStream(FileOutputStream output) throws FileNotFoundException {
		buffer = 0;
		bitsToGo = 8;
		out = output;
	}

	public void outputBit(boolean bit) throws IOException {
		buffer <<= 1;
		if (bit)
			buffer ^= 0x01;
		bitsToGo -= 1;
		if (bitsToGo == 0) {
			out.write(buffer);
			bitsToGo = 8;
			buffer >>= 8;
		}
	}

	public void writeLastByte() throws IOException {
		out.write(buffer <<  bitsToGo);
	}
	
	public void outputInvBit(int bit) throws IOException {
		buffer >>= 1;
		if (bit == 1)
			buffer |= 0x80;
		bitsToGo -= 1;
		if (bitsToGo == 0) {
			out.write(buffer);
			bitsToGo = 8;
		}
	}

	public void writeLastInvByte() throws IOException {
		out.write(buffer >> bitsToGo);
	}
	
}
