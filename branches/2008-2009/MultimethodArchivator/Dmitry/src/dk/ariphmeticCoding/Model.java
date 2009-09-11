package dk.ariphmeticCoding;

import dk.main.DataList;

public class Model {

	public static int NO_OF_CHARS = 256;
	public static int EOF = NO_OF_CHARS + 1;
	public static int MAX_FREQUENCY = 16383;
	public static int NO_OF_SYMBOLS = NO_OF_CHARS + 1;
	public DataList cumFreq;
	public DataList freq;
	public DataList symbolToIndex;
	public DataList indexToSymbol;

	public void startModel() {
		freq = new DataList(NO_OF_SYMBOLS + 1, 1);
		cumFreq = new DataList(NO_OF_SYMBOLS + 1, 0);
		symbolToIndex = new DataList(NO_OF_CHARS, 0);
		indexToSymbol = new DataList(NO_OF_SYMBOLS + 1, 0);

		int i;
		for (i = 0; i < NO_OF_CHARS; i++) {
			symbolToIndex.putData(i, i + 1);
			indexToSymbol.putData(i + 1, i);
		}
		for (i = 0; i < NO_OF_SYMBOLS; i++) {
			cumFreq.putData(i, NO_OF_SYMBOLS - i);
		}
		freq.putData(0, 0);
	}

	public void updateModel(int symbol) {
		int i;
		if (cumFreq.getData(0) == MAX_FREQUENCY) {
			int cum;
			cum = 0;
			for (i = NO_OF_SYMBOLS; i >= 0; i--) {
				freq.putData(i, (freq.getData(i) + 1) / 2);
				cumFreq.putData(i, cum);
				cum += freq.getData(i);
			}
		}

		for (i = symbol; freq.getData(i) == freq.getData(i - 1); i--);

		if (i < symbol) {
			int ch_i, ch_symbol;
			ch_i = indexToSymbol.getData(i);
			ch_symbol = indexToSymbol.getData(symbol);
			indexToSymbol.putData(i, ch_symbol);
			indexToSymbol.putData(symbol, ch_i);
			symbolToIndex.putData(ch_i, symbol);
			symbolToIndex.putData(ch_symbol, i);
		}

		freq.addOne(i);
		while (i > 0) {
			i -= 1;
			cumFreq.addOne(i);
		}

	}
}
