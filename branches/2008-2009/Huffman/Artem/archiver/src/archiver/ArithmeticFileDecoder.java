package archiver;

import java.io.IOException;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class ArithmeticFileDecoder extends ArithmeticCoder {
	public boolean cutOneFlag = false;
	public int digitsCuted = 0;
	public ArithmeticCharInterval getScaledInterval( ArithmeticCharInterval sourceinterval){
		digitsCuted = 0;
		int localfactor = 10;
		int a = (int) (sourceinterval.a * localfactor);
		int b = (int) (sourceinterval.b * localfactor);
		while( 0 == ( a - b ) ){
			digitsCuted++;
			sourceinterval.scaleup();
			a = (int) (sourceinterval.a * localfactor);
			b = (int) (sourceinterval.b * localfactor);
		}
		return sourceinterval;
	}

	public String saveStoF(String stbf){
		if('0'==stbf.charAt(stbf.length()-1)){
			stbf+="1";
			cutOneFlag = true;
		}
		return stbf;
	}
	public float getNewF(FileInput fis,float f) throws IOException{
		// дополняем число до readlen знаков после запятой
		byte[] buf;
		if( digitsCuted > 0 ){
			buf = fis.read(digitsCuted);
			if(cutOneFlag){digitsCuted++;cutOneFlag=false;}
			System.out.println((char)buf[0]);
			
			String stbf = saveStoF(getStringFromBytes(buf));
			System.out.println(stbf);
			System.out.println("0."+String.valueOf(f).substring(2+digitsCuted)+stbf);

			f = Float.valueOf("0."+String.valueOf(f).substring(2+digitsCuted)+stbf);
			digitsCuted = 0;
		}
		return f;
	}
	
	public void getCharsTable(FileInput fis) throws IOException{
		int readBytes = 2;
		byte[] codes = new byte[readBytes];
		codes = fis.read(readBytes);
	
		while(codes[0]!=0 && codes[1]!=0){
			ArithmeticChar ch = new ArithmeticChar();
			ch.key = codes[0];
			ch.weight = codes[1];
			chars.add(ch);
			codes = fis.read(readBytes);
		}
		signChars();
		//printChars();
	}

	public String getStringFromBytes(byte[] buf){
		String str = "";
		for (byte b : buf){
			str += (char)b;
		}
		return str;
	}

	public void decodeData(String fisName,String fosName) throws IOException{
		FileInput fis = new FileInput(fisName);
		// востанавливаем таблицу символов\
		getCharsTable(fis);
		// скорость потокового чтения из файла
		int readlen = 7;

		// получаем первый кусок кодированного числа
		byte[] buf = fis.read(readlen);
		float f = Float.valueOf(saveStoF("0."+getStringFromBytes(buf)));
		System.out.println(f);
		// открываем файл на запись в потоке
		//FileOutput fos = new FileOutput(fosName);
		// получаем первый промежуток для текущего числа
		String res = "";
		ArithmeticCharInterval curinterval = new ArithmeticCharInterval();
		for (ArithmeticChar ch : chars) {
			if(f > (float)ch.bound_low / factor && f < (float)ch.bound_high / factor){
				curinterval.a = (float)ch.bound_low / factor;
				curinterval.b = (float)ch.bound_high / factor;
				//curinterval.print((char)ch.key+"");
				//curinterval = getScaledInterval(curinterval);
				res += (char)ch.key;
				//fos.write(res);res = "";
				break;
			}
		}
		
		
		// продолжаем считывать данные, пересчитывая промежуток и число пока не еоф
		while(fis.lenLeft() > 0){
			//System.out.println(fis.lenLeft()+" lenLeft");
			// подбираем промежуток из таблицы, в который входит число
			for (ArithmeticChar ch : chars) {
				ArithmeticCharInterval tinterval = getNextInterval((byte)ch.key, curinterval);
				if(f > tinterval.a && f < tinterval.b){
					curinterval = tinterval;
					curinterval.print("before scale,f "+f);
					curinterval = getScaledInterval(curinterval);
					f = getNewF(fis, f);
					curinterval.print("after scale,f "+f);

					if(digitsCuted > 0){
						//System.out.println(digitsCuted+"dc");
						//System.out.println(String.valueOf(f).substring(2+digitsCuted));
					}
					//System.out.println(cutedFloatStringBuffer);
					//curinterval.print((char)ch.key+"");
					res += (char)ch.key;
					//fos.write(res);
					//res = "";
					break;
				}
			}
			fis.read();
			// дополняем число до numlen знаков после запятой
			
		}
		System.out.println(res);
		fis.flush();
		//fos.flush();
		/* */
	}

	public static void main(String[] args) throws IOException {
		
		String fn1 = "D:/.huf/g.a";
		String fn2 = "D:/.huf/g.a.coded";
		
		ArithmeticFileDecoder d = new ArithmeticFileDecoder();
		d.decodeData(fn2, fn1);
		
	}
}
