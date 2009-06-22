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
public class ArithmeticFileCoder extends ArithmeticCoder {

	public void writeCharsToFile(FileOutput fos) throws IOException{
		for (ArithmeticChar ch : chars) {
			fos.write((byte)ch.key);
			fos.write((byte)ch.weight);
		}
        fos.write((byte)0);
		fos.write((byte)0);
	}
	
	public void writeDataToFile(FileOutput fos,String data) throws IOException{
		byte[] bytes = new byte[data.length()];
		for (int i = 0; i < data.length(); i++) {
			bytes[i] = (byte) data.charAt(i);
		}
		fos.write(bytes);
	}

	public String getEncodedData(String fisName,String fosName) throws IOException{

		FileInput fis = new FileInput(fisName);
		int readlen = 128;
		//создаем кодирующую таблицу символов
		byte[] buf = new byte[readlen];
		while(buf.length > 0){
			buf = fis.read(readlen);
			addChars(buf);
		}
		fis.flush();
		signChars();
		printChars();

		fis = new FileInput(fisName);
		buf = new byte[readlen];

		// производим начальное заполнение интервала
		buf = fis.read(1);
		ArithmeticCharInterval curinterval = new ArithmeticCharInterval();
		curinterval.a = (float)getChar(buf[0]).bound_low / factor;
		curinterval.b = (float)getChar(buf[0]).bound_high / factor;
		curinterval.print((char)buf[0]+"");

		// в потоке пересчитываем интервал для каждого символа
		buf = fis.read(readlen);
		//buf = fis.read();
		String encodedData = "";
		while(buf.length>0){
			for (byte b : buf) {
				curinterval = getNextInterval(b, curinterval);
				curinterval = getScaledInterval(curinterval);
				curinterval.print((char)b+"");
				encodedData += encodedDataBuffer;
			}
			buf = fis.read(readlen);
		}
		fis.flush();
		// получаем конечное число
		float num = (float) (curinterval.a + (curinterval.b - curinterval.a) / 2);
		encodedData += (""+num).substring(2);
		return encodedData;
	}
	
	public static void main(String[] args) throws IOException {
		ArithmeticFileCoder a = new ArithmeticFileCoder();
		String fn1 = "D:/.huf/g.a";
		String fn2 = "D:/.huf/g.a.coded";
		String data = a.getEncodedData(fn1,fn2);
		// записываем в файл таблицу символов
		FileOutput fos = new FileOutput(fn2);
		a.writeCharsToFile(fos);
		// записываем в файл данные
		a.writeDataToFile(fos, data);
		fos.flush();

		System.out.println("0."+data);
	}
}
