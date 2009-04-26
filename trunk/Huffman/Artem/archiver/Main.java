package archiver;

import java.io.IOException;

public class Main {
	public static int  readBytes = 1;

	public static byte getByteByBits(String bits)
	{
		int value = 0;
		if(bits!=null)
		{
			for (int i = 0; i < bits.length(); i++)
			{
				if(bits.charAt(i) == '1')
				{
					value = value | (1 << i);
				}
			}
		}
		return (byte)value;
	}
    public static String getBitsByByte(byte b) {

        boolean[] bits = new boolean[8];
        String str = "" ;
        for (int i = 0; i < bits.length; i++) {
            bits[i] = ((b & (1 << i)) != 0);
            if(bits[i])
				str += "1";
			else str += "0";
        }

        return str;
    }
	public static void testBBConversions() {
		byte byt = 3;
		System.out.println(getBitsByByte(byt));
		System.out.println(getByteByBits(getBitsByByte(byt)) );
		System.out.println(getBitsByByte(getByteByBits(getBitsByByte(byt))) );
		System.out.println(getByteByBits(getBitsByByte(getByteByBits(getBitsByByte(byt)))) );

	}


	public static void setTreeLeaveWeight(Huffman h,String fisName) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			h.setTreeLeaveWeight(buf);
		}
		fis.flush();
		h.makeWeights();
	}

    public static void writeCodesToFile(Huffman h,FileOutput fos) throws IOException{
		for (ItemWeight iw : h.itemWeightList) {
                fos.write((byte)iw.key);
                fos.write((byte)iw.newWeight);
		}
        fos.write((byte)0);
        fos.write((byte)0);
	}
	public static void writeCodedDataToFile(Huffman h,String fisName,FileOutput fos) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		String lineBits = "";
		buf = fis.read();
		while(buf.length > 0){
			String wordBits = h.codeToBits(buf[0]);
			//System.out.println(buf[0]+" - "+wordBits);
			lineBits += wordBits;
            //System.out.println(lineBits+" ( "+ lineBits.length()+" ) ");
			if(lineBits.length() > 8){
				wordBits = lineBits.substring(0, 8);
				lineBits = lineBits.substring(8);
				byte writeByte = getByteByBits(wordBits);
                //System.out.println(wordBits+" - "+writeByte);
				fos.write(writeByte);
			}
			buf = fis.read();
		}
        if(lineBits.length() > 0){
            byte writeByte = getByteByBits(lineBits);
            //System.out.println(lineBits+" - "+writeByte);
            fos.write(writeByte);
            // записываем длину последнего "слова" в дополнительный последний байт
            byte lwlByte = (byte)lineBits.length();
            fos.write(lwlByte);
        }else 
            fos.write((byte)0);
		fis.flush();
	}

	public static String getWholeBitCode(Huffman h,String fisName) throws IOException{
        FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		String wordBits = "";
        buf = fis.read();
		while(buf.length>0){
			wordBits += h.codeToBits(buf[0]);
            buf = fis.read();
		}
        fis.flush();
        return wordBits;
	}

	public static void retrieveCodes(FileInput fis, Huffman h) throws IOException{
		int readBytes = 2;
		byte[] codes = new byte[readBytes];
        codes = fis.read(readBytes);
       
        while(codes[0]!=0 && codes[1]!=0){
			ItemWeight iw = new ItemWeight();
			iw.key = codes[0];
			iw.newWeight = codes[1];
			h.itemWeightList.add(iw);
            codes = fis.read(readBytes);
        }
	}

    public static void printHuffmanData(Huffman h){
        System.out.println("key - oldWeight - newWeight - char - code");
		for (ItemWeight iw : h.itemWeightList)
			System.out.println(iw.key+" - "+iw.oldWeight+" - "+iw.newWeight+" - "
					+(char)iw.key+" - "+h.codes.get(iw.key)+" ; ");
    }

	public static void archiveFile(String fisName) throws IOException{
		Huffman h = new Huffman();
		//	делаем коды хаффмана
		setTreeLeaveWeight(h,fisName);
		h.makeTree();
		h.makeCodes();	
        //printHuffmanData(h);
        //System.out.println(getWholeBitCode(h,fisName));

		//	записываем кодированные данные в потоке
        String fosName = fisName+".huf";
		FileOutput fos = new FileOutput(fosName);
		writeCodesToFile(h, fos);
		writeCodedDataToFile(h,fisName,fos);
		
		fos.flush();

		System.out.println(fisName+" has been archivated to "+fosName);
         /* */
	}
	public static String getBitcodetextConvention(String uncodeBits) {
		String endBits = uncodeBits.substring(uncodeBits.length()-8,uncodeBits.length());
        byte lastWordLen = getByteByBits(endBits);
		uncodeBits = uncodeBits.substring(0, uncodeBits.length()-8);
        int cutLen = 8-lastWordLen;
		uncodeBits = uncodeBits.substring(0, uncodeBits.length()-cutLen);
		return uncodeBits;
	}
	public static void extractFile(String fisName) throws IOException{
		FileInput fis = new FileInput(fisName+".huf");
		Huffman h = new Huffman();
        // воссоздаем структуру хаффмана
        retrieveCodes(fis, h);
		h.makeTree();
		h.makeCodes();
		//printHuffmanData(h);
        
		// в потоке читаем, декодируем и записываем в файл
        FileOutput fos = new FileOutput(fisName+".unhuf");  // разархивируем в ...
		BitDecoder bd = new BitDecoder(h);
		byte[] buf = fis.read();
		while(fis.lenLeft()>1){
			bd.addBits(getBitsByByte(buf[0]));
			fos.write(bd.decodeBits());
			buf = fis.read();
		}
		String uncodeBits = "";
 		while(buf.length > 0){
            uncodeBits += getBitsByByte(buf[0]);
            buf = fis.read();
		}
        fis.flush();
		
		uncodeBits = getBitcodetextConvention(uncodeBits);
		bd.addBits(uncodeBits);
		
		while(!bd.isAllDecoded())
			fos.write(bd.decodeBits());
        fos.flush();
		
        System.out.println(fisName+".huf"+" has been unarchivated to "+fisName+".unhuf");
	}


	public static void main(String[] args) throws IOException {
		//testBBConversions();

        /*  архивируем и записываем результат* */
		String fisName = "D:/.huf/g.d";
		archiveFile(fisName);

        /*  возвращаем исходный файл * */
        extractFile(fisName);

        System.out.println("ok!");


	}

}
