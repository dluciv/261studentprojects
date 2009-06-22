/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HuffmanFileCompressorThread extends Thread  {

	public FileInput fis;

	private byte getPercantage(int a,int cur){
		return (byte) (100 - cur * 100 / a);
	}

	public void writeCodedDataToFile(HuffmanCoder h,String fisName,FileOutput fos) throws IOException{
		fis = new FileInput(fisName);
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

			// скольно процентов выполнено
			System.out.println( getPercantage(maxLenLeft,fis.lenLeft())+"" );
            try {
				sleep(2);
			} catch (InterruptedException e) {
				System.out.println( "Exception: " + e.toString() );
			}

		}
	}
		
	public void run(String fisName,String fosName) throws IOException {

		HuffmanCoder h = new HuffmanCoder();
		HuffmanFileCompressor hfc = new HuffmanFileCompressor();
		//	делаем коды хаффмана
		hfc.setTreeLeaveWeight(h,fisName);
		h.makeTree();
		h.makeCodes();
		//	записываем кодированные данные в потоке

		FileOutput fos = new FileOutput(fosName);
		hfc.writeHuffmanCodesToFile(h, fos);
		writeCodedDataToFile(h,fisName,fos);
		fos.flush();
		//log += fisName+" has been archivated to "+fosName+"\n";

	}
	public static void main(String args[]) {
		HuffmanFileCompressorThread thread = new HuffmanFileCompressorThread();
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException x) {
			System.out.println(x.toString());
		}
	}

}
