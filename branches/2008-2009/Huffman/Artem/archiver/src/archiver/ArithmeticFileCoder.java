package archiver;

import java.io.IOException;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class ArithmeticFileCoder extends ArithmeticCoder {

	public String encodedDataBuffer = "";
	public ArithmeticCharInterval getScaledInterval( ArithmeticCharInterval sourceinterval ){
		encodedDataBuffer = "";
		//int localfactor = factor;
		int localfactor = 10;
		int a = (int) (sourceinterval.a * localfactor);
		int b = (int) (sourceinterval.b * localfactor);
		while( 0 == ( a - b ) ){
			encodedDataBuffer += a;
			sourceinterval.scaleup();
			a = (int) (sourceinterval.a * localfactor);
			b = (int) (sourceinterval.b * localfactor);
		}
		return sourceinterval;
	}

	public void writeCharsToFile(FileOutput fos) throws IOException{
		for (ArithmeticChar ch : chars) {
			fos.write((byte)ch.key);
			fos.write((byte)ch.weight);
		}
		fos.write((byte)0);
		fos.write((byte)0);
	}
	
	public void encodeData(String fisName,String fosName) throws IOException{

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
		String encodedData = "";
		while(buf.length>0){
			for (byte b : buf) {
				curinterval = getNextInterval(b, curinterval);
				curinterval.print((char)b+"");
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

		System.out.println("0."+encodedData);
		// записываем реузультат файл
		FileOutput fos = new FileOutput(fosName);
		// записываем в файл таблицу символов
		writeCharsToFile(fos);
		// записываем в файл данные
		fos.write(encodedData);
		fos.flush();

	}
	
	public static void main(String[] args) throws IOException {
		
		String fn1 = "D:/.huf/g.a";
		String fn2 = "D:/.huf/g.a.coded";
		
		ArithmeticFileCoder a = new ArithmeticFileCoder();
		a.encodeData(fn1,fn2);
	}
}
