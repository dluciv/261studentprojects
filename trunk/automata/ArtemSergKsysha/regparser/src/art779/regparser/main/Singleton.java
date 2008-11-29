package art779.regparser.main;

public class Singleton {
	private static Helper instance = new Helper();
	
	private Singleton(){
		
	}
	
	public static Helper getInstanceHelper(){
		return instance;
	}
}


