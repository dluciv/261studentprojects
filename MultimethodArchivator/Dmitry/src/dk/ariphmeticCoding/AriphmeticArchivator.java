package dk.ariphmeticCoding;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import dk.main.DataList;
import dk.streams.BitOutputStream;

public class AriphmeticArchivator {

	public static int CODE_VALUE_BITS = 16;
	public static int TOP_VALUE = (1 << CODE_VALUE_BITS) - 1;
	public static int FIRST_QTR = TOP_VALUE / 4 + 1;
	public static int HALF = 2 * FIRST_QTR;
	public static int THIRD_QTR = 3 * FIRST_QTR;

	private long low = 0;
	private long high = TOP_VALUE;
	private long bits_to_follow = 0;
	private BitOutputStream output;

	public void writeFile(String inFilePath) throws IOException {

		File inFile = new File(inFilePath);
		String outFilePath = inFilePath + ".arc";
		File outFile = new File(outFilePath);
		FileInputStream in = new FileInputStream(inFile);
		FileOutputStream out = new FileOutputStream(outFile);

		Model m = new Model();
		m.startModel();
		int ch;
		int symbol;

		output = new BitOutputStream(out);

		while (true) {
			ch = in.read();
			if (ch == -1) {
				break;
			}
			symbol = m.symbolToIndex.getData(ch);
			DataList cumFreq = m.cumFreq;
			encodeSymbol(symbol, cumFreq);
			m.updateModel(symbol);
		}

		encodeSymbol(Model.EOF, m.cumFreq);
		doneEncoding();
		output.writeLastInvByte();
	}

	private void encodeSymbol(int symbol, DataList cumFreq) throws IOException {
		long range;
		range = (high - low) + 1;
		high = low + (range * cumFreq.getData(symbol - 1)) / cumFreq.getData(0)
				- 1;
		low = low + (range * cumFreq.getData(symbol)) / cumFreq.getData(0);
		for (;;) {
			if (high < HALF) {
				bitsPlusFollow(0);
			} else if (low >= HALF) {
				bitsPlusFollow(1);
				low -= HALF;
				high -= HALF;
			} else if (low >= FIRST_QTR && high < THIRD_QTR) {
				bits_to_follow += 1;
				low -= FIRST_QTR;
				high -= FIRST_QTR;
			} else
				break;
			low = 2 * low;
			high = 2 * high + 1;
		}
	}

	private void doneEncoding() throws IOException {
		bits_to_follow += 1;
		if (low < FIRST_QTR)
			bitsPlusFollow(0);
		else
			bitsPlusFollow(1);
	}

	private void bitsPlusFollow(int bit) throws IOException {
		output.outputInvBit(bit);
		while (bits_to_follow > 0) {
			output.outputInvBit(bit ^ 1);
			bits_to_follow -= 1;
		}
	}

}
