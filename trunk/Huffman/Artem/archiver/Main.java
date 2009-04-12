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
		fis.clean();
	}
	public static void growTree(Huffman h,String fisName) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			h.growTree();
		}
		fis.clean();
        //h.sortTree();
	}

    public static void writeCodesToFile(Huffman h,FileOutput fos) throws IOException{
		for (int valByte = 0; valByte < h.code.length; valByte++) {
            String bitCode = h.code[valByte];
            if(bitCode!=null){
                fos.write((byte)valByte);
                fos.write((byte)h.getWeight(valByte));
            }
		}
        fos.write((byte)0);
        fos.write((byte)0);
	}
	public static void writeCodedDataToFile(Huffman h,String fisName,FileOutput fos) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		String lineBits = "";
		while(buf.length > 0){
			buf = fis.read();
			String wordBits = h.coder(buf);
			lineBits += wordBits;
            //System.out.println(lineBits+" ( "+ lineBits.length()+" ) ");
			if(lineBits.length() > 8){
				wordBits = lineBits.substring(0, 8);
				lineBits = lineBits.substring(8);
				byte writeByte = getByteByBits(wordBits);
                //System.out.println(wordBits+" - "+writeByte);
				fos.write(writeByte);
			}
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
		fis.clean();
	}

	public static String getWholeBitCode(Huffman h,String fisName) throws IOException{
        FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		String wordBits = "";
        buf = fis.read();
		while(buf.length>0){
			wordBits += h.coder(buf);
            buf = fis.read();
		}
        fis.clean();
        return wordBits;
	}

	public static void retrieveCodes(FileInput fis, Huffman h) throws IOException{
        int readBytes = 2;
		byte[] codes = new byte[readBytes];
        codes = fis.read(readBytes);
       
        while(codes[0]!=0 && codes[1]!=0){
            h.weights[codes[0]] = codes[1];
            codes = fis.read(readBytes);
        }
	}

    public static void printHuffmanData(Huffman h){
        //System.out.println("byte - weight - bitcode - value");
		for (int valByte = 0; valByte < h.code.length; valByte++) {
            String bitCode = h.code[valByte];
            if(bitCode!=null){
                /*
                System.out.println(valByte+" - "+ h.getWeight(valByte)
                        + " - "+ bitCode
                        + " - " + (char)valByte);*/
            }
		}
    }

	public static void archiveFile(String fisName) throws IOException{
		Huffman h = new Huffman();

		//	делаем кода хаффмана
		setTreeLeaveWeight(h,fisName);
		growTree(h,fisName);
		h.makeCode();
        printHuffmanData(h);
        System.out.println(getWholeBitCode(h,fisName));

		//	записываем кодированные данные в потоке
        String fosName = fisName+".huf";
		FileOutput fos = new FileOutput(fosName);
		writeCodesToFile(h, fos);
		writeCodedDataToFile(h,fisName,fos);
		fos.clean();

		System.out.println(fisName+" has been archivated to "+fosName);
	}

	public static void unarchiveFile(String fisName) throws IOException{
		FileInput fis = new FileInput(fisName+".huf");
		Huffman h = new Huffman();
        // воссоздаем структуру хаффмана
        retrieveCodes(fis, h);
        h.growTree();
        h.makeCode();
		printHuffmanData(h);

        // в потоке читаем, декодируем и записываем в файл
        FileOutput fos = new FileOutput(fisName+".unhuf");  // разархивируем в ...
        byte[] buf = fis.read();
        String uncodeBits = "";
		while(fis.lenLeft()>1){
            uncodeBits += getBitsByByte(buf[0]);
            if(uncodeBits.length()>8){
                TwoString ts = h.slowDecoder(uncodeBits);
                uncodeBits = ts.str1;
                String word = ts.str2;
                fos.write(word);
            }
            buf = fis.read();
		}
        // считываем концовку файла, отрезаем пустые биты, завершаем запись файла
		while(buf.length > 0){
            uncodeBits += getBitsByByte(buf[0]);
            buf = fis.read();
		}
        fis.clean();
        byte lastWordLen = getByteByBits(uncodeBits.substring(uncodeBits.length()-8,uncodeBits.length()));
        int cutLen = 8;
        if(lastWordLen > 0)
            cutLen += 8 - lastWordLen;
        uncodeBits = uncodeBits.substring(0, uncodeBits.length()-cutLen);
        fos.write(h.fastDecoder(uncodeBits));
        fos.clean();

        System.out.println(fisName+".huf"+" has been unarchivated to "+fisName+".unhuf");
	}

//unarchive
	public static void main(String[] args) throws IOException {
		//testBBConversions();

        /*  архивируем и записываем результат* */
		String fisName = "D:/.huf/g.d";
		archiveFile(fisName);

        /*  возвращаем исходный файл * */
        unarchiveFile(fisName);

        System.out.println("ok!");


	}

}
