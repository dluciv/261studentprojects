/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

import java.io.IOException;

/**
 *
 * @author Admin
 */
public class HuffmanFileExtractor {


	public String getBitcodeFileConvention(String uncodeBits) {
		String endBits = uncodeBits.substring(uncodeBits.length()-8,uncodeBits.length());
        byte lastWordLen = BitCoder.convertBitsToByte(endBits);
		uncodeBits = uncodeBits.substring(0, uncodeBits.length()-8);
        int cutLen = 8-lastWordLen;
		uncodeBits = uncodeBits.substring(0, uncodeBits.length()-cutLen);
		return uncodeBits;
	}

	public void retrieveHuffmanData(FileInput fis, HuffmanCoder h) throws IOException{
		int readBytes = 2;
		byte[] codes = new byte[readBytes];
        codes = fis.read(readBytes);

        while(codes[0]!=0 && codes[1]!=0){
			HuffmanChar iw = new HuffmanChar();
			iw.key = codes[0];
			iw.newWeight = codes[1];
			h.itemWeightList.add(iw);
            codes = fis.read(readBytes);
        }
	}
	
	public void extractFile(String fisName,String fosName) throws IOException{
		FileInput fis = new FileInput(fisName);
		HuffmanCoder h = new HuffmanCoder();
        // воссоздаем структуру хаффмана
        retrieveHuffmanData(fis, h);
		h.makeTree();
		h.makeCodes();
		// в потоке читаем, декодируем и записываем в файл
        FileOutput fos = new FileOutput(fosName);  // разархивируем в ...
		BitDecoder bd = new BitDecoder(h);
		System.out.println("стркутуру востановили");
		int readlen = 1024;
		byte[] buf = fis.read(readlen);
		System.out.println("этап2");
		while(fis.lenLeft()>1){
			System.out.println(fis.lenLeft());
			bd.addBits(BitCoder.converByteToBits(buf));
			fos.write(bd.decodeBits());
			buf = fis.read(readlen);
		}
		String uncodeBits = "";
		System.out.println("этап3");
 		while(buf.length > 0){
            uncodeBits += BitCoder.converByteToBits(buf);
            buf = fis.read(readlen);
		}
        fis.flush();

		uncodeBits = getBitcodeFileConvention(uncodeBits);
		bd.addBits(uncodeBits);
		System.out.println("этап3");
		while(!bd.isAllDecoded())
			fos.write(bd.decodeBits());
        fos.flush();
		System.out.println("конец");

	}
}
