package dk.archivator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class BitOutputStream {

	public void writeFile(File inFile, File outFile, int restBits,
			int maxCodeLength, HashMap<Integer, Integer> codesLength,
			HashMap<Integer, Vector<Integer>> codeList) throws IOException {

		FileInputStream in = new FileInputStream(inFile);
		FileOutputStream out = new FileOutputStream(outFile);
		// WRITE restBits
		out.write(restBits);
		// WRITE maxCodeLength
		out.write(maxCodeLength);

		// WRITE TABEL
		int tmp = 0;
		int byteCounter = 1;

		for (int i = 0; i < Archivator.FREQUENCY_SIZE; i++) {
			if (codesLength.containsKey(i)) {
				if (tmp == codesLength.get(i)) {
					byteCounter++;
				} else {
					if (i != 0) {
						out.write(tmp);
						out.write(byteCounter);
						byteCounter = 1;
					}
					tmp = codesLength.get(i);
				}
			} else {
				if (0 == tmp && i != 0) {
					byteCounter++;
				} else if (i != 0) {
					out.write(tmp);
					out.write(byteCounter);
					byteCounter = 1;
					tmp = 0;
				}
			}
			if (i == 255) {
				out.write(tmp);
				out.write(byteCounter);
			}
		}

		// WRITE FILE
		int c = 0;
		int tmpByte = 0;
		int counter = 0;

		while ((c = in.read()) != -1) {
			int size = codesLength.get(c);
			int i = 0;
			while (i != size) {
				++counter;
				tmpByte = (tmpByte << 1) + codeList.get(c).get(i);
				i++;
				if (counter == 8) {
					out.write(tmpByte);
					counter = 0;
					tmpByte = 0;
				}
			}
		}

		// WRITE LAST BYTE)
		tmpByte = tmpByte << (8 - counter);
		out.write(tmpByte);

	}
}
