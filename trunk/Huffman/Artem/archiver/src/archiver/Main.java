package archiver;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String fn1 = "D:/.huf/g.d";
		String fn2 = fn1+".huff";

		//HuffmanFileCompressor hfc = new HuffmanFileCompressor();
		//hfc.compressFile(fn1,fn2);

		HuffmanFileExtractor hfe = new HuffmanFileExtractor();
        hfe.extractFile(fn2,fn1+".unhuff");
	}

}
