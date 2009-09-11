package dk.huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import dk.main.DataList;
import dk.streams.BitOutputStream;

public class HuffmanArchivator {

	public static int FREQUENCY_SIZE = 256;

	private File inFile;
	private File outFile;
	private DataList frequencyList;
	private HashMap<Integer, Vector<Boolean>> canonicalCodes;
	private HashMap<Integer, Integer> codesLength;
	private int maxCodeLength;
	private int restBits;

	public HuffmanArchivator(String inFilePath) throws IOException {
		inFile = new File(inFilePath);
		String outFilePath = inFilePath + ".huf";
		outFile = new File(outFilePath);
		outFile.createNewFile();
		countFrequencys();
		HuffmanTree tree = new HuffmanTree(frequencyList);
		if (tree.getTreeSize() != 0) {
			codesLength = tree.getLenghtCode();
			maxCodeLength = tree.getMaxCodeLength();
			canonicalCodes = tree.getCanonicalCode(codesLength);
			System.out.println(canonicalCodes);
			countRestBits();
		}
	}

	public void countFrequencys() throws IOException {
		frequencyList = new DataList(FREQUENCY_SIZE, 0);
		FileInputStream in = new FileInputStream(inFile);
		int c = 0;
		while ((c = in.read()) != -1) {
			frequencyList.addOne(c);
		}
		in.close();
	}

	private void countRestBits() {
		restBits = 0;
		for (int i = 0; i < frequencyList.size(); i++) {
			int frequency = frequencyList.getData(i);
			if (frequency != 0) {
				restBits += frequency * codesLength.get(i);
			}
		}
		restBits = restBits % 8;
	}

	public void writeFile() throws IOException {
		FileInputStream in = new FileInputStream(inFile);
		FileOutputStream out = new FileOutputStream(outFile);

		writeRestBits(out);
		writeMaxCodeLength(out);
		writeTable(out);

		BitOutputStream output = new BitOutputStream(out);
		int c = 0;
		Vector<Boolean> codeC;
		while ((c = in.read()) != -1) {
			int size = codesLength.get(c);
			codeC = canonicalCodes.get(c);
			for (int i = 0; i < size; i++) {
				output.outputBit(codeC.get(i));
			}
		}
		output.writeLastByte();
	}

	private void writeMaxCodeLength(FileOutputStream out) throws IOException {
		out.write(maxCodeLength);
	}

	private void writeRestBits(FileOutputStream out) throws IOException {
		out.write(restBits);
	}

	private void writeTable(FileOutputStream out) throws IOException {
		int tmp = 0;
		if (codesLength.containsKey(0)) {
			tmp = codesLength.get(0);
		}
		int byteCounter = 1;
		// use RLE
		for (int i = 1; i < HuffmanArchivator.FREQUENCY_SIZE; i++) {
			if (codesLength.containsKey(i)) {
				if (tmp == codesLength.get(i)) {
					byteCounter++;
				} else {
					out.write(tmp);
					out.write(byteCounter);
					byteCounter = 1;	
				}
				tmp = codesLength.get(i);
			} else {
				if (tmp == 0) {
					byteCounter++;
				} else {
					out.write(tmp);
					out.write(byteCounter);
					byteCounter = 1;
				}
				tmp = 0;
			}
			if (i == 255) {
				out.write(tmp);
				out.write(byteCounter);
			}
		}
	}

	public HashMap<Integer, Vector<Boolean>> getCodeList() {
		return canonicalCodes;
	}

	public int getRestBits() {
		return restBits;
	}

}
