package tools;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
*
* @author ksenyiacypan
*/

public class Tools {
	public static final int BUF = 4096;
	public static final int HAFFMAN = 0;
	public static final int ARIFMET = 1;
	static public int[] counter(FileSt in) throws IOException {
		double u = Math.PI;		
		int[] res = new int[256];
		byte arg[] = new byte[4096];
		
		int len= 0;
		while ((len = in.read(arg)) > 0) {
			for (int i = 0; i < len; i++) {	
				res[arg[i] & 0xFF]++;
			}
		}
		in.refresh();		
		return res;
	}
	static public int kcount(int freq, int count) {
		int k = 0;
		if (freq == 0) {
			return 0;
		}
		for (; (freq << k) < count; k++);
		return k;
	}	
	static public int[] GetQ(int[] A) {
		int[] Q = new int[A.length + 1];
		Q[0] = 0;
		for (int i = 1; i <= A.length; i++) {
			Q[i] = A[i - 1] + Q[i - 1];
		}
		return Q;
	}
	static public int[] GetK(int[] A) {
		int N = 0;
		for (int i = 0; i < A.length; i++) {
			N += A[i];
		}
		int[] k = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			k[i] = kcount(A[i], N); 
		}
		return k;
	}
	public static int readForward(FileSt in) throws IOException {
		int value = 0;
		byte[] data = new byte[4];
		in.read(data);
		for (int i = 0; i < 4; i++) {
			value <<= 8;
			value |= data[i] & 0xFF;
		}
		return value;
	}
	public static int readBack(FileSt in) throws IOException {
		int value = 0;
		byte[] data = new byte[4];
		in.read(data);
		for (int i = 3; i >= 0; i--) {
			value <<= 8;
			value |= (data[i] & 0xFF) << 8 ;
		}
		return value;
	}
	public static void reverse(String arg0, String arg1) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(arg0, "rws");
		FileOutputStream out = new FileOutputStream(arg1);
		long size = raf.length();
		byte[] buffer = new byte[BUF];
		
		for (long i = size / BUF * BUF; i >= 0; i -= BUF) {
			int len = raf.read(buffer, 0, BUF);
			for (int j = 0; j < len / 2; j++) {
				buffer[j] ^= buffer[len - 1 - j];
				buffer[len - 1 - j] ^= buffer[j]; 
				buffer[j] ^= buffer[len - 1 - j];
			}
			out.write(buffer, 0, len);
		}
		
		out.close();
	}
	
	public static void write(int number, OutputStream out) throws IOException {
		byte[] u = new byte[4];
		for (int i = 3; i >= 0; i--) {
			u[i] = (byte)((number >> (8 * i)) & 0xFF);
		}	
		out.write(u);
	}

}
