package archiver;

import java.io.IOException;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class Main {

	public static void main(String[] args) throws IOException {
		
		String fn1 = "D:/.huf/g.d";
		String fn2 = fn1+".huff";

		//HuffmanFileCompressor hfc = new HuffmanFileCompressor();hfc.compressFile(fn1,fn2);
		HuffmanFileCompressorThread hfct = new HuffmanFileCompressorThread();hfct.run(fn1,fn2);
		//HuffmanFileExtractor hfe = new HuffmanFileExtractor();hfe.extractFile(fn2,fn1+".unhuff");
	}

}
