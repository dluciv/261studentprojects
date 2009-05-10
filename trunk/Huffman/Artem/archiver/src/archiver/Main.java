package archiver;

import java.io.IOException;

public class Main {
	public static int  readBytes = 1;
	private static String log = "";

	public String getLog(){
		return log;
	}
	public static byte convertBitsToByte(String bits)
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
		System.out.println(convertBitsToByte(getBitsByByte(byt)) );
		System.out.println(getBitsByByte(convertBitsToByte(getBitsByByte(byt))) );
		System.out.println(convertBitsToByte(getBitsByByte(convertBitsToByte(getBitsByByte(byt)))) );

	}


	public static void setTreeLeaveWeight(HuffmanCoder h,String fisName) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			h.setTreeLeaveWeight(buf);
		}
		fis.flush();
		h.makeWeights();
	}

    public static void writeCodesToFile(HuffmanCoder h,FileOutput fos) throws IOException{
		for (HuffmanChar iw : h.itemWeightList) {
                fos.write((byte)iw.key);
                fos.write((byte)iw.newWeight);
		}
        fos.write((byte)0);
        fos.write((byte)0);
	}
	public static void writeCodedDataToFile(HuffmanCoder h,String fisName,FileOutput fos) throws IOException{
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
				byte writeByte = convertBitsToByte(wordBits);
                //System.out.println(wordBits+" - "+writeByte);
				fos.write(writeByte);
			}
			buf = fis.read();
		}
        if(lineBits.length() > 0){
            byte writeByte = convertBitsToByte(lineBits);
            //System.out.println(lineBits+" - "+writeByte);
            fos.write(writeByte);
            // записываем длину последнего "слова" в дополнительный последний байт
            byte lwlByte = (byte)lineBits.length();
            fos.write(lwlByte);
        }else 
            fos.write((byte)0);
		fis.flush();
	}

	public static String getWholeBitCode(HuffmanCoder h,String fisName) throws IOException{
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

	public static void retrieveCodes(FileInput fis, HuffmanCoder h) throws IOException{
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
    public static String getHuffmanData(HuffmanCoder h){
		String str ="";
		str += "key - oldWeight - newWeight - char - code\n";
		for (HuffmanChar iw : h.itemWeightList)
			str += iw.key+" - "+iw.oldWeight+" - "+iw.newWeight+" - "
					+(char)iw.key+" - "+h.codes.get(iw.key)+" ; \n";
		return str;
    }
    public static void printHuffmanData(HuffmanCoder h){
        System.out.println("key - oldWeight - newWeight - char - code");
		for (HuffmanChar iw : h.itemWeightList)
			System.out.println(iw.key+" - "+iw.oldWeight+" - "+iw.newWeight+" - "
					+(char)iw.key+" - "+h.codes.get(iw.key)+" ; ");
    }

	public static void archiveFile(String fisName) throws IOException{
		HuffmanCoder h = new HuffmanCoder();
		//	делаем коды хаффмана
		setTreeLeaveWeight(h,fisName);
		h.makeTree();
		h.makeCodes();	
		log += getHuffmanData(h);
		//	записываем кодированные данные в потоке
        String fosName = fisName+".huf";
		FileOutput fos = new FileOutput(fisName+".huf");
		writeCodesToFile(h, fos);
		writeCodedDataToFile(h,fisName,fos);		
		fos.flush();
		log += fisName+" has been archivated to "+fosName+"\n";
	}
	public static String getBitcodetextConvention(String uncodeBits) {
		String endBits = uncodeBits.substring(uncodeBits.length()-8,uncodeBits.length());
        byte lastWordLen = convertBitsToByte(endBits);
		uncodeBits = uncodeBits.substring(0, uncodeBits.length()-8);
        int cutLen = 8-lastWordLen;
		uncodeBits = uncodeBits.substring(0, uncodeBits.length()-cutLen);
		return uncodeBits;
	}
	public static void extractFile(String fisName) throws IOException{
		FileInput fis = new FileInput(fisName+".huf");
		HuffmanCoder h = new HuffmanCoder();
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

		log += fisName+".huf"+" has been unarchivated to "+fisName+".unhuf\n";
	}


	public static void main(String[] args) throws IOException {
		//testBBConversions();

        /*  архивируем и записываем результат* */
		String fisName = "D:/.huf/g.d";
		archiveFile(fisName);

        /*  возвращаем исходный файл * */
        extractFile(fisName);
		System.out.println(log);

        //System.out.println("ok!");


	}

}
