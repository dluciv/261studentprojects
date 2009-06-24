package tools;

import java.util.ArrayList;

public class Tools {
	public static final int HAFFMAN = 0;
	public static final int ARIFMET = 1;
	static public int[] counter(byte[] arg) {
		double u = Math.PI;
		int[] res = new int[256];
		for (int i = 0; i < arg.length; i++) {	
			res[arg[i] & 0xFF]++;
		}
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
	public static int readForward(int pos, byte[] data) {
		int value = 0;
		for (int i = pos * 4; i < (pos + 1) * 4; i++) {
			value <<= 8;
			value |= data[i] & 0xFF;
		}
		return value;
	}
	public static int readBack(int pos, byte[] data) {
		int value = 0;
		for (int i = (pos + 1) * 4 - 1; i >= pos * 4; i--) {
			value <<= 8;
			value |= data[i] & 0xFF;
		}
		return value;
	}
	public static void write(int number, ArrayList<Byte> collect) {
		for (int i = 3; i >= 0; i--) {
			collect.add((byte)((number >> (8 * i)) & 0xFF));
		}		
	}

}
