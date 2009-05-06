package dk.archivator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Archivator {

	public static int FREQUENCY_SIZE = 256;

	private File inFile;
	private File outFile;
	private DataList frequencyList; // list of frequency
	private HashMap<Integer, Vector<Integer>> canonicalCodes;
	private HashMap<Integer, Integer> codesLength;
	private int maxCodeLength;
	private int restBits;

	/*
	 * CREATE TREE AND CODES
	 */
	public Archivator(String inFilePath, String outFilePath) throws IOException {

		inFile = new File(inFilePath);

		outFile = new File(outFilePath);
		outFile.createNewFile();
		frequencyList = new DataList(FREQUENCY_SIZE, 0);
		// create frequency list
		readFile();
		HuffmanTree tree = new HuffmanTree(frequencyList);

		if (tree.getTreeSize() != 0) {

			codesLength = tree.getLenghtCode();
			maxCodeLength = tree.getMaxCodeLength();
			canonicalCodes = tree.getCanonicalCode(codesLength);
			restBits = 0;

			for (int i = 0; i < frequencyList.size(); i++) {
				int frequency = frequencyList.getData(i);
				if (frequency != 0) {
					restBits += frequency * codesLength.get(i);
				}
			}

			restBits = restBits % 8;

			writeFile();
		}

	}

	/*
	 * READ FILE
	 */
	public void readFile() throws IOException {
		try {
			FileInputStream in = new FileInputStream(inFile);
			int c = 0;
			while ((c = in.read()) != -1) {
				frequencyList.addData(c);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * WRITE FILE
	 */
	public void writeFile() throws IOException {
		try {
			BitOutputStream out = new BitOutputStream();
			out.writeFile(inFile, outFile, restBits, maxCodeLength,
					codesLength, canonicalCodes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Vector<Integer>> getCodeList() {
		return canonicalCodes;
	}

	public int getRestBits() {
		return restBits;
	}

}
