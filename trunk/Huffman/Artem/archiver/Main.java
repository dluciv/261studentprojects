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
    	
        boolean[] bits = new boolean[4];
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
		s.o(getBitsByByte(byt));
		s.o(getByteByBits(getBitsByByte(byt)) );
		s.o(getBitsByByte(getByteByBits(getBitsByByte(byt))) );
		s.o(getByteByBits(getBitsByByte(getByteByBits(getBitsByByte(byt)))) );
	
	}

	
	public static void setWeight(Huffman h,String fisName) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			h.setWeights(buf);
		}
		fis.clean();
	}
	public static void growTree(Huffman h,String fisName) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		while(buf.length>0){
			buf = fis.read();
			h.growTree(buf);
		}
		fis.clean();
	}	
	public static void writeCodesToFile(Huffman h,FileOutput fos) throws IOException{
		//	первые 512 байт файла - коды вида [значение][ключ][значение][ключ]...
		for (int valByte = 0; valByte < h.code.length; valByte++) {
			byte keyByte = getByteByBits(h.code[valByte]);
			fos.write((byte)valByte);
			fos.write(keyByte);
			
			if(keyByte!=0)s.o(valByte+" - "+ keyByte+" - "+h.code[valByte]);
		}		
	}	
	public static void writeCodedDataToFile(Huffman h,String fisName,FileOutput fos) throws IOException{
		FileInput fis = new FileInput(fisName);
		byte[] buf = new byte[1];
		String lineBits = "";
		while(buf.length>0){
			buf = fis.read();
			String wordBits = h.coder(buf);
			lineBits += wordBits;
			if(lineBits.length()>8){
				wordBits = lineBits.substring(0, 8);
				lineBits = lineBits.substring(8);
				byte writeByte = getByteByBits(wordBits);
				fos.write(writeByte);
			}
			else if(lineBits.length()==8){
				byte writeByte = getByteByBits(lineBits);
				fos.write(writeByte);
				lineBits = "";
			}
		}	
		fis.clean();
	}
	public static void archivateFile(String fisName) throws IOException{
		String fosName = fisName+".huf";
		Huffman h = new Huffman();
		//	считаем весы букв алфавита
		setWeight(h,fisName);
		//	строим дерево
		growTree(h,fisName);
		//	получаем коды по дереву
		h.makeCode();
		//	записываем данные в потоке		
		FileOutput fos = new FileOutput(fosName);
		writeCodesToFile(h, fos);
		writeCodedDataToFile(h,fisName,fos);
		fos.clean();
		
		s.o(fisName+" has been archivated to "+fosName);
	}
	
	public static void retrieveCodes(FileInput fis, Huffman h) throws IOException{
		byte[] codes = new byte[512];
		codes = fis.read(512);		
		for (int i = 0; i < codes.length; i++) {
			if(0!=codes[i+1]){
				s.o(codes[i]+" - "+codes[i+1]+" - "+ getBitsByByte(codes[i+1]) );
				h.code[codes[i]] = getBitsByByte(codes[i+1]);
			}
			i++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//testBBConversions();
		/*
		String fisName = "D:/.huf/g.d";
		archivateFile(fisName);
		*/
		
		
		String fisName = "D:/.huf/g.d.huf";
		FileInput fis = new FileInput(fisName);
		Huffman h = new Huffman();
		//	получим коды для декодирования дальнейшего текста
		retrieveCodes(fis, h);
		/*
		byte[] dataBytes = new byte[5];
		dataBytes = fis.read(5);
		s.o("s1");
		String dataBits = null;
		for (int i = 0; i < dataBytes.length; i++) {
			s.o("s2");
			dataBits += getBitsByByte(dataBytes[i]);
		}
		s.o("s3");
		s.o(h.decoder(dataBits));
		
		fis.clean();
		*/
		

		
	}

}
