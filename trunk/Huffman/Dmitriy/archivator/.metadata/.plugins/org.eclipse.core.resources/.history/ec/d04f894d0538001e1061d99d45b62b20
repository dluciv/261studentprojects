package dk.archivator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class BitInputStream {

	public void writeFile(FileInputStream in, FileOutputStream out,
			Vector<Integer> symbols, Vector<Integer> base, DataList offs,
			int restBits) throws IOException {
		int length = 1;
		Vector<Integer> bits = toBit(in.read());
		int tmpByte = bits.get(0);
		int i = 1;
		int c = 0; // c = in.read();

		// READ and WRITE FILE
		while (true) {
			if (tmpByte >= base.get(length)) {
				out.write(symbols.get(offs.getData(length) + tmpByte
						- base.get(length)));
				length = 0;
				tmpByte = 0;
			}
			if (i > 7) {
				i = i % 8;
				c = in.read();
				bits = toBit(c);
				if (in.available() == 0) { // READ LAST BYTE
					break;
				}
			}
			tmpByte = (tmpByte << 1) + bits.get(i);
			length++;
			i++;
		}

		// READ and WRITE LAST BYTE
		for (int j = 0; j < restBits; j++) {
			tmpByte = (tmpByte << 1) + bits.get(i);
			length++;
			i++;
			if (tmpByte >= base.get(length)) {
				out.write(symbols.get(offs.getData(length) + tmpByte
						- base.get(length)));
				length = 0;
				tmpByte = 0;
			}
		}
	}

	// convert byte to list of bits
	private Vector<Integer> toBit(int c) {
		Vector<Integer> tmpBits = new Vector<Integer>();
		while (c != 0) {
			tmpBits.add(0, c % 2);
			c = c / 2;
		}
		for (int i = tmpBits.size(); i < 8; i++) {
			tmpBits.add(0, 0);
		}
		return tmpBits;
	}
}
