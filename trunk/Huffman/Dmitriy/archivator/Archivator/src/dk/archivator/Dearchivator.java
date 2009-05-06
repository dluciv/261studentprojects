package dk.archivator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Dearchivator {

	private File outFile;
	private File inFile;
	private HashMap<Integer, Integer> codesLength; // canonical codes length

	public Dearchivator(String inFilePath, String outFilePath)
			throws IOException {
		outFile = new File(outFilePath);
		outFile.createNewFile();
		inFile = new File(inFilePath);
		if (inFile.length() != 0) {
			writeFile(inFile);
		}
	}

	public void writeFile(File inFile) {
		try {

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);

			int restBits = in.read(); // read restBits
			int maxCodeLength = in.read(); // read maxCodeLength

			// http://www.255.ru/index.php?newsid=968
			Vector<Integer> symbols = new Vector<Integer>();
			Vector<Integer> base = new Vector<Integer>();
			base.add(1); // one not-leaf node on zero level
			DataList offs = new DataList(maxCodeLength + 1, -1);
			DataList t = new DataList(maxCodeLength, 0);

			codesLength = new HashMap<Integer, Integer>();

			int codeLength = 0;
			int controlSum = 0;
			int symbolsCounter = 0;

			// read table and create t and codesLength
			while (controlSum != Archivator.FREQUENCY_SIZE) {
				codeLength = in.read();
				symbolsCounter = in.read();
				controlSum += symbolsCounter;
				if (codeLength != 0) {
					for (int i = controlSum - symbolsCounter; i < controlSum; i++) {
						t.addData(codeLength - 1);
						symbols.add(i);
						codesLength.put(i, codeLength);
					}
				}
			}

			// create base
			if (t.size() != 1) {
				for (int i = 1; i <= maxCodeLength; i++) {
					base.add(base.get(i - 1) * 2 - t.getData(i - 1));
				}
			} else {
				base.add(0); // IF ONLY ONE NODE
			}

			// sort symbols ( two keys 1 - code length 2 - alphavite)
			symbols = qSort(symbols);

			// create offs
			int code = -1;
			for (int i = 0; i < symbols.size(); i++) {
				code = codesLength.get(symbols.get(i));
				if (i == 0) {
					offs.putData(code, i);
				} else if (i != 0
						&& code != codesLength.get(symbols.get(i - 1))) {
					offs.putData(code, i);
				}
			}

			BitInputStream bInStream = new BitInputStream();
			bInStream.writeFile(in, out, symbols, base, offs, restBits);

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * sort (for symbols)
	 */

	private Vector<Integer> qSort(Vector<Integer> sort) {
		Integer tmp = sort.get(0);
		int size = sort.size();
		Vector<Integer> smallers = new Vector<Integer>();
		Vector<Integer> biggers = new Vector<Integer>();
		for (int i = 1; i < size; i++) {
			Integer tmp2 = sort.get(i);
			if (!compareTwoSymbols(tmp2, tmp)) {
				smallers.add(tmp2);
			} else
				biggers.add(tmp2);
		}

		if (smallers.size() == 0 && biggers.size() == 0) {
			Vector<Integer> n = new Vector<Integer>();
			n.add(tmp);
			return n;
		} else if (smallers.size() == 0)
			return add_to_end(tmp, qSort(biggers));
		else if (biggers.size() == 0)
			return add(qSort(smallers), tmp);
		else
			return concat(add(qSort(smallers), tmp), qSort(biggers));

	}

	private Vector<Integer> add(Vector<Integer> list1, Integer tmp) {
		list1.add(tmp);
		return list1;
	}

	private Vector<Integer> add_to_end(Integer tmp, Vector<Integer> list1) {
		list1.add(0, tmp);
		return list1;
	}

	private Vector<Integer> concat(Vector<Integer> list1, Vector<Integer> list2) {
		for (int i = 0; i < list2.size(); i++) {
			list1.add(list2.get(i));
		}
		return list1;
	}

	private boolean compareTwoSymbols(int symbFirst, int symbSecond) {
		int codeFirst = codesLength.get(symbFirst);
		int codeSecond = codesLength.get(symbSecond);
		if (codeFirst < codeSecond) {
			return true;
		} else if (codeFirst > codeSecond) {
			return false;
		} else {
			if (symbFirst > symbSecond) {
				return true;
			} else
				return false;
		}
	}
}
