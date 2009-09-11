package archiver;

import java.io.IOException;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class HuffmanFileCompressor {

	public void setTreeLeaveWeight(HuffmanCoder h,String fisName) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			h.setTreeLeaveWeight(buf);
		}
		fis.flush();
		h.makeWeights();
	}

	public void writeHuffmanCodesToFile(HuffmanCoder h,FileOutput fos) throws IOException{
		for (HuffmanChar iw : h.itemWeightList) {
			fos.write((byte)iw.key);
			fos.write((byte)iw.newWeight);
		}
		fos.write((byte)0);
		fos.write((byte)0);
	}

	public void writeCodedDataToFile(HuffmanCoder h,String fisName,FileOutput fos) throws IOException{
		FileInput fis = new FileInput(fisName);
		int readlen = 256;
		byte[] buf = new byte[readlen];
		String lineBits = "";
		int maxLenLeft = fis.lenLeft();

		buf = fis.read(readlen);
		while(buf.length > 0){
			String wordBits = h.codeToBits(buf);
			lineBits += wordBits;
			byte[] writeByte = new byte[lineBits.length()/8];
			int i = 0;
			while(lineBits.length() > 8){
				wordBits = lineBits.substring(0, 8);
				lineBits = lineBits.substring(8);
				writeByte[i] = BitCoder.convertBitsToByte(wordBits);
				i++;
			}
			fos.write(writeByte);
			buf = fis.read(readlen);
		}

		if(lineBits.length() > 0){
			byte writeByte = BitCoder.convertBitsToByte(lineBits);
			fos.write(writeByte);
			// записываем длину последнего "слова" в дополнительный последний байт
			byte lwlByte = (byte)lineBits.length();
			fos.write(lwlByte);
		}else
			fos.write((byte)0);
		fis.flush();
	}

	public void compressFile(String fisName,String fosName) throws IOException{
		HuffmanCoder h = new HuffmanCoder();
		//	делаем коды хаффмана
		setTreeLeaveWeight(h,fisName);
		h.makeTree();
		h.makeCodes();
		//log += h.getHuffmanData();
		//	записываем кодированные данные в потоке

		FileOutput fos = new FileOutput(fosName);
		writeHuffmanCodesToFile(h, fos);
		writeCodedDataToFile(h,fisName,fos);
		fos.flush();
		//log += fisName+" has been archivated to "+fosName+"\n";
	}

}
