package dk.huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import dk.main.DataList;
import dk.streams.BitInputStream;

public class HuffmanDearchivator {
	private File outFile;
	private File inFile;
	private HashMap<Integer, Integer> codesLength;
	private Vector<Integer> symbols;
	private Vector<Integer> base;
	private int restBits;
	private int maxCodeLength;
	private FileInputStream in;
	private DataList offs;
	private DataList t;

	public HuffmanDearchivator(String inFilePath)
			throws IOException {
		String outFilePath = inFilePath.substring(0,inFilePath.length()-4) + ".dhuf";
		outFile = new File(outFilePath);
		outFile.createNewFile();
		inFile = new File(inFilePath);
		in = new FileInputStream(inFile);
		restBits = in.read();
		maxCodeLength = in.read();
		symbols = new Vector<Integer>();
		base = new Vector<Integer>();
		base.add(1);
		offs = new DataList(maxCodeLength + 1, -1);
		t = new DataList(maxCodeLength, 0);
		codesLength = new HashMap<Integer, Integer>();

		if (inFile.length() != 0) {
			readTable();
		}
	}

	public void readTable() throws IOException {
		int codeLength = 0;
		int controlSum = 0;
		int symbolsCounter = 0;

		// read table and create t, symbols and codesLength
		while (controlSum != HuffmanArchivator.FREQUENCY_SIZE) {
			codeLength = in.read();
			symbolsCounter = in.read();
			controlSum += symbolsCounter;
			if (codeLength != 0) {
				for (int i = controlSum - symbolsCounter; i < controlSum; i++) {
					t.addOne(codeLength - 1);
					symbols.add(i);
					codesLength.put(i, codeLength);
				}
			}
		}
		createBase();
		sortSymbols();
		createOffs();
	}

	private void createBase() {
		if (t.size() != 1) {
			for (int i = 1; i <= maxCodeLength; i++) {
				base.add(base.get(i - 1) * 2 - t.getData(i - 1));
			}
		} else {
			base.add(0); // if only one node
		}
	}

	private void sortSymbols() {
		Collections.sort(symbols, new SymbolsComparator());
	}

	private void createOffs() {
		int code = -1;
		for (int i = 0; i < symbols.size(); i++) {
			code = codesLength.get(symbols.get(i));
			if (i == 0) {
				offs.putData(code, i);
			} else if (i != 0 && code != codesLength.get(symbols.get(i - 1))) {
				offs.putData(code, i);
			}
		}
	}

	public void writeFile() throws IOException {
		if (inFile.length() != 0) {
		FileOutputStream out = new FileOutputStream(outFile);
		BitInputStream input = new BitInputStream(in);
		int length = 1;
		int buffer = input.inputBit();
		boolean isLastByte = false;

		while (true) {
			if (isLastByte) {
				if (restBits == 0)
					{break;}
				restBits--;
			}
			if (buffer >= base.get(length)) {
				out.write(symbols.get(offs.getData(length) + buffer
						- base.get(length)));
				length = buffer = 0;
			}
			if (in.available() == 0) {
				isLastByte = true; // read last byte
			}
			buffer = (buffer << 1) + input.inputBit();
			length++;
		}
		in.close();
		}
	}

	/*
	 * compare symbols (two keys 1 - code length 2 - alphavite)
	 */
	class SymbolsComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			int first = (Integer) o1;
			int second = (Integer) o2;
			int codeFirst = codesLength.get(first);
			int codeSecond = codesLength.get(second);
			if (codeFirst < codeSecond) {
				return 1;
			} else if (codeFirst > codeSecond) {
				return -1;
			} else {
				if (first > second) {
					return 1;
				} else
					return -1;
			}
		}
	}
}
