package dk.ariphmeticCoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import dk.main.DataList;
import dk.streams.BitInputStream;

public class AriphmeticDearchivator {

	private BitInputStream in;
	private long low;
	private long high;
	private long value;
	private String inFp;

	public AriphmeticDearchivator(String inFilePath) throws IOException {
		inFp = inFilePath;
		File inFile = new File(inFilePath);
		FileInputStream input = new FileInputStream(inFile);
		in = new BitInputStream(input);
		int i;
		value = 0;
		for (i = 1; i <= AriphmeticArchivator.CODE_VALUE_BITS; i++) {
			value = 2 * value + in.inputInvBit();
		}
		low = 0;
		high = AriphmeticArchivator.TOP_VALUE;

	}

	public void writeFile() throws IOException {
		String outFilePath = inFp.substring(inFp.length() - 4) + ".dearc";
		File outFile = new File(outFilePath);
		FileOutputStream out = new FileOutputStream(outFile);
		Model m = new Model();
		m.startModel();
		int ch;
		int symbol;
		for (;;) {
			DataList cumFreq = m.cumFreq;
			symbol = decode_symbol(cumFreq);
			if (symbol == Model.EOF) {
				break;
			}
			ch = m.indexToSymbol.getData(symbol);
			out.write(ch);
			m.updateModel(symbol);
		}
	}

	int decode_symbol(DataList cumFreq) throws IOException {
		long range;
		long cum;
		int symbol;
		range = (high - low) + 1;
		cum = (((value - low) + 1) * cumFreq.getData(0) - 1) / range;
		for (symbol = 1; cumFreq.getData(symbol) > cum; symbol++)
			;

		high = low + (range * cumFreq.getData(symbol - 1)) / cumFreq.getData(0)
				- 1;
		low = low + (range * cumFreq.getData(symbol)) / cumFreq.getData(0);
		while (true) {
			if (high < AriphmeticArchivator.HALF) {

			} else if (low >= AriphmeticArchivator.HALF) {
				value -= AriphmeticArchivator.HALF;
				low -= AriphmeticArchivator.HALF;
				high -= AriphmeticArchivator.HALF;
			} else if (low >= AriphmeticArchivator.FIRST_QTR
					&& high < AriphmeticArchivator.THIRD_QTR) {
				value -= AriphmeticArchivator.FIRST_QTR;
				low -= AriphmeticArchivator.FIRST_QTR;
				high -= AriphmeticArchivator.FIRST_QTR;
			} else
				break;
			low = 2 * low;
			high = 2 * high + 1;
			value = 2 * value + in.inputInvBit();
		}
		return symbol;
	}

}
