package archiver;

public class s {
	public static void b(byte bytes[])
	{
		String s = ""; 
		for (int i = 0; i < bytes.length; i++) {
			s += bytes[i] + "," ;
			
		}
		o(s);
	}
	public static void o(char str){
		System.out.println(str+"");
	}
	public static void o(String str){
		System.out.println(str);
	}
	public static void o(int str){
		o(str+"");
	}
}
