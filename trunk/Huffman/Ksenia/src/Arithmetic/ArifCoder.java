package Arithmetic;

import java.util.ArrayList;
import java.util.HashMap;

public class ArifCoder {
	
	public void code(byte[] mas ){
		int[] count = new int[256];
		int[] Q = new int[256];
		int[] k = new int[256];
		ArrayList<Byte> fin = new ArrayList<Byte>();
		for(int i=0; i<mas.length; i++){
			count[mas[i]+128]++;
		}
		for(int i=0; i<count.length;i++){
			if(i==0){
				Q[i] = count[i];
			}
			Q[i] = Q[i-1]+count[i];
		}
		for(int i=0; i<count.length;i++){
			for(int n = 0;;n++){
				if((1<<n)*count[i]>=mas.length){
					k[i] = n;
				}
			}
		}
		int s = k[count.length];
		int shift = 0;
		long cur = 0;
		for(int i =mas.length-1; i>=0;){
			while((s-shift<8) &&(i>=0)){
				cur = (Q[i] - count[i])<<(s-shift);
				i--;
				s = k[i]+s;
			}
			if(i>=0){
				int 
			}
		}
		
	}

}
